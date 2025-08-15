package com.mediscreen.practitioner_notes.model;

import org.springframework.aot.generate.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "practitioner-notes")
public class PractitionerNotes {
    @Id
    
    private String id;
    private Long patientId;
    private String note;
    
    // Getters and setters

	public String getId() {
		return id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
   

   
    
}