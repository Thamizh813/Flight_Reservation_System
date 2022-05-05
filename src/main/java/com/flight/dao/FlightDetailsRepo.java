package com.flight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.FlightBean;

public interface FlightDetailsRepo extends JpaRepository<FlightBean, String> {

	FlightBean findByFlightID(String flightID);
	
}
