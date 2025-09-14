package com.patientUI.patientDemographicUI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.patientUI.patientDemographicUI.model.Patient;
import com.patientUI.patientDemographicUI.model.PatientGet;
@Service
public class PatientApiClient {
    private final RestTemplate restTemplate;

    @Value("${api.patient.base-url}")
    private String baseUrl;

    public PatientApiClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public PatientGet getPatient(Long id) {
    	PatientGet pget = restTemplate.getForObject(baseUrl + "/patient/" + id, PatientGet.class);
    	System.out.println("pget.dob" + pget.getDob() ); 
        return pget;
    }

    public Patient createPatient(Patient dto) {
//        return restTemplate.postForObject(baseUrl + "/patients", dto, Patient.class);
        return restTemplate.postForObject(baseUrl + "/patient/add", dto, Patient.class);
    }

    public void updatePatient(Long id, Patient dto) {
        restTemplate.put(baseUrl + "/patient?patientId=" + id, dto);
    }
    
    public List<Patient> getAllPatients() {
        return restTemplate.getForObject(baseUrl + "/patients" , List.class );
    }

}
