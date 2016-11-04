package com.walmart.ganesh.codechallenge.model;

import java.util.List;

/**
 * IFindAndHoldSeats : This is the behavior API that is to be implemented, which
 * is used by ITicketService to find and hold the seats in the venue if
 * available.
 * 
 * @author gbollapragada
 */
public interface IFindAndHoldSeats {
	/**
	 * Find and hold the seats for a customer
	 *
	 * @param numSeats
	 *            the number of seats to find and hold
	 * @param customerEmail
	 *            unique identifier for the customer
	 * @return List<ISeatHold> A list of hold objects that hold a reservatio on
	 *         the seat and the specific seats and related information
	 * @throws SeatsNotAvailableException
	 *             if the requested seats are not available
	 */
	public List<ISeatHold> findAndHoldSeats(int numSeats, String customerEmail, ITicketService ticketServiceInterface)
			throws SeatsNotAvailableException;
}
