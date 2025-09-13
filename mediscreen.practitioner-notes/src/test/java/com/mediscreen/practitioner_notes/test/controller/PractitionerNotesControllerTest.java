package com.mediscreen.practitioner_notes.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
import com.mediscreen.practitioner_notes.Application;
import com.mediscreen.practitioner_notes.controller.PractitionerNotesController;
import com.mediscreen.practitioner_notes.model.PractitionerNotes;
import com.mediscreen.practitioner_notes.repository.PractitionerNotesRepository;
import com.mediscreen.practitioner_notes.service.PractitionerNotesService;

@SpringBootTest
@AutoConfigureMockMvc
class PractitionerNotesControllerTest {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	@InjectMocks
	private PractitionerNotesController practitionerNotesController;
	
	@MockitoBean
	private PractitionerNotesRepository practitionerNotesRepository;
	
	@MockitoBean
	private PractitionerNotesService practitionerNotesService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private PractitionerNotes practitionerNotes1;
	private PractitionerNotes practitionerNotes2;
	private PractitionerNotes practitionerNotes3;

	@BeforeEach
	void setUp() throws Exception {
		practitionerNotes1 =new PractitionerNotes();
		practitionerNotes1.setId("001");
		practitionerNotes1.setPatientId(1L);
		practitionerNotes1.setNote("Patient states that everything seems fine Lab reports Hemoglobin A1C above recommended level. Patient admits to being long term Smoker");
		
		practitionerNotes2 =new PractitionerNotes();
		practitionerNotes2.setId("002");
		practitionerNotes2.setPatientId(2L);
		practitionerNotes2.setNote("Lab reports Microalbumin elevated");
		
		practitionerNotes3 =new PractitionerNotes();
		practitionerNotes3.setId("003");
		practitionerNotes3.setPatientId(2L);
		practitionerNotes3.setNote("Patient states that they are experiencing back pain when seated for a long time");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
  
    
	@Test
	void testGetAllPatientsHistory() throws Exception {
		when(practitionerNotesService.getAllNotes()).thenReturn(List.of(practitionerNotes1,practitionerNotes2,practitionerNotes3));
		
		mockMvc.perform(get("/patHistory")).andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[0].id").value("001"))
        .andExpect(jsonPath("$[1].patientId").value(2))
        .andExpect(jsonPath("$[2].note").value("Patient states that they are experiencing back pain when seated for a long time"));
		
	}

	@Test
	void testAddPatientHistory() throws Exception {
		PractitionerNotes addPractitionerNotes = new PractitionerNotes();
		
		addPractitionerNotes.setPatientId(1L);
		addPractitionerNotes.setNote("Patient states that they are feeling terrific Weight at or below recommended level");
	
		mockMvc.perform(
		post("/patHistory/add").contentType("application/json").content(mapper.
		writeValueAsString(addPractitionerNotes))).andExpect(status().is2xxSuccessful());
				
	}
	
//	GetNotesByPatientId_Expect2Notes (for patient id 2)
	@Test
	void testGetNotesByPatientId_Expect2Notes() throws Exception {
		when(practitionerNotesService.getAllNotesByPatientId(2L)).thenReturn(List.of(practitionerNotes2,practitionerNotes3));
		
		mockMvc.perform(get("/patient/{patientId}",2L)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				
				.andExpect(jsonPath("$[0].id").value("002"))
			    .andExpect(jsonPath("$[0].patientId").value(2))
			    .andExpect(jsonPath("$[0].note").value("Lab reports Microalbumin elevated"))
				
				.andExpect(jsonPath("$[1].id").value("003"))
				.andExpect(jsonPath("$[1].patientId").value(2))
				.andExpect(jsonPath("$[1].note").value("Patient states that they are experiencing back pain when seated for a long time"));
		
	}
	
//	When no notes for a given Patient ID
	@Test
	void testGetNotesByPatientId_NoNotesForGivenID() throws Exception {
	    when(practitionerNotesService.getAllNotesByPatientId(99L)).thenReturn(List.of());

	    mockMvc.perform(get("/patient/{patientId}", 99L))
	           .andExpect(status().isOk())
	           .andExpect(jsonPath("$.length()").value(0));
	}
	
//Update note for the given id
	@Test
	void testUpdatePatientHistory() throws Exception {
		PractitionerNotes updatePractitionerNotes =new PractitionerNotes();
		updatePractitionerNotes.setNote("Lab reports Cholesterol LDL high");
		
		PractitionerNotes updatedResponse = new PractitionerNotes();
	    updatedResponse.setId("001");
	    updatedResponse.setPatientId(1L);
	    updatedResponse.setNote("Lab reports Cholesterol LDL high");

	    when(practitionerNotesService.findById(eq("001"), any(PractitionerNotes.class)))
	        .thenReturn(updatedResponse);

		
		ObjectMapper objectMapper = new ObjectMapper();
		String updatedPractitionerNotesJson = objectMapper.writeValueAsString(updatePractitionerNotes);

		mockMvc.perform(put("/patHistory?id=001").contentType(MediaType.APPLICATION_JSON).content(updatedPractitionerNotesJson))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value("001"))
        .andExpect(jsonPath("$.patientId").value(1))
        .andExpect(jsonPath("$.note").value("Lab reports Cholesterol LDL high"));
		

	
	}

}
