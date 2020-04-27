package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping("/")
	public String Index() {
		return "<h1>Teste Azor Carlos</h1>";
	}

}
