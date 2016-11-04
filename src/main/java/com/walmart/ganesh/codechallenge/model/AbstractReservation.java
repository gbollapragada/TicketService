package com.walmart.ganesh.codechallenge.model;

/**
 * AbstractReservation: This holds the permanent reservation on the seat
 * 
 * @author gbollapragada
 *
 */
public abstract class AbstractReservation extends AbstractBooking {

	/**
	 * AbstractBooking holds reference to the user and the seat that this
	 * booking refers to
	 * 
	 * @param seat
	 *            - Seat that this booking refers to
	 * @param owner
	 *            - email id of the owner
	 */
	AbstractReservation(ISeat seat, String owner) {
		super(seat, owner);
	}

}
