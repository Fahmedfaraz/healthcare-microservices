package com.mediscreen.practitioner_notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.service.PractitionerNotesService;

@RestController
public class PractitionerNotesController {
	@Autowired
	PractitionerNotesService practitionerNotesService;

// get all the patients notes in the database
	@GetMapping("/patHistory")
	public List<PractitionerNotes> patHistory() {
		return practitionerNotesService.getAllNotes();
	}
	
// Add new patient note
	@PostMapping("/patHistory/add")
	public void addPatientHistory(@RequestBody PractitionerNotes pNotes) {
		practitionerNotesService.savePatientHistory(pNotes);
	}
	
// get patient notes by ID
	@GetMapping("/patient/{patientId}")
	public List<PractitionerNotes> getNotesByPatientId(@PathVariable Long patientId) {	
		return practitionerNotesService.getAllNotesByPatientId(patientId);		
	}

}
