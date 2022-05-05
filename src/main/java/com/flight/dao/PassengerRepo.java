package com.flight.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.PassangerBean;
import com.flight.models.Reservation;

public interface PassengerRepo extends JpaRepository<PassangerBean, Long>{
	
	ArrayList<PassangerBean> findByReserveid(Reservation reserveid);

}
