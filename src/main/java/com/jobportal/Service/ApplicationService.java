package com.jobportal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Entities.Application;
import com.jobportal.Entities.Job;
import com.jobportal.Entities.Users;
import com.jobportal.Repositories.ApplicationRepo;
import com.jobportal.Repositories.JobRepo;
import com.jobportal.Repositories.UsersRepo;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepo arepo;

	@Autowired
	private JobRepo jrepo;

	@Autowired
	private UsersRepo urepo;


	public Application applyToJob(Long jobId, Long userId, String resume)
	{
		Job job = jrepo.findById(jobId).orElseThrow(()-> new RuntimeException("Job not found"));
		Users users = urepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));

		if (arepo.existsByJobAndUsers(job, users)) {
			throw new IllegalStateException("You have already applied to this job.");
		}

		if (job.getApplicationDeadline() != null &&
				job.getApplicationDeadline().isBefore(java.time.LocalDate.now())) {
			throw new IllegalStateException("The application deadline for this job has passed. You can no longer apply.");
		}

		Application apply = new Application();
		apply.setJob(job);
		apply.setUsers(users);
		apply.setResume(resume);
		apply.setAppliedAt(java.time.LocalDateTime.now());
		apply.setStatus(com.jobportal.Entities.ApplicationStatus.APPLIED);
		return arepo.save(apply);
	}

	public Application updateStatus(Long appId, com.jobportal.Entities.ApplicationStatus status)
	{
		Application application = arepo.findById(appId)
				.orElseThrow(() -> new RuntimeException("Application not found"));
		application.setStatus(status);
		return arepo.save(application);
	}

	public List<Application> getApplicationsByJob(Long jobId)
	{
		Job job = jrepo.findById(jobId).orElseThrow(() -> new RuntimeException("applications not found"));
		return arepo.findByJob(job);
	}

	public List<Application> getApplicationsByUsers(Long usersId)
	{
		Users users = urepo.findById(usersId).orElseThrow(() -> new RuntimeException("applications not found"));
		return arepo.findByUsers(users);
	}

	public void cancelApplication(Long appId) {
		arepo.deleteById(appId);
	}

}