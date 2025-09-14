package com.mediscreen.patient_demographics.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient_demographics.controller.PatientController;
import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.repository.PatientRepository;
import com.mediscreen.patient_demographics.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

	static ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private PatientController patientController;

	@MockitoBean
	private PatientRepository patientReopsitory;

	@MockitoBean
	private PatientService patientService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
    void contextLoads() {
	}
	
	@Test
	void testGetPatients() throws Exception {
		Patient patient1 = createSamplePatient(1L);
		Patient patient2 = createSamplePatient(2L);
		List<Patient> patients = Arrays.asList(patient1, patient2);

		when(patientService.getPatients()).thenReturn(patients);

		mockMvc.perform(get("/patients")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].patientId").value(1)).andExpect(jsonPath("$[1].patientId").value(2));
	}

	@Test
	void testGetPatient() throws Exception {
		Patient patient = createSamplePatient(1L);
		List<Patient> allPatient = new ArrayList<Patient>();
		allPatient.add(patient);
		when(patientService.getPatient("John")).thenReturn(allPatient);

		mockMvc.perform(get("/patient/given").param("name", "John")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].given").value("John")).andExpect(jsonPath("$[0].family").value("Smith"));
	}

	@Test
	void testGetPatientByID() throws Exception {
		Patient patient = createSamplePatient(1L);
		when(patientService.getPatientById(1L)).thenReturn(patient);

		mockMvc.perform(get("/patient/1")).andExpect(status().isOk()).andExpect(jsonPath("$.patientId").value(1))
				.andExpect(jsonPath("$.given").value("John"));
	}
	
	@Test
	void testGetPersonInfoNegative() throws Exception {
		
		
		mockMvc.perform(get("/patient/3333"))
		.andExpect(status().is4xxClientError());
	}

	@Test
	void testAddPatient() throws JsonProcessingException, Exception {
		Date date1 = new Date(System.currentTimeMillis());

		Patient addPatient = new Patient();
		addPatient.setFamily("Jill");
		addPatient.setGiven("Smith");
		addPatient.setDob(date1);
		addPatient.setPhone("789654128");
		addPatient.setAddress("123 Edison St");
		addPatient.setSex("F");
		mockMvc.perform(
				post("/patient/add").contentType("application/json").content(mapper.writeValueAsString(addPatient)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testUpdatePatient() throws Exception {

		// Arrange
		Patient updatedPatient = createSamplePatient(1L); 
		updatedPatient.setFamily("Doe");
		updatedPatient.setGiven("John");

		ObjectMapper objectMapper = new ObjectMapper();
		String updatedPatientJson = objectMapper.writeValueAsString(updatedPatient);

		// Act + Assert
		mockMvc.perform(put("/patient?patientId=1").contentType(MediaType.APPLICATION_JSON).content(updatedPatientJson))
				.andExpect(status().isOk());

	}

	private Patient createSamplePatient(Long id) {

		Date date1 = new Date(System.currentTimeMillis());
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
