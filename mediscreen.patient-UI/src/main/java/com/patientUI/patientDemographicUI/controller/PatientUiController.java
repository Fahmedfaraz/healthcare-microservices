package com.patientUI.patientDemographicUI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.patientUI.patientDemographicUI.model.Patient;
import com.patientUI.patientDemographicUI.service.PatientApiClient;

@Controller
@RequestMapping("/ui/patients")
public class PatientUiController {
	
	@Autowired
	private final PatientApiClient apiClient;

    public PatientUiController(PatientApiClient apiClient) {
        this.apiClient = apiClient;
    }
    
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allPatientList", apiClient.getAllPatients());
        return "patient-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patientDto", new Patient());
        return "patient-form";
    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient dto, RedirectAttributes redirectAttributes) {
        apiClient.createPatient(dto);
        redirectAttributes.addFlashAttribute("success", "Patient created successfully");
        return "redirect:/ui/patients/new";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("patientDto", apiClient.getPatient(id));
        return "patient-form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient dto,
                                RedirectAttributes redirectAttributes) {
        apiClient.updatePatient(id, dto);
        redirectAttributes.addFlashAttribute("success", "Patient updated successfully");
        return "redirect:/ui/patients/" + id + "/edit";
    }
}
