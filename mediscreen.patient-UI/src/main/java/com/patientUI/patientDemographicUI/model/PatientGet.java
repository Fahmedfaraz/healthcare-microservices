package com.patientUI.patientDemographicUI.model;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

public class PatientGet {

	   private Long patientId;

	   private String family;
	   
	   private String given;
	   
	   private Date dob;
	   
//	   private Date dob;
	   
	   private String address;
	   
	   private String sex;
	   
	   private String phone;
	   
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
		

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// Method to convert your LocalDate to Date
    @Transient
    public String formattedDOB() {
        // LocalDate localDate = get value of localDate from Optional<LocalDate> dob;

        // Convert LocalDate to Date Object
        ZoneId zoneId = ZoneId.systemDefault();
        
        ZonedDateTime zonedDateTime = dob.toInstant().atZone(zoneId);
        
        Date dob1 = Date.from(dob.toInstant());

        LocalDate localDate = zonedDateTime.toLocalDate();

        
        // Formatting the date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(localDate);
    
        return formattedDate ;
    }
}
