package com.mediscreen.patient_demographics.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
//@Data
//@Getter
//@Setter
public class Patient {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long patient_id;

	   @Column(name = "familyname", nullable = true)
	   private String family;
	   
	   @Column(name = "givenname", nullable = false)
	   private String given;
	   
	   @Column(name = "dateofbirth", nullable = false)
	   private Date dob;
	   
	   @Column(name = "streetaddress", nullable = true)
	   private String address;
	   
	   @Column(name = "sex", nullable = false)
	   private String sex;
	   
	   @Column(name = "phone", nullable = true)
	   private String phone;

	public long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
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
}
