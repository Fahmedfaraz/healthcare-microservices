package com.mediscreen.patient_demographics.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient_demographics.controller.PatientController;
import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.repository.PatientRepository;
import com.mediscreen.patient_demographics.service.PatientService;
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
	
	static ObjectMapper mapper=new ObjectMapper();
	
	@InjectMocks
	private PatientController patientController;
	
	@MockitoBean
	private PatientRepository patientReopsitory;
	
	@MockitoBean
    private PatientService patientService; 
	
	private Patient patient;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
//        objectMapper = new ObjectMapper();
	}
	


	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPatients() throws Exception {
//		mockMvc.perform(get("/patients")).andExpect(status().is2xxSuccessful());
		Patient patient1 = createSamplePatient(1l);
        Patient patient2 = createSamplePatient(2l);
        List<Patient> patients = Arrays.asList(patient1, patient2);

//        when(patientService.getPatients()).thenReturn(patients);
//        mockMvc.perform(get("/patients"))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.length()").value(2))
//        .andExpect(jsonPath("$[0].patient_id").value("P1"))
//        .andExpect(jsonPath("$[1].patient_id").value("P2"));
        mockMvc.perform(get("/patients")).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testGetPatient() {
//		fail("Not yet implemented");
	}

	@Test
	void testAddPatient() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdatePatient() {
//		fail("Not yet implemented");
	}
	private Patient createSamplePatient(Long id) {
		
		Date date1 = new Date( System.currentTimeMillis() );
        Patient patient = new Patient();
        patient.setPatientId(id);
        patient.setFamily("Smith");
        patient.setGiven("John");
        patient.setDob(date1);
        patient.setAddress("123 Main St");
        patient.setSex("M");
        patient.setPhone("1234567890");
        return patient;
    }
}
