package com.aag.spring.fruit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {

	@GetMapping("/wishMe")
	public String greeting(@RequestParam("name") String name, Model model) {
		String message = "Hi " + name + " welcome to Java Techie";
		model.addAttribute("message", message);
		return "otro";
	}

}
