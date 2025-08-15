package com.mediscreen.diabetes_assessment.model;

public class PractitionerNotes {

	private Long patientId;
	private String note;

	// Getters and setters
	

	public String getNote() {
		return note;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public void setNote(String note) {
		this.note = note;
	}

}