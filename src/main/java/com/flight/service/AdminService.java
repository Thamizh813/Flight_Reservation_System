package com.flight.service;

import java.util.ArrayList;

import com.flight.models.FlightBean;
import com.flight.models.PassangerBean;
import com.flight.models.RouteBean;
import com.flight.models.ScheduleBean;


public interface AdminService {

	FlightBean addFlight(FlightBean flightBean);
	FlightBean modifyFlight(FlightBean flightBean);
	void removeFlight(String flightID);
	ArrayList<FlightBean> viewByAllFlights();
	FlightBean viewByFlightId(String flightId);
	
	RouteBean viewByRouteId(String routeID);
	ArrayList<RouteBean> viewByAllRoute();
	RouteBean addRoute(RouteBean routeBean);
	RouteBean modifyRoute(RouteBean routeBean);
	void removeRoute(String routeID);
	
	ArrayList<ScheduleBean> viewBySchedule();
	ScheduleBean viewByScheduleId(String scheduleId);
	ArrayList<PassangerBean>viewPassengersByFlight(String scheduleId);
	ScheduleBean addSchedule(ScheduleBean scheduleBean);
	boolean modifySchedule(ScheduleBean scheduleBean);
	void removeSchedule(String scheduleId);

	
	
	
}
	

	
