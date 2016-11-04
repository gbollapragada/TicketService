package com.walmart.ganesh.codechallenge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractFindAndHoldSeats : This is the behavior object that implements
 * IFindAndHoldSeats, which is used by ITicketService to find and hold the seats
 * in the venue if available. This can be swapped out for more optimized
 * versions or when rules change
 * 
 * @author gbollapragada
 */
public abstract class AbstractFindAndHoldSeats implements IFindAndHoldSeats {

	/**
	 * Find and hold the seats for a customer
	 *
	 * @param numSeats
	 *            - the number of seats to find and hold
	 * @param customerEmail
	 *            - unique identifier for the customer
	 * @param ticketServiceInterface
	 *            - Reference to the parent has-a ITicketService
	 * @return List<ISeatHold> - A list of hold objects that hold a reservatio
	 *         on the seat and the specific seats and related information
	 * @throws SeatsNotAvailableException
	 *             - if the requested seats are not available
	 */
	@Override
	public List<ISeatHold> findAndHoldSeats(int numSeats, String customerEmail, ITicketService ticketServiceInterface)
			throws SeatsNotAvailableException {

		// Checks if there are any seats
		if (ticketServiceInterface.numSeatsAvailable() < numSeats) {
			throw new SeatsNotAvailableException("Venu is either full or requested seats are not available.");
		} else if (numSeats <= 0) {
			throw new SeatsNotAvailableException("Cannot make requests lesser that 1.");
		} else {
			// Get the venue from TicketServiceInterface and search for best
			// available seats
			// This class cane be extended to perform various types of searches
			com.walmart.ganesh.codechallenge.model.IVenue venue = ticketServiceInterface.getVenue();
			List<ISeat> openSeats = venue.getOpenSeats();
			List<ISeatHold> seatHolds = new ArrayList<ISeatHold>();
			for (int i = 0; i <= numSeats - 1; i++) {
				// Connect seat to booking and booking to seat
				IBooking hold = new InMemoryHoldImpl(openSeats.get(i), customerEmail);
				openSeats.get(i).setBooking(hold);
				ISeatHold seatHoldID = new InMemorySeatHoldImpl(openSeats.get(i));
				openSeats.get(i).setSeatHold(seatHoldID);
				seatHolds.add(openSeats.get(i).getSeatHold());
			}
			return seatHolds;
		}
	}

}
