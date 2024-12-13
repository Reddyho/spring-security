package com.coforge.training.security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*Author:Koppula.Reddy
*Date  :Dec 2, 2024
*Time   :3:22:48 PM
*
*/


@RestController
@RequestMapping("/api")
public class HelloWorldController {

	@GetMapping("/hello")
	public String Hello() {
		
		return "hello world from spring security";
	}
}
