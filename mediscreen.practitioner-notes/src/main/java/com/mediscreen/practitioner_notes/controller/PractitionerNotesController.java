package com.mediscreen.practitioner_notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.practitioner_notes.exception.UserNotFoundException;
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
	@ResponseStatus(HttpStatus.CREATED)
	public void addPatientHistory(@RequestBody PractitionerNotes pNotes) {
		practitionerNotesService.savePatientHistory(pNotes);
	}
	
// get patient notes by ID
	@GetMapping("/patient/{patientId}")
	public List<PractitionerNotes> getNotesByPatientId(@PathVariable Long patientId) {	
		List<PractitionerNotes> practitionerNotes = practitionerNotesService.getAllNotesByPatientId(patientId);
		if (practitionerNotes == null || practitionerNotes.isEmpty()) {
	        throw new UserNotFoundException("No PractitionerNotes found for Patient ID: " + patientId);
	    }
		return practitionerNotes;
	}
	
//	Update Patient notes
	@PutMapping("/patHistory")
	 public PractitionerNotes updatePatientHistory(@RequestParam("id") String id, @RequestBody PractitionerNotes practitionerNotes) {
		return practitionerNotesService.findById(id, practitionerNotes);
	}

}
