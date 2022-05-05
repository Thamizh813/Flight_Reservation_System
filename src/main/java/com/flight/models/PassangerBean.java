package com.flight.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="passenger_details")
public class PassangerBean {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private String age;
	private String seatNo;
	
	
	
	@ManyToOne
	@JoinColumn (name="reserveation_id")
	private Reservation reserveid;
	
	
}
