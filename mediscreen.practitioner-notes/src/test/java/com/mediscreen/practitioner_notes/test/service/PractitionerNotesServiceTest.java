package com.mediscreen.practitioner_notes.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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
import com.mediscreen.practitioner_notes.Application;
import com.mediscreen.practitioner_notes.controller.PractitionerNotesController;
import com.mediscreen.practitioner_notes.exception.UserNotFoundException;
import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.repository.PractitionerNotesRepository;
import com.mediscreen.practitioner_notes.service.PractitionerNotesService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PractitionerNotesServiceTest {
	static ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private PractitionerNotesService practitionerNotesService;

	@MockitoBean
	private PractitionerNotesRepository practitionerNotesRepository;

	@Autowired
	private MockMvc mockMvc;

	private PractitionerNotes practitionerNotes1;
	private PractitionerNotes practitionerNotes2;
	private PractitionerNotes practitionerNotes3;

	@BeforeEach
	void setUp() throws Exception {
		practitionerNotes1 = new PractitionerNotes();
		practitionerNotes1.setId("001");
		practitionerNotes1.setPatientId(1L);
		practitionerNotes1.setNote(
				"Patient states that everything seems fine Lab reports Hemoglobin A1C above recommended level. Patient admits to being long term Smoker");

		practitionerNotes2 = new PractitionerNotes();
		practitionerNotes2.setId("002");
		practitionerNotes2.setPatientId(2L);
		practitionerNotes2.setNote("Lab reports Microalbumin elevated");

		practitionerNotes3 = new PractitionerNotes();
		practitionerNotes3.setId("003");
		practitionerNotes3.setPatientId(2L);
		practitionerNotes3.setNote("Patient states that they are experiencing back pain when seated for a long time");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMain() {
	   Application.main(new String[] {});
	}
	
	@Test
	void contextLoads() {
	}

	@Test
	void testGetAllNotes() {

		when(practitionerNotesRepository.findAll())
				.thenReturn(List.of(practitionerNotes1, practitionerNotes2, practitionerNotes3));

		List<PractitionerNotes> result = practitionerNotesService.getAllNotes();

		assertThat(result).isNotNull();
		assertThat(result).hasSize(3);
		assertThat(result).containsExactlyInAnyOrder(practitionerNotes1, practitionerNotes2, practitionerNotes3);

		verify(practitionerNotesRepository, times(1)).findAll();

	}

	@Test
	void testSavePatientHistory() {

		PractitionerNotes addNewNote = new PractitionerNotes();
		addNewNote.setPatientId(5L);
		addNewNote.setNote("Adding a new note to patient history");

		when(practitionerNotesRepository.save(addNewNote)).thenReturn(addNewNote);

		PractitionerNotes saved = practitionerNotesService.savePatientHistory(addNewNote);

		assertThat(saved).isNotNull();
		assertThat(saved.getPatientId()).isEqualTo(5L);
		assertThat(saved.getNote()).isEqualTo("Adding a new note to patient history");

	}

	@Test
	void testGetAllNotesByPatientId() {

		when(practitionerNotesRepository.findBypatientId(2L))
				.thenReturn(List.of(practitionerNotes2, practitionerNotes3));

		List<PractitionerNotes> result = practitionerNotesService.getAllNotesByPatientId(2L);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result).containsExactlyInAnyOrder(practitionerNotes2, practitionerNotes3);

		verify(practitionerNotesRepository, times(1)).findBypatientId(2L);
	}

	
	 @Test void testGetAllNotesByPatientId_WhenNoNotesFound() {
	 
	 Long patientId = 99L;
	 when(practitionerNotesRepository.findBypatientId(patientId)).thenReturn(List.
	 of());
	 
	 UserNotFoundException exception = assertThrows( UserNotFoundException.class,
	 () -> practitionerNotesService.getAllNotesByPatientId(patientId) );
	 
	 assertThat(exception.getMessage())
	 .isEqualTo("No PractitionerNotes found for Patient ID: " + patientId);
	 
	 verify(practitionerNotesRepository, times(1)).findBypatientId(patientId); }
	 

	@Test
	void testFindById_WhenRecordExists() {

		PractitionerNotes existingNote = new PractitionerNotes();
		existingNote.setId("123");
		existingNote.setNote("Old Note");

		PractitionerNotes updateRequest = new PractitionerNotes();
		updateRequest.setNote("Updated Note");

		when(practitionerNotesRepository.findById("123")).thenReturn(existingNote);
		when(practitionerNotesRepository.save(any(PractitionerNotes.class))).thenReturn(existingNote);

		PractitionerNotes result = practitionerNotesService.findById("123", updateRequest);

		assertThat(result).isNotNull();
		assertThat(result.getNote()).isEqualTo("Updated Note");
		verify(practitionerNotesRepository, times(1)).findById("123");
		verify(practitionerNotesRepository, times(1)).save(existingNote);
	}

	@Test
	void testFindById_WhenRecordDoesNotExist() {

		PractitionerNotes updateRequest = new PractitionerNotes();
		updateRequest.setNote("New Note");

		when(practitionerNotesRepository.findById("999")).thenReturn(null);

		assertThrows(UserNotFoundException.class, () -> {
			practitionerNotesService.findById("999", updateRequest);
		});

		verify(practitionerNotesRepository, times(1)).findById("999");
		verify(practitionerNotesRepository, never()).save(any());
	}

}
