package com.flight.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.ScheduleBean;

public interface ScheduleFlightsRepo extends JpaRepository<ScheduleBean, String> {
   ScheduleBean findByScheduleID(String scheduleID);
   	public List<ScheduleBean> findByRouteNotNull();
   
   
}
