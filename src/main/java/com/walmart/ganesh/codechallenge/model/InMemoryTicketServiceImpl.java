package com.walmart.ganesh.codechallenge.model;

/**
 * InMemoryTicketServiceImpl : In-Memory implementation of ITicketService
 * 
 * @author gbollapragada
 *
 */
public class InMemoryTicketServiceImpl extends AbstractTicketService {
	public InMemoryTicketServiceImpl(IFindAndHoldSeats findAndHoldSeats, IReserveSeat reserveSeat,
			INumSeatsAvailable numSeatsAvailable, IVenue venue) {
		super(findAndHoldSeats, reserveSeat, numSeatsAvailable, venue);
	}

}
