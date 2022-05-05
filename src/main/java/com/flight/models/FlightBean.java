package com.flight.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="flight_details")
public class FlightBean{

	
		@Id
		private String flightID;
		private String flightName;
		private int seatingCapacity;
		private int reservationCapacity;
		
		@OneToMany(mappedBy = "flight", orphanRemoval = true)
		@JsonIgnore
		private List<ScheduleBean> schdlflights;
	
	
}
