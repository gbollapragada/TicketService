package com.walmart.ganesh.codechallenge.model;

/**
 * IReserveSeat : This is the behavior API that to be implemented, which is used
 * by ITicketService to reserve a seat in the venue if available. This can be
 * swapped out for more optimized versions or when rules change
 * 
 * @author gbollapragada
 */
public interface IReserveSeat {
	public String reserveSeats(String seatHoldId, String customerEmail, ITicketService ticketServiceInterface)
			throws BookingNotAvailableException;
}
