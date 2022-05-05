package com.flight.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="creditcard_details")
public class CreditCard {
	@Id
	private String creditCardNumber;
	private String validTo;
	private String validFrom;
	private String creditBalance;
	

	@ManyToOne
	@JoinColumn (name="user_id")
	private Credentialsbean credit;
	
	
}
