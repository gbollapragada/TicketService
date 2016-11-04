package com.walmart.ganesh.codechallenge.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * AbstractSeat : to hold the state/instance data for a Seat
 * 
 * @author gbollapragada
 */
public abstract class AbstractSeat implements ISeat {

	private final String seatID;
	private final String row;
	// Can be of type Hold or Reservation
	private IBooking booking;
	private ISeatHold seatHold;
	//Timer to release the hold in 6 seconds if a reservation is not made
	private Timer releaseHoldBooking = new Timer();

	public AbstractSeat(String row, String seatID) {
		this.seatID = seatID;
		this.row = row;
	}

	/**
	 * SeatID is a combination of the row and the seat ID
	 */
	@Override
	public String getSeatID() {
		return row + seatID;
	}

	/**
	 * Checks if the seat is occupied by inspecting if there are temporary holds
	 * or booking
	 */
	@Override
	public boolean isOccupied() {
		return booking != null;
	}

	/**
	 * Used to set hold or reserve on a seat, there is a timer object that is
	 * invoked in 6 seconds to release the seat if no booking is made
	 */
	@Override
	public void setBooking(IBooking booking) {
		this.booking = booking;
		this.releaseHoldBooking.schedule(new TimerTask() {
			public void run() {
				releaseHold();
			}
		}, 6000);
	}

	@Override
	public IBooking getBooking() {
		return this.booking;
	}

	/**
	 * Used to get the state information for the seat object as a string
	 */
	@Override
	public String getDetails() {
		StringBuffer sb = new StringBuffer();
		sb.append("Seat Details : ");
		sb.append(getSeatID());
		sb.append(";IsOnHold : ");
		if (isOccupied()) {
			sb.append(isOccupied());
			sb.append(";");
			sb.append(booking.getOwner());
			sb.append(";");
		}
		return sb.toString();
	}

	/**
	 * Releases the hold on a seat. Stops any timer events that may be running
	 */
	@Override
	public void releaseHold() {
		this.releaseHoldBooking.cancel();
		this.booking = null;
		this.seatHold = null;
	}

	/**
	 * Holds the seat
	 */
	@Override
	public void setSeatHold(ISeatHold seatHold) {
		this.seatHold = seatHold;
	}

	/**
	 * Returns the hold object
	 */
	@Override
	public ISeatHold getSeatHold() {
		return seatHold;
	}

	/**
	 * Reserves the seat that is on hold
	 * 
	 * @param confirmBooking
	 *            - replace the temporary booking with the confirmed booking
	 * @return - the HoldID
	 */
	protected String reserve(IBooking confirmBooking) {
		this.releaseHoldBooking.cancel();
		this.booking = confirmBooking;
		return this.seatHold.getHoldID();
	}

}
