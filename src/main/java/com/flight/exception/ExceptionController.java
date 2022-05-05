package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(value = FlightNotFoundException.class)
	public ResponseEntity<Object> exception(FlightNotFoundException exception) {

	   return new ResponseEntity<Object>("Flight not found", HttpStatus.NOT_FOUND);

	  }
	
	@ExceptionHandler(value = RouteNotFouundException.class)
	public ResponseEntity<Object> exception1(RouteNotFouundException exception){
		return new ResponseEntity<Object>("Route not found", HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value= NoFlightOrRouteFound.class)
	public ResponseEntity<Object> exeception2(NoFlightOrRouteFound exception){
		return new ResponseEntity<Object>("No Route Or Flight Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= NoScheduleFoundException.class)
	public ResponseEntity<Object> exeception3(NoScheduleFoundException exception){
		return new ResponseEntity<Object>("No Schedule Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= NoReservationFoundException.class)
	public ResponseEntity<Object> exeception4(NoReservationFoundException exception){
		return new ResponseEntity<Object>("No Reservation Found", HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(value= PassengersListcancel.class)
	public ResponseEntity<Object> exeception5(PassengersListcancel exception){
		return new ResponseEntity<Object>("Flights deleted and Corresponding schedules are deleted,  Reservations corresponding to that flight has been cancelled", HttpStatus.OK);
	}
	
	@ExceptionHandler(value= RoutesDeletedScheduleNull.class)
	public ResponseEntity<Object> exeception6(RoutesDeletedScheduleNull exception){
		return new ResponseEntity<Object>("Routes deleted and Corresponding schedules are becomes Null, we have update the route for schedule", HttpStatus.OK);
	}

}
