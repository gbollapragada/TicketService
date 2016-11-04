package com.walmart.ganesh.codechallenge.model;

import java.util.List;

/**
 * ITicketService : Is used to manage all FindAndHold, numSeats and Reserve
 * requests, Operates on a venue container using the VenuInterface
 * 
 * @author gbollapragada Implements the API to access the Ticketing service,
 * 
 */
public interface ITicketService {
	/**
	 * The number of seats in the venue that are neither held nor reserved
	 *
	 * @return the number of tickets available in the venue
	 */
	int numSeatsAvailable();

	/**
	 * Find and hold the best available seats for a customer
	 *
	 * @param numSeats
	 *            - the number of seats to find and hold
	 * @param customerEmail
	 *            - unique identifier for the customer
	 * @return a SeatHoldInterface interface identifying the object identifying
	 *         the specific seats and related information
	 * @throws SeatsNotAvailableException
	 *             - if the requested seats are not available
	 */
	List<ISeatHold> findAndHoldSeats(int numSeats, String customerEmail) throws SeatsNotAvailableException;

	/**
	 * Commit seats held for a specific customer
	 *
	 * @param seatHoldId
	 *            - the seat hold identifier
	 * @param customerEmail
	 *            - the email address of the customer to which the seat hold is
	 *            assigned
	 * @return a reservation confirmation code
	 * @throws SeatsNotAvailableException
	 *             - - if the requested seats are not available
	 * @throws BookingNotAvailableException
	 *             - if the requested hold is not valid or not available
	 */
	String reserveSeats(String seatHoldId, String customerEmail) throws BookingNotAvailableException;

	/**
	 * Get Venue object
	 * 
	 * @return the venue object
	 */
	IVenue getVenue();

	/**
	 * Commit seats held for a specific customer
	 *
	 * @param seatHoldId
	 *            - the seat hold identifier
	 * @param customerEmail
	 *            - the email address of the customer to which the seat hold is
	 *            assigned
	 * @return a reservation confirmation code
	 * @throws BookingNotAvailableException
	 *             - if the requested hold is not valid or not available
	 */
	IBooking getSeatonHold(String seatHoldId, String customerEmail) throws BookingNotAvailableException;

}