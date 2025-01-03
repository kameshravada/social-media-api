package com.kamesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/hi")
	public String hiControllerHandler() {
		return "Hi im Kamesh \n im good What about you ??";
	}
	
	@GetMapping("/")
	public String homeControllerHandler() {
		return "Hi Welcome here";
	}
}
