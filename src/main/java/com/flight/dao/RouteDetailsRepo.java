package com.flight.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.models.RouteBean;

public interface RouteDetailsRepo extends JpaRepository<RouteBean, String> {
 RouteBean findByRouteID(String routeID);

}
