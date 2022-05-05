package com.flight.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dao.FlightDetailsRepo;
import com.flight.dao.PassengerRepo;
import com.flight.dao.ReservationRepo;
import com.flight.dao.RouteDetailsRepo;
import com.flight.dao.ScheduleFlightsRepo;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.NoReservationFoundException;
import com.flight.exception.NoScheduleFoundException;
import com.flight.exception.PassengersListcancel;
import com.flight.exception.RouteNotFouundException;
import com.flight.exception.RoutesDeletedScheduleNull;
import com.flight.models.FlightBean;
import com.flight.models.PassangerBean;
import com.flight.models.Reservation;
import com.flight.models.RouteBean;
import com.flight.models.ScheduleBean;

@Service
public class AdminSericeImpl implements AdminService{
	
	@Autowired
	private FlightDetailsRepo flr;
	
	@Autowired
	private RouteDetailsRepo rdr;
	
	@Autowired
	private ScheduleFlightsRepo scfr;
	
	@Autowired
	private ReservationRepo reserve;
	
	@Autowired
	private PassengerRepo pass;

	@Override
	public FlightBean addFlight(FlightBean flightBean) {
		
		return flr.save(flightBean);
	}

	@Override
	public FlightBean modifyFlight(FlightBean flightBean) {
		
		FlightBean f = flr.findByFlightID(flightBean.getFlightID());
		f.setFlightID(flightBean.getFlightID());
		f.setFlightName(flightBean.getFlightName());
		f.setReservationCapacity(flightBean.getReservationCapacity());
		f.setSeatingCapacity(flightBean.getSeatingCapacity());
		return flr.save(f);
		
	}

	@Override
	public void removeFlight(String flightID) {
		
		if(flr.existsById(flightID)){
		   List<ScheduleBean> s= scfr.findAll();
		   for(ScheduleBean sb:s) {
			   if(sb.getFlight().getFlightID().equals(flightID)) {
				   List<Reservation>r = reserve.findByScheduleidNotNull();
				   for(Reservation res:r) {
					   if(res.getScheduleid().getScheduleID().equals(sb.getScheduleID())) {
						   res.setBookingStatus(0);
						   res.setScheduleid(null);
						   reserve.save(res);
						   scfr.save(sb);
						   flr.deleteById(flightID);
						   throw new PassengersListcancel();
					   }
					   
				   }
			   }
			   
		   }
		
		
		}
	    if(flr.existsById(flightID)) {
			   flr.deleteById(flightID);

		}
		else {
			throw new FlightNotFoundException();
			
		}
		
		

	}

	@Override
	public ArrayList<FlightBean> viewByAllFlights() {
		return (ArrayList<FlightBean>) flr.findAll();
	}

	@Override
	public FlightBean viewByFlightId(String flightId) {
		return flr.findByFlightID(flightId);
	}

	@Override
	public RouteBean viewByRouteId(String routeID) {
		
		return rdr.findByRouteID(routeID);
	}

	@Override
	public ArrayList<RouteBean> viewByAllRoute() {
		return (ArrayList<RouteBean>) rdr.findAll();
	}

	@Override
	public RouteBean addRoute(RouteBean routeBean) {
		return rdr.save(routeBean);
		
	}

	@Override
	public RouteBean modifyRoute(RouteBean routeBean) {
		RouteBean r = rdr.findByRouteID(routeBean.getRouteID());
		r.setRouteID(routeBean.getRouteID());
		r.setDestination(routeBean.getDestination());
		r.setDistance(routeBean.getDistance());
		r.setFare(routeBean.getFare());
		r.setSource(routeBean.getSource());
		
	   return rdr.save(r);
		
	}

	@Override
	public void removeRoute(String routeID) {
		if(rdr.existsById(routeID)) {
			List<ScheduleBean> s= scfr.findByRouteNotNull();
			   for(ScheduleBean sb:s) {
				   if(sb.getRoute().getRouteID().equals(routeID)) {
					  sb.setRoute(null);
					  scfr.save(sb);
					  rdr.deleteById(routeID);
					  throw new RoutesDeletedScheduleNull();

						   }
						   
					   }
				   }
			   if(rdr.existsById(routeID)) {
		rdr.deleteById(routeID);
		
	}
		else {
			throw new RouteNotFouundException();
			}
		}

	@Override
	public ArrayList<ScheduleBean> viewBySchedule() {
		return (ArrayList<ScheduleBean>) scfr.findAll();
	}

	@Override
	public ScheduleBean viewByScheduleId(String scheduleId) {
		return scfr.findByScheduleID(scheduleId);
	}

	@Override
	public ScheduleBean addSchedule(ScheduleBean scheduleBean) {
		
		return  scfr.save(scheduleBean);

	}

	@Override
	public boolean modifySchedule(ScheduleBean scheduleBean) {
		
		ScheduleBean r = scfr.findByScheduleID(scheduleBean.getScheduleID());
		r.setAvailableDays(scheduleBean.getAvailableDays());
		r.setDepartureTime(scheduleBean.getDepartureTime());
		r.setTravelDuration(scheduleBean.getTravelDuration());
		r.setFlight(scheduleBean.getFlight());
		r.setRoute(scheduleBean.getRoute());
		scfr.save(r);
		return true;
	}

	@Override
	public void removeSchedule(String scheduleId) {
		if(scfr.existsById(scheduleId)) {
			List<Reservation> res = reserve.findByScheduleidNotNull();
			for(Reservation r:res) {
				if(r.getScheduleid().getScheduleID().equals(scheduleId)) {
					r.setBookingStatus(0);
					r.setScheduleid(null);
					reserve.save(r);
					scfr.deleteById(scheduleId);
				}
			}
		}
			if(scfr.existsById(scheduleId)) {
		scfr.deleteById(scheduleId);
				}
		
		else {
			throw new NoScheduleFoundException();
		}
	}

	@Override
	public ArrayList<PassangerBean> viewPassengersByFlight(String scheduleId) {
		if(scfr.existsById(scheduleId)) {
		List<ScheduleBean> list= scfr.findAll();
		ArrayList<PassangerBean> p = new ArrayList<PassangerBean>();
		for(ScheduleBean l:list) {
			if(l.getScheduleID().equals(scheduleId)) {
				if(reserve.findByScheduleid(l).isEmpty()) {
					throw new NoReservationFoundException();
				}
				else {
				List<Reservation>res=reserve.findByScheduleid(l);
				for(Reservation r:res) {
					p = pass.findByReserveid(r);
				}
				}
			}
		}
		return p;
		}
		else {
			throw new NoScheduleFoundException();
		}
	}

}
