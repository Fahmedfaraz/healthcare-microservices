package com.mediscreen.practitioner_notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "practitioner-notes")
public class PractitionerNotes {
    @Id
    private String patId;
    private String note;
    
    // Getters and setters
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
   

   
    
}