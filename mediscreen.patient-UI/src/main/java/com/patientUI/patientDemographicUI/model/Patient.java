package com.patientUI.patientDemographicUI.model;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Patient {
	private Long patientId;
    private String family;
    private String given;
    
    public Patient() {
    	
    }
    
//    @JsonFormat(pattern = "yyyy-MM-ddThh:mm:ss.SSSX")
//    2025-09-01T00:00:00.000+00:00
    private LocalDate dob;
//    private Date dob;
    private String sex;
    private String phone;
    private String email;
    private String address;
    
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
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

	
	public LocalDate getDob() { return dob; } 
	
	public void setDob(LocalDate dob) {
	this.dob = dob; 
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public Date getDob() {
//		return dob;
//	}
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	// Method to convert your LocalDate to Date
    @Transient
    public String formattedDOB() {
        // LocalDate localDate = get value of localDate from Optional<LocalDate> dob;

        // Convert LocalDate to Date Object
        ZoneId zoneId = ZoneId.systemDefault();
        
        ZonedDateTime zonedDateTime = dob.atStartOfDay().atZone(zoneId);
        
//        Date dob1 = Date.from(dob.toInstant());

        LocalDate localDate = zonedDateTime.toLocalDate();

        
        // Formatting the date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(localDate);
    
        return formattedDate ;
    }
    
}
