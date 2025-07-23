package com.mediscreen.practitioner_notes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(path = "/testPN")
	public String helloworld() {
		return "Docker Image is running for Practitioner notes";
	}

}
