package com.mediscreen.practitioner_notes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.repository.PractitionerNotesRepository;

@Service
public class PractitionerNotesService {
	@Autowired
	PractitionerNotesRepository practitionerNotesRepository;
	
	// Get all Practitioner Notes
		public List<PractitionerNotes> getAllNotes() {
			return practitionerNotesRepository.findAll();
		}

//		Save Patient history
		public PractitionerNotes savePatientHistory(PractitionerNotes pNotes) {
			return practitionerNotesRepository.save(pNotes);
			
		}
}
