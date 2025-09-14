package com.mediscreen.diabetes_assessment.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.diabetes_assessment.client.DoctorNotesClient;
import com.mediscreen.diabetes_assessment.client.PatientClient;
import com.mediscreen.diabetes_assessment.constants.RiskLevel;
import com.mediscreen.diabetes_assessment.constants.Triggers;
import com.mediscreen.diabetes_assessment.exception.ResourceNotFoundException;
import com.mediscreen.diabetes_assessment.model.DiabetesReport;
import com.mediscreen.diabetes_assessment.model.Patient;
import com.mediscreen.diabetes_assessment.model.PractitionerNotes;

@Service
public class DiabetesReportService {
	private static final Log logger = LogFactory.getLog(DiabetesReportService.class);

	@Autowired
	private PatientClient patientClient;

	@Autowired
	private DoctorNotesClient doctorNoteClient;

	public DiabetesReport generateReport(Long patientId) {
		RiskLevel risk = null;
		logger.info("generateReport-" + patientId);
		Patient patient = patientClient.getPatientById(patientId);
		logger.info("generateReport-response" + patient);
		if (patient != null) {
			String birthDate = new SimpleDateFormat("yyyy-MM-dd").format(patient.getDob());
			String claculatedAge = getAge(birthDate);
			int age = Integer.parseInt(claculatedAge);

			logger.info("getNotesByPatientId-" + patientId);
			List<PractitionerNotes> notes = doctorNoteClient.getNotesByPatientId(patientId);
			
			logger.info("getNotesByPatientId-" + notes);
			int triggerCount = countTriggers(notes);

			if (triggerCount <= 1) {
//	            log.info("Patient with id {} has no diabetes risk", patientId);
				risk = RiskLevel.NONE;

			} else if (age >= 30) {
				if (triggerCount >= 2 && triggerCount <= 5) {
					risk = RiskLevel.BORDERLINE;
				} else if (triggerCount >= 6 && triggerCount <= 7) {
					risk = RiskLevel.IN_DANGER;
				} else if (triggerCount >= 8) {
					risk = RiskLevel.EARLY_ONSET;
				}
			} else if (patient.getSex() == "M" && age <= 30) {
				if (triggerCount >= 3 && triggerCount <= 4) {
					risk = RiskLevel.IN_DANGER;
				} else if (triggerCount >= 5) {
					risk = RiskLevel.EARLY_ONSET;
				}
			} else if (patient.getSex() == "F" && age <= 30) {
				if (triggerCount >= 4 && triggerCount <= 6) {
					risk = RiskLevel.IN_DANGER;
				} else if (triggerCount >= 7) {
					risk = RiskLevel.EARLY_ONSET;
				}
			}

			DiabetesReport diabetesReport = new DiabetesReport();
			diabetesReport.setPatientId(patient.getPatientId());
			diabetesReport.setFamily(patient.getFamily());
			diabetesReport.setGiven(patient.getGiven());
			diabetesReport.setAge(age);
			diabetesReport.setSex(patient.getSex());
			diabetesReport.setNotes(notes);
			diabetesReport.setDiabetesRisk(risk);
			logger.info("diabetesReport-" + diabetesReport);
			return diabetesReport;
		} else {
			throw new ResourceNotFoundException("Patient not found with PatientID: " + patientId);
		}
	}

	public String getAge(String birthDateStr) {
		String pattern = "yyyy-MM-dd";
		String age;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
		LocalDate currentDate = LocalDate.now();

		// Calculate the period between the birth date and current date
		Period period = Period.between(birthDate, currentDate);

		// Return the age in years
		age = period.getYears() + "";

		return age;
	}

	public int countTriggers(List<PractitionerNotes> notes) {

		if (notes == null) {
//	            log.debug("notes is null, returning 0");
			return 0;
		}
		int count = 0;
		List<String> countedTriggers = new ArrayList<>();

		for (PractitionerNotes pNote : notes) {
			for (String trigger : Triggers.getTriggers()) {
				if (pNote.getNote().toLowerCase().contains(trigger.toLowerCase())
						&& !countedTriggers.contains(trigger.toLowerCase())) {
					count++;
					countedTriggers.add(trigger.toLowerCase());
				}
			}
		}
		return count;
	}
}