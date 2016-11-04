package com.walmart.ganesh.codechallenge.model;

import java.util.List;

/**
 * AbstractTicketService : Is used to manage all FindAndHold, numSeats and
 * Reserve requests, implements ITicketService. Operates on a venue container
 * using the VenuInterface
 * 
 * @author gbollapragada
 *
 */
public abstract class AbstractTicketService implements ITicketService {

	/**
	 * This is the Venue instance
	 */
	private final IVenue venue;

	/**
	 * These are the behavior objects to implement reserve, numSeats and
	 * findAndHold
	 */
	
	private final IReserveSeat reserveSeat;
	private final INumSeatsAvailable numSeatsAvailable;
	private final IFindAndHoldSeats findAndHoldSeats;

	/**
	 * 
	 * @param findAndHoldSeats
	 * @param reserveSeat
	 * @param numSeatsAvailable
	 * @param venue
	 */
	AbstractTicketService(IFindAndHoldSeats findAndHoldSeats, IReserveSeat reserveSeat,
			INumSeatsAvailable numSeatsAvailable, IVenue venue) {
		this.findAndHoldSeats = findAndHoldSeats;
		this.reserveSeat = reserveSeat;
		this.numSeatsAvailable = numSeatsAvailable;
		this.venue = venue;
	}

	@Override
	public int numSeatsAvailable() {
		return numSeatsAvailable.numSeatsAvailable(this);
	}

	@Override
	public List<ISeatHold> findAndHoldSeats(int numSeats, String customerEmail) throws SeatsNotAvailableException {
		return findAndHoldSeats.findAndHoldSeats(numSeats, customerEmail, this);
	}

	@Override
	public String reserveSeats(String seatHoldId, String customerEmail) throws BookingNotAvailableException {
		return reserveSeat.reserveSeats(seatHoldId, customerEmail, this);
	}

	@Override
	public IBooking getSeatonHold(String seatHoldId, String customerEmail) throws BookingNotAvailableException {
		List<IBooking> bookings;
		IBooking result = null;
		synchronized (venue) {
			bookings = venue.getBookings();
			for (IBooking currentBooking : bookings) {
				if (currentBooking.getSeat().getSeatHold().getHoldID().equals(seatHoldId)) {
					result = currentBooking;
					if (!currentBooking.getOwner().equalsIgnoreCase(customerEmail))
						throw new BookingNotAvailableException("Booking doesnot match provided email");
				} else {
					// do nothing
				}
			}
			if (result == null)
				throw new BookingNotAvailableException("Booking doesnot not exist");
		}
		return result;

	}

	@Override
	public IVenue getVenue() {
		return venue;
	}

}
