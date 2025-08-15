package com.mediscreen.diabetes_assessment.model;

import java.util.List;

import com.mediscreen.diabetes_assessment.constants.RiskLevel;

public class DiabetesReport {
	
	private Patient patient;
    private List<PractitionerNotes> notes;
	public RiskLevel diabetesRisk;	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<PractitionerNotes> getNotes() {
		return notes;
	}
	public void setNotes(List<PractitionerNotes> notes) {
		this.notes = notes;
	}
	public RiskLevel getDiabetesRisk() {
		return diabetesRisk;
	}
	public void setDiabetesRisk(RiskLevel diabetesRisk) {
		this.diabetesRisk = diabetesRisk;
	}
	
    
}
