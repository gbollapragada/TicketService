package com.walmart.ganesh.codechallenge.model;

/**
 * InMemorySeatImpl : In-Memory implementation of ISeat
 * 
 * @author gbollapragada
 *
 */
public class InMemorySeatImpl extends AbstractSeat {

	public InMemorySeatImpl(String seatID, String row) {
		super(seatID, row);
	}

	@Override
	public String reserve() {
		IBooking tmpBooking = getBooking();
		IBooking confirmBooking = new InMemoryReservationImpl(tmpBooking.getSeat(), tmpBooking.getOwner());
		return confirmBooking.getSeat().getSeatHold().getHoldID();
	}

}
