package com.flight.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.exception.FlightNotFoundException;
import com.flight.models.FlightBean;
import com.flight.service.AdminService;

@RestController
@RequestMapping("/flight-admin-controller")
public class FlightAdminController {

@Autowired
AdminService adser;

@PostMapping(value="/flight/save",consumes = "application/json")
public String addflight(@RequestBody FlightBean flight) {
	
	Random rand = new Random();
	int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
	flight.setFlightID(flight.getFlightName().substring(0,2) + Integer.toString(resRandom)); 
	adser.addFlight(flight);
	 return "Added Successfully";
	
}
@GetMapping(value="/flight/viewall")
public ArrayList<FlightBean> viewall(){
	if(adser.viewByAllFlights().isEmpty()) {
		System.out.println("No flight is available");
		throw new FlightNotFoundException();
	}
	return adser.viewByAllFlights();
}

@GetMapping(value="/flight/viewById/{flightID}")
public FlightBean viewById(@PathVariable("flightID") String flightID ) {
	if(adser.viewByFlightId(flightID)==null) {
		System.out.println("No flight is available");
		throw new FlightNotFoundException();
	}
	return adser.viewByFlightId(flightID);
}
@DeleteMapping(value="/flight/delete/{flightID}")
public String deleteflight(@PathVariable("flightID") String flightID) {
	
		
	adser.removeFlight(flightID);	
	
	return "Deleted Successfully";
	
	
}

@PutMapping(value="/flight/update")
public FlightBean updateFlight(@RequestBody FlightBean flight) {
	return adser.modifyFlight(flight);
}
}

