package com.mediscreen.diabetes_assessment.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mediscreen.diabetes_assessment.model.PractitionerNotes;

@FeignClient(name = "doctor-notes-service", url = "${doctor.notes.service.url}")

public interface DoctorNotesClient {
	 @GetMapping("/patient/{patId}")
	    List<PractitionerNotes> getNotesByPatientId(@PathVariable ( name = "patId") Long patId);
}
