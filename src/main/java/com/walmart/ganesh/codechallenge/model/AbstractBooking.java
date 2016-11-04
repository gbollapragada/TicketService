package com.walmart.ganesh.codechallenge.model;

/**
 * AbstractBooking : This is Booking state object, should be accessed using API BookingInterface
 * 					 There are two types of AbstractBooking AbstractHold, and AbstractReservation
 * @author gbollapragada
 */
public class AbstractBooking implements IBooking {

	// Holds seat reference and owner details
	private final ISeat seat;
	private final String owner;
	
	/**
	 * Holds reference to the user and the seat that this booking refers to
	 * 
	 * @param seat - Seat that this booking refers to
	 * @param owner - email id of the owner
	 */
	AbstractBooking(ISeat seat, String owner){
		this.seat = seat;
		this.owner = owner;
	}

	@Override
	public String getOwner() {
		return this.owner;
	}

	@Override
	public void release() {
		seat.releaseHold();
	}

	@Override
	public ISeat getSeat() {
		return seat;
	}

}
