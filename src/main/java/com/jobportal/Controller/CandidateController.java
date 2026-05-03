package com.jobportal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobportal.Entities.Application;
import com.jobportal.Entities.Job;
import com.jobportal.Entities.Role;
import com.jobportal.Entities.Users;
import com.jobportal.Service.ApplicationService;
import com.jobportal.Service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private JobService jservice;

	@Autowired
	private ApplicationService aservice;

	@GetMapping("/cdashboard")
	public String dashboard(HttpSession session, Model model,
							@RequestParam(required = false) String keyword)
	{
		Users currentUser = (Users) session.getAttribute("currentUser");

		if (currentUser == null || !currentUser.getRole().contains(Role.ROLE_CANDIDATE)) {
			return "redirect:/login";
		}
		model.addAttribute("userName", currentUser.getUserName());

		List<Job> jobs = jservice.searchJobs(keyword);
		List<Application> applications = aservice.getApplicationsByUsers(currentUser.getUserId());

		model.addAttribute("jobs", jobs);
		model.addAttribute("applications", applications);
		model.addAttribute("keyword", keyword != null ? keyword : "");
		return "c-dashboard";
	}

	@PostMapping("/apply")
	public String applyToJob(@RequestParam Long jobId, @RequestParam String resume, HttpSession session, RedirectAttributes redirectAttrs)
	{
		Users user = (Users) session.getAttribute("currentUser");
		try {
			aservice.applyToJob(jobId, user.getUserId(), resume);
			redirectAttrs.addFlashAttribute("success", "Application submitted successfully!");
		} catch (IllegalStateException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/candidate/cdashboard";
	}

	@GetMapping("/cancel/{id}")
	public String cancelApplication(@PathVariable("id") Long appId, HttpSession session) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || !currentUser.getRole().contains(Role.ROLE_CANDIDATE)) {
			return "redirect:/login";
		}
		aservice.cancelApplication(appId);
		return "redirect:/candidate/cdashboard";
	}
}