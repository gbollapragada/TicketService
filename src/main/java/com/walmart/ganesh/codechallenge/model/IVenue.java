package com.walmart.ganesh.codechallenge.model;

import java.util.List;

/**
 * IVenue : Venue interface is the public API for the Venu container
 * 
 * @author gbollapragada
 * 
 */
public interface IVenue {

	/**
	 * Returns Holds and Reservations
	 * 
	 * @return
	 */
	public List<IBooking> getBookings();

	/**
	 * Return Holds
	 * 
	 * @return
	 */
	public List<IBooking> getHolds();

	/**
	 * Return Reservations
	 * 
	 * @return
	 */
	public List<IBooking> getReservations();

	/**
	 * Returns number of seats that are available to book
	 * 
	 * @return
	 */
	public int getNumOfAvailableSeats();

	/**
	 * Returns the open seats
	 * 
	 * @return
	 */
	public List<ISeat> getOpenSeats();

}
