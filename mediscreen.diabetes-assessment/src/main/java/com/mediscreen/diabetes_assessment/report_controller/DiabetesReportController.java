package com.mediscreen.diabetes_assessment.report_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.diabetes_assessment.model.DiabetesReport;
import com.mediscreen.diabetes_assessment.service.DiabetesReportService;

@RestController
public class DiabetesReportController {
	@Autowired
	private DiabetesReportService diabetesReportService;
	
	@GetMapping("/diabetes/{patientId}")
    public DiabetesReport getDiabetesReport(@PathVariable Long patientId) {
        return diabetesReportService.generateReport(patientId);
    }
}
