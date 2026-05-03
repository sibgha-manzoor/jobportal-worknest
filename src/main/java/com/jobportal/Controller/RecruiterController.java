package com.jobportal.Controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.Entities.Application;
import com.jobportal.Entities.ApplicationStatus;
import com.jobportal.Entities.Job;
import com.jobportal.Entities.Role;
import com.jobportal.Entities.Users;
import com.jobportal.Service.ApplicationService;
import com.jobportal.Service.JobService;
import com.jobportal.Service.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

	@Autowired
	private JobService jservice;

	@Autowired
	private UsersService uservice;

	@Autowired
	private ApplicationService aservice;

	@GetMapping("/rdashboard")
	public String recruiterDashboard(HttpSession session, Model model) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		String recruiter = currentUser.getUserName();

		List<Job> jobs = jservice.getJobsByRecruiter(recruiter);

		List<Application> applications = new java.util.ArrayList<>();
		for (Job job : jobs) {
			applications.addAll(aservice.getApplicationsByJob(job.getJobId()));
		}

		List<Users> allUsers = uservice.getAllUsers();
		List<Users> candidates = allUsers.stream()
				.filter(user -> user.getRole().contains(Role.ROLE_CANDIDATE))
				.toList();

		model.addAttribute("jobs", jobs);
		model.addAttribute("candidates", candidates);
		model.addAttribute("applications", applications);
		model.addAttribute("statuses", ApplicationStatus.values());
		return "r-dashboard";
	}

	@PostMapping("/updatestatus")
	public String updateApplicationStatus(@RequestParam Long appId, @RequestParam ApplicationStatus status, RedirectAttributes redirectAttrs) {
		aservice.updateStatus(appId, status);
		redirectAttrs.addFlashAttribute("success", "Application status updated successfully!");
		return "redirect:/recruiter/rdashboard";
	}

	@PostMapping("/addjob")
	public String addJob(
			@RequestParam String jobTitle,
			@RequestParam String jobDesc,
			@RequestParam String jobLoc,
			@RequestParam String jobCompany,
			@RequestParam String jobPostedBy,
			@RequestParam String jobSalary,
			@RequestParam String jobQualification,
			@RequestParam(required = false) Integer vacancy,
			@RequestParam(required = false) String applicationDeadline,
			@RequestParam(required = false) String jobType,
			RedirectAttributes redirectAttrs)
	{
		LocalDate deadline = (applicationDeadline != null && !applicationDeadline.isEmpty())
				? LocalDate.parse(applicationDeadline) : null;
		jservice.addJob(jobTitle, jobDesc, jobLoc, jobCompany, jobPostedBy, jobSalary, jobQualification,
				vacancy, deadline, jobType);
		redirectAttrs.addFlashAttribute("success", "Job added successfully!");
		return "redirect:/recruiter/rdashboard";
	}

	@GetMapping("/deletejob")
	public String deleteJob(@RequestParam Long jobId) {
		jservice.deleteJob(jobId);
		return "redirect:/recruiter/rdashboard";
	}

	@GetMapping("/editjob")
	public String showEditForm(@RequestParam Long jobId, Model model) {
		Job job = jservice.getJobById(jobId);
		model.addAttribute("job", job);
		return "edit-job";
	}

	@PostMapping("/updatejob")
	public String updateJob(
			@RequestParam Long jobId,
			@RequestParam String jobTitle,
			@RequestParam String jobDesc,
			@RequestParam String jobLoc,
			@RequestParam String jobCompany,
			@RequestParam String jobPostedBy,
			@RequestParam String jobSalary,
			@RequestParam String jobQualification,
			@RequestParam(required = false) Integer vacancy,
			@RequestParam(required = false) String applicationDeadline,
			@RequestParam(required = false) String jobType,
			RedirectAttributes redirectAttrs)
	{
		LocalDate deadline = (applicationDeadline != null && !applicationDeadline.isEmpty())
				? LocalDate.parse(applicationDeadline) : null;
		jservice.updateJob(jobId, jobTitle, jobDesc, jobLoc, jobCompany, jobPostedBy, jobSalary, jobQualification,
				vacancy, deadline, jobType);
		redirectAttrs.addFlashAttribute("success", "Job updated successfully!");
		return "redirect:/recruiter/rdashboard";
	}
}