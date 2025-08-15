package com.mediscreen.practitioner_notes.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.practitioner_notes.model.PractitionerNotes;

@Repository
public interface PractitionerNotesRepository extends MongoRepository<PractitionerNotes,Long> {

	List<PractitionerNotes> findBypatientId(Long patientId);
}
