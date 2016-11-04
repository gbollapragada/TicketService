package com.walmart.ganesh.codechallenge.model;

/**
 * AbstractReserveSeat : This is the behavior object that implements
 * IReserveSeat, which is used by ITicketService to reserve a seat in the venue
 * if available. This can be swapped out for more optimized versions or when
 * rules change
 * 
 * @author gbollapragada
 */
public abstract class AbstractReserveSeat implements IReserveSeat {

	/**
	 * @param seatHoldId
	 *            - The ID of the hold that is used to search and reserve the
	 *            seat
	 * @param customerEmail
	 *            - The customer requesting the reservation will be used to
	 *            match against hold
	 * @param ticketServiceInterface
	 *            - Reference to the parent has-a ITicketService
	 */
	@Override
	public String reserveSeats(String seatHoldId, String customerEmail, ITicketService ticketServiceInterface)
			throws BookingNotAvailableException {
		IBooking booking = ticketServiceInterface.getSeatonHold(seatHoldId, customerEmail);
		if (booking instanceof InMemoryHoldImpl) {
			// return the hold id
			return booking.getSeat().reserve();
		} else {
			// It is already booked nothing to do return
			return booking.getSeat().getSeatHold().getHoldID();
		}
	}
}
