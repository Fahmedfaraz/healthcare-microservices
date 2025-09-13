package com.mediscreen.patient_demographics.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient_demographics.controller.PatientController;
import com.mediscreen.patient_demographics.exception.UserNotFoundException;
import com.mediscreen.patient_demographics.model.Patient;
import com.mediscreen.patient_demographics.repository.PatientRepository;
import com.mediscreen.patient_demographics.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
	static ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private PatientService patientService;

	@MockitoBean
	private PatientRepository patientReopsitory;

	@MockitoBean
	private PatientController patientController;

	@Autowired
	private MockMvc mockMvc;
	private Patient patient1;
	private Patient patient2;

	@BeforeEach
	void setUp() throws Exception {
		Date date1 = new Date(System.currentTimeMillis());
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.YEAR, -50); // subtract 50 years
//		Date date1 = (Date) cal.getTime();
		patient1 = new Patient();
		patient1.setPatientId(11L);
		patient1.setFamily("Smith");
		patient1.setGiven("John");
		patient1.setDob(date1);
		patient1.setAddress("123 Main St");
		patient1.setSex("M");
		patient1.setPhone("1234567890");

//    	Calendar cal2 = Calendar.getInstance();
//		cal2.add(Calendar.YEAR, -60); // subtract 50 years
//		Date date2 = (Date) cal.getTime();
		patient2 = new Patient();
		patient2.setPatientId(12L);
		patient2.setFamily("Jill");
		patient2.setGiven("White");
		patient2.setDob(date1);
		patient2.setAddress("123 Addison St");
		patient2.setSex("F");
		patient2.setPhone("1234888890");

	}

	@Test
	void testGetPatients() {

		// mock findAll() properly
		when(patientReopsitory.findAll()).thenReturn(List.of(patient1, patient2));

		List<Patient> result = patientService.getPatients();

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result).containsExactlyInAnyOrder(patient1, patient2);

		// verify repo interaction
		verify(patientReopsitory, times(1)).findAll();
	}

//	@Test
	void testGetPatient() {
		when(patientReopsitory.findByGivenName("John")).thenReturn(patient1);
		
		List<Patient> result = patientService.getPatient("John");

		assertThat(result).isNotNull();
		assertThat(result.get(0).getGiven()).isEqualTo("John");
	}

	@Test
	void testGetPatientById() {
		when(patientReopsitory.findByPatientId(12L)).thenReturn(patient2);

		Patient result = patientService.getPatientById(12L);

		assertThat(result).isNotNull();
		assertThat(result.getFamily()).isEqualTo("Jill");
	}

	@Test
	void testGetPatientNotFoundById() {
		when(patientReopsitory.findByPatientId(12L)).thenReturn(null);

		// Expect exception (e.g., UserNotFoundException)
		assertThrows(UserNotFoundException.class, () -> {
			patientService.getPatientById(12L);
		});
	}

	@Test
	void testGetPatientNotFoundByGivenName() {
		when(patientReopsitory.findByGivenName("John")).thenReturn(null);

		// Expect exception (e.g., UserNotFoundException)
		assertThrows(UserNotFoundException.class, () -> {
			patientService.getPatient("John");
		});
	}

	@Test
	void testSavePatient() {
		Date date1 = new Date(System.currentTimeMillis());
		patient2 = new Patient();
		patient2.setPatientId(14L);
		patient2.setFamily("Jill");
		patient2.setGiven("Brown");
		patient2.setDob(date1);
		patient2.setAddress("123 Addison St");
		patient2.setSex("F");
		patient2.setPhone("1234888890");

		when(patientReopsitory.save(patient2)).thenReturn(patient2);

		Patient saved = patientService.savePatient(patient2);

		assertThat(saved).isNotNull();
		assertThat(saved.getGiven()).isEqualTo("Brown");
		assertThat(saved.getFamily()).isEqualTo("Jill");
	}

	@Test
	void testUpdatePatient() {

//		Existing patient in DB
		Patient existing = new Patient();
		existing.setPatientId(12L);
		existing.setFamily("OldFamily");
		existing.setGiven("OldGiven");
		existing.setPhone("1111111111");

		// New details to update
		Patient updates = new Patient();
		updates.setFamily("NewFamily");
		updates.setGiven("NewGiven");
		updates.setDob(new Date(1977 / 05 / 05));
		updates.setPhone("2222222222");
		updates.setAddress("123 Test St");

		// Mock repository behavior
		when(patientReopsitory.findById(12L)).thenReturn(Optional.of(existing));
		when(patientReopsitory.save(any(Patient.class))).thenReturn(existing);

		// Act
		Patient result = patientService.updatePatient(12L, updates);

		// Assert
		assertThat(result.getFamily()).isEqualTo("NewFamily");
		assertThat(result.getGiven()).isEqualTo("NewGiven");
		assertThat(result.getPhone()).isEqualTo("2222222222");
		assertThat(result.getAddress()).isEqualTo("123 Test St");

		// Verify save() was called once
		verify(patientReopsitory, times(1)).save(existing);

	}

	@Test
	void testUpdatePatientNotFound() {
		// Mock repository to return empty Optional
		when(patientReopsitory.findById(12L)).thenReturn(Optional.empty());

		// Act + Assert
		assertThrows(UserNotFoundException.class, () -> {
			patientService.updatePatient(12L, new Patient());
		});

		// Verify save() is never called
		verify(patientReopsitory, never()).save(any(Patient.class));
	}

}
