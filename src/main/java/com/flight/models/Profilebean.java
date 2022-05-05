package com.flight.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="profile_details")
@NoArgsConstructor
public class Profilebean {



	@Id
	@GeneratedValue
	private String userID;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private String street;
	private String location;	
	private String city;
	private String state;
	private String pincode;
	@Size(max=10)
	private String mobileNo;
	private String emailID;	
	private String password;
	
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Credentialsbean cred;
	
	
}
