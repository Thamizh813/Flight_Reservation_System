package com.flight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.flight.models.PassangerBean;
import com.flight.models.Reservation;
import com.flight.models.ScheduleBean;

public interface CustomerService {

	ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, Date date); 
	String reserveTicket(Reservation reservationBean, ArrayList<PassangerBean> passengers);
	boolean cancelTicket(String reservationId);
	Map<Reservation,PassangerBean> viewTicket(String reservationId) ;
	Map<Reservation,PassangerBean> printTicket(String reservationId);
}
