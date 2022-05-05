package com.flight.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="Schedule_details")

public class ScheduleBean {
	
	@Id	
	private String scheduleID;
	private int travelDuration;
	private String availableDays;
	private String departureTime;
	
	
	
	@ManyToOne
	@JoinColumn (name="flight_id")
	private FlightBean flight;
	
	
	
	@ManyToOne
    @JoinColumn(name="route_id")
    private RouteBean route;
	
	
	@OneToMany(mappedBy = "scheduleid",orphanRemoval = true)
	@JsonIgnore
	private List<Reservation> reservation;

	 
}
