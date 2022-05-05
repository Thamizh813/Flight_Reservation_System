package com.flight.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
@Table(name="reservation_details")
public class Reservation {
	@Id
	private String reservationid;
	private Date bookingdate;
	private Date journeydate;
	private Integer noofseats;
	private Integer totalfare;
	private int bookingStatus;
	
	
	
	@OneToOne
	@JoinColumn (name="user_id")
	@JsonIgnore
	private Credentialsbean reserve;
	
	@ManyToOne
	@JoinColumn (name="schedule_id")
	@JsonIgnore
	private ScheduleBean scheduleid;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "reserveid",orphanRemoval = true)
	private List<PassangerBean> passanger;
	
	

}
