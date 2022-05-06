package com.flight.service;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.flight.models.FlightBean;
import com.flight.models.PassangerBean;
import com.flight.models.RouteBean;
import com.flight.models.ScheduleBean;

@FeignClient(url = "http://flightreservationsystem-env.eba-impptehs.us-west-2.elasticbeanstalk.com/", name = "FlightApp")
public interface AdminService {
	
	@PostMapping("/flight/save")
	FlightBean addFlight(FlightBean flightBean);
	
	@PutMapping("/flight/update")
	FlightBean modifyFlight(FlightBean flightBean);
	
	@DeleteMapping("/flight/delete/{flightID}")
	void removeFlight(String flightID);
	
	@GetMapping("/flight/viewall")
	ArrayList<FlightBean> viewByAllFlights();
	
	@GetMapping("/flight/viewById/{flightID}")
	FlightBean viewByFlightId(String flightId);
	
	@GetMapping("/route/viewById/{routeID}")
	RouteBean viewByRouteId(String routeID);
	
	@GetMapping("/route/viewall")
	ArrayList<RouteBean> viewByAllRoute();
	
	@PostMapping(value="/route/save")
	RouteBean addRoute(RouteBean routeBean);
	
	@PutMapping("/route/update")
	RouteBean modifyRoute(RouteBean routeBean);
	
	@DeleteMapping("/route/delete/{routeID}")
	void removeRoute(String routeID);
	
	
	@GetMapping("/schedule/viewall")
	ArrayList<ScheduleBean> viewBySchedule();
	
	@GetMapping("/schedule/viewById/{scheduleID}")
	ScheduleBean viewByScheduleId(String scheduleId);

	@GetMapping("/get/passanger/{scheduleID}")
	ArrayList<PassangerBean>viewPassengersByFlight(String scheduleId);
	
	@PostMapping("/schedule/save")
	ScheduleBean addSchedule(ScheduleBean scheduleBean);
	
	@PutMapping("/update/schedule")
	boolean modifySchedule(ScheduleBean scheduleBean);
	
	@DeleteMapping("/schedule/delete/{scheduleID}")
	void removeSchedule(String scheduleId);

	
	
	
}
	

	
