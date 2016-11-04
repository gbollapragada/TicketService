package com.walmart.ganesh.codechallenge.model;

/**
 * AbstractHold: This holds the temporary booking on the seat
 * 
 * @author gbollapragada
 */
public abstract class AbstractHold extends AbstractBooking {

	/**
	 * AbstractBooking holds reference to the user and the seat that this
	 * booking refers to
	 * 
	 * @param seat
	 *            - Seat that this booking refers to
	 * @param owner
	 *            - email id of the owner
	 */
	AbstractHold(ISeat seat, String owner) {
		super(seat, owner);
	}

}
