package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.security.context.AccountContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "message", required = false) String message,
						Model model) {
		model.addAttribute("error", error);
		model.addAttribute("message", message);
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
	}

	@GetMapping("/denied")
	public String accessDenied(@RequestParam(value = "message", required = false) String message,
		                       Model model) {
		AccountContext account = (AccountContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", account.getUsername());
		model.addAttribute("message", message);
		return "denied";
	}
}
