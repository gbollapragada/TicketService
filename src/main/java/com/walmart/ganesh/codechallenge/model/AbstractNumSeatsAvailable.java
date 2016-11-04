package com.walmart.ganesh.codechallenge.model;

/**
 * AbstractNumSeatsAvailable : This is the behavior object that implements
 * INumSeatsAvailable, which is used by ITicketService to find the seats that
 * are available in the venue The number of seats in the venue that are neither
 * held nor reserved. This behavior can be swapped out when rules change
 * 
 * @author gbollapragada
 */
public abstract class AbstractNumSeatsAvailable implements INumSeatsAvailable {
	/**
	 * @param ticketServiceInterface - Reference to the parent has-a ITicketService
	 */
	@Override
	public int numSeatsAvailable(ITicketService ticketServiceInterface) {
		return ticketServiceInterface.getVenue().getNumOfAvailableSeats();
	}

}
