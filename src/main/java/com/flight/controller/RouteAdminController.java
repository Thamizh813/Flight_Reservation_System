package com.flight.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.exception.RouteNotFouundException;
import com.flight.models.RouteBean;
import com.flight.service.AdminService;

@RestController
@RequestMapping("/route-admin-controller")
public class RouteAdminController {

@Autowired
AdminService adser;

@PostMapping(value="/route/save",consumes = "application/json")
public String addroute(@RequestBody RouteBean route) {
	
	Random rand = new Random();
	int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
	route.setRouteID(route.getSource().substring(0,2)+ route.getDestination().substring(0,2) + Integer.toString(resRandom)); 
	adser.addRoute(route);
	 return "Added Successfully";
	
}
@GetMapping(value="/route/viewall")
public ArrayList<RouteBean> viewall(){
	if(adser.viewByAllRoute().isEmpty()) {
		System.out.println("No Route is available");
		throw new RouteNotFouundException();
	}
	return adser.viewByAllRoute();
}

@GetMapping(value="/route/viewById/{routeID}")
public RouteBean viewById(@PathVariable("routeID") String routeID ) {
	if(adser.viewByRouteId(routeID)==null) {
		System.out.println("No route is available");
		throw new RouteNotFouundException();
	}
	return adser.viewByRouteId(routeID);
}
@DeleteMapping(value="/route/delete/{routeID}")
public String deleteroute(@PathVariable("routeID") String routeID) {
	
		
	adser.removeRoute(routeID);
	return "Deleted Successfully";
	
	
}

@PutMapping(value="/route/update")
public String updateroute(@RequestBody RouteBean route) {
	adser.modifyRoute(route);
	
	return "Updated Successfully";
}
}