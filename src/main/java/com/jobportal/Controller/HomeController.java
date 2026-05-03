package com.jobportal.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home()
	{
		return "home";
	}

	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpSession session)
	{
		session.invalidate();
		return "redirect:/login";
	}
	@GetMapping("/register")
	public String newUser()
	{
		return "register";
	}

}