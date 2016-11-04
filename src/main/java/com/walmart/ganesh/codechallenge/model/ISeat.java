package com.walmart.ganesh.codechallenge.model;

/**
 * ISeat : Seat API this is used to interact with the Seat object
 * 
 * @author gbollapragada
 * 
 */
public interface ISeat {
	public String getSeatID();

	public boolean isOccupied();

	public String getDetails();

	public void setBooking(IBooking booking);

	public IBooking getBooking();

	public void releaseHold();

	public void setSeatHold(ISeatHold seatHold);

	public ISeatHold getSeatHold();

	public String reserve();

}
