package com.mediscreen.practitioner_notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.service.PractitionerNotesService;

@RestController
public class PractitionerNotesController {
	@Autowired
	PractitionerNotesService practitionerNotesService;
	
	@GetMapping("/patHistory")
	public List<PractitionerNotes> patHistory() {
		return practitionerNotesService.getAllNotes();
	}
	
	@PostMapping("/patHistory/add")
	public void addPatientHistory(@RequestBody PractitionerNotes pNotes) {
		practitionerNotesService.savePatientHistory(pNotes);
	}

}
