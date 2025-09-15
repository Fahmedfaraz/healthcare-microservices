package com.mediscreen.practitioner_notes.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.practitioner_notes.exception.UserNotFoundException;
import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.repository.PractitionerNotesRepository;

@Service
public class PractitionerNotesService {

	private static final Log logger = LogFactory.getLog(PractitionerNotesService.class);

	@Autowired
	PractitionerNotesRepository practitionerNotesRepository;

	// Get all Practitioner Notes
	public List<PractitionerNotes> getAllNotes() {
		logger.info("getAllNotes-started-");
		return practitionerNotesRepository.findAll();
	}

//		Save Patient history
	public PractitionerNotes savePatientHistory(PractitionerNotes pNotes) {
		logger.info("savePatientHistory-started-");
		return practitionerNotesRepository.save(pNotes);
	}

//		Find by Patient ID
	public List<PractitionerNotes> getAllNotesByPatientId(Long patientId) {
		logger.info("getAllNotesByPatientId-started-" + patientId);
		List<PractitionerNotes> notes = practitionerNotesRepository.findBypatientId(patientId);

		// If list is empty, throw exception
		if (notes == null || notes.isEmpty()) {
			throw new UserNotFoundException("No PractitionerNotes found for Patient ID: " + patientId);
		}
		return notes;
	}

//	Find practitioner note by ID
	public PractitionerNotes findById(String id, PractitionerNotes practitionerNotes) {
		logger.info("findById-started-" + id);
		PractitionerNotes pNote = practitionerNotesRepository.findById(id);

		// If not found, throw exception
		if (pNote == null) {
			throw new UserNotFoundException("PractitionerNote not found for Id: " + id);
		}
		pNote.setNote(practitionerNotes.getNote());
		practitionerNotesRepository.save(pNote);
		return practitionerNotes;
	}
}
