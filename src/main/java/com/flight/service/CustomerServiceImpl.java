package com.flight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.flight.models.PassangerBean;
import com.flight.models.Reservation;
import com.flight.models.ScheduleBean;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, Date date) {
		return null;
	}

	@Override
	public String reserveTicket(Reservation reservationBean, ArrayList<PassangerBean> passengers) {
		return null;
	}

	@Override
	public boolean cancelTicket(String reservationId) {
		return false;
	}

	@Override
	public Map<Reservation, PassangerBean> viewTicket(String reservationId) {
		return null;
	}

	@Override
	public Map<Reservation, PassangerBean> printTicket(String reservationId) {
		return null;
	}

}
