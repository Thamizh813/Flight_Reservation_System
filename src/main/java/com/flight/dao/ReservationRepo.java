package com.flight.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.Reservation;
import com.flight.models.ScheduleBean;

public interface ReservationRepo extends JpaRepository<Reservation, String> {

ArrayList<Reservation> findByScheduleid(ScheduleBean scheduleid);
	public List<Reservation> findByScheduleidNotNull();
}
