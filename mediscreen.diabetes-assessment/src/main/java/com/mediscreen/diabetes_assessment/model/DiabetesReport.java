package com.mediscreen.diabetes_assessment.model;

import java.util.Date;
import java.util.List;

import com.mediscreen.diabetes_assessment.constants.RiskLevel;

public class DiabetesReport {
	
	/*private Patient patient;
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
	*/
	
	private Long patientId;

	private String family;

	private String given;

	private int age;

	private String sex;
	
	private List<PractitionerNotes> notes;
	
	public RiskLevel diabetesRisk;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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
