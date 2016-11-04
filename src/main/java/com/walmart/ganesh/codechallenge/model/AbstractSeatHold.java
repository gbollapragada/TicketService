package com.walmart.ganesh.codechallenge.model;

import java.util.UUID;

/**
 * AbstractSeatHold : Used to interact with SeatHold object, used to maintain
 * the hold id and the SeatHold
 * 
 * @author gbollapragada
 *
 */
public abstract class AbstractSeatHold implements ISeatHold {

	private final String holdID;
	private boolean isReserved = false;
	ISeat seat;

	@Override
	public String getHoldID() {
		return holdID;
	}

	public AbstractSeatHold(ISeat seat) {
		this.seat = seat;
		holdID = UUID.randomUUID().toString();
	}

	@Override
	public String getDetails() {
		StringBuffer sb = new StringBuffer();
		sb.append("Email : ");
		sb.append(seat.getBooking().getOwner());
		sb.append(";Reserved : ");
		sb.append(isReserved);
		sb.append(";HoldID : ");
		sb.append(holdID);
		sb.append(";SeatDetails : ");
		sb.append(seat.getDetails());

		return sb.toString();
	}

}
