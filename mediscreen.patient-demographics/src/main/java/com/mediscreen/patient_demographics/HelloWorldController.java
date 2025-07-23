package com.mediscreen.patient_demographics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@GetMapping(path="/")
	public String helloworld() {
		return "Docker Image is running for Patient Demographics";
	}

}
