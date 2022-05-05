package com.flight.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dao.FlightDetailsRepo;
import com.flight.dao.RouteDetailsRepo;
import com.flight.exception.NoFlightOrRouteFound;
import com.flight.exception.NoScheduleFoundException;
import com.flight.models.FlightBean;
import com.flight.models.PassangerBean;
import com.flight.models.RouteBean;
import com.flight.models.ScheduleBean;
import com.flight.service.AdminService;

@RestController
public class ScheduleAdminController {

	
@Autowired
AdminService adms;

@Autowired
FlightDetailsRepo fl;

@Autowired
RouteDetailsRepo rpo;

@GetMapping(value="/schedule/viewall",produces = "application/json")
public ArrayList<ScheduleBean> viewallSchedule() {
	if(adms.viewBySchedule().isEmpty()) {
		throw new NoScheduleFoundException();
	}
	return adms.viewBySchedule();
	
}

@PostMapping(consumes = "application/json",value="/schedule/save")
public String addSchedule(@RequestBody ScheduleBean schedulbean) {

	List<FlightBean> fdetails = fl.findAll();
	List<RouteBean> rddetails = rpo.findAll();

	for(FlightBean fd: fdetails) {
		for(RouteBean rd: rddetails) {
		if(fd.getFlightID().equals(schedulbean.getFlight().getFlightID())&&rd.getRouteID().equals(schedulbean.getRoute().getRouteID())) {
			schedulbean.getFlight().setFlightID(fd.getFlightID());
			schedulbean.getRoute().setRouteID(rd.getRouteID());
			Random rand = new Random();
			int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
			schedulbean.setScheduleID(schedulbean.getRoute().getSource().substring(0,2) +schedulbean.getRoute().getDestination().substring(0,2)+ Integer.toString(resRandom)); 
			adms.addSchedule(schedulbean);
			return "Added succesfully";
			 
		}	
	}
		
	}
	throw new NoFlightOrRouteFound();
	
}

@GetMapping(value="/schedule/viewById/{scheduleID}")
public ScheduleBean viewById(@PathVariable("scheduleID") String scheduleID ) {
	
	if(adms.viewByScheduleId(scheduleID)==null) {
		throw new NoScheduleFoundException();
	}
	return adms.viewByScheduleId(scheduleID);
	
}

@DeleteMapping(value="/schedule/delete/{scheduleID}")
public String deleteSchdId(@PathVariable("scheduleID") String scheduleID) {
	adms.removeSchedule(scheduleID);
	return "Schedule Deleted Successfully, Corresponding Reservations are cancelled";
	
}

@PutMapping(value="/update/schedule")
public String upadteSchedule(@RequestBody ScheduleBean schedulebean) {
	adms.modifySchedule(schedulebean);
	return "Updated Successfully";
}

@GetMapping(value="/get/passanger/{scheduleID}")
public ArrayList<PassangerBean> getpass(@PathVariable("scheduleID") String scheduleID){
	return adms.viewPassengersByFlight(scheduleID);
	
}

}
