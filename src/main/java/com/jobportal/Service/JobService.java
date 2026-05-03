package com.jobportal.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Entities.Job;
import com.jobportal.Repositories.JobRepo;

@Service
public class JobService {

	@Autowired
	private JobRepo jrepo;

	@Autowired
	private UsersService uservice;

	public void addJob(String jobTitle, String jobDesc, String jobLoc, String jobCompany,
					   String jobPostedBy, String jobSalary, String jobQualification,
					   Integer vacancy, LocalDate applicationDeadline, String jobType)
	{
		Job job = new Job();
		job.setJobTitle(jobTitle);
		job.setJobDesc(jobDesc);
		job.setJobLoc(jobLoc);
		job.setJobCompany(jobCompany);
		job.setJobPostedBy(jobPostedBy);
		job.setJobPosted(java.time.LocalDateTime.now());
		job.setJobSalary(jobSalary);
		job.setJobQualification(jobQualification);
		job.setVacancy(vacancy);
		job.setApplicationDeadline(applicationDeadline);
		job.setJobType(jobType);

		jrepo.save(job);
	}

	public List<Job> getAllJobs()
	{
		return jrepo.findAll();
	}

	public List<Job> searchJobs(String keyword)
	{
		if (keyword == null || keyword.trim().isEmpty()) {
			return jrepo.findAll();
		}
		return jrepo.searchJobs(keyword.trim());
	}

	public Optional<Job> getJobByTitle(String jobTitle)
	{
		return jrepo.findByJobTitle(jobTitle);
	}

	public void updateJob(Long jobId, String jobTitle, String jobDesc, String jobLoc, String jobCompany,
						  String jobPostedBy, String jobSalary, String jobQualification,
						  Integer vacancy, LocalDate applicationDeadline, String jobType)
	{
		Job job = jrepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
		job.setJobTitle(jobTitle);
		job.setJobDesc(jobDesc);
		job.setJobLoc(jobLoc);
		job.setJobCompany(jobCompany);
		// jobPostedBy and jobPosted are intentionally NOT updated —
		// changing them would break the recruiter's dashboard lookup
		// and lose the original posting timestamp.
		job.setJobSalary(jobSalary);
		job.setJobQualification(jobQualification);
		job.setVacancy(vacancy);
		job.setApplicationDeadline(applicationDeadline);
		job.setJobType(jobType);

		jrepo.save(job);
	}

	public void deleteJob(Long jobId)
	{
		jrepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
		jrepo.deleteById(jobId);
	}

	public Job getJobById(Long jobId) {
		return jrepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
	}

	public List<Job> getJobsByRecruiter(String username) {
		return jrepo.findByJobPostedBy(username);
	}
}