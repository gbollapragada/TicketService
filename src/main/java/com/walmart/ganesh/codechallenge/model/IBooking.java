package com.walmart.ganesh.codechallenge.model;

/**
 * IBooking : This is the API that is used to access the specialized instances
 * of AbstractHold or AbstractReservation
 * 
 * @author gbollapragada
 */
public interface IBooking {
	public String getOwner();

	public void release();

	public ISeat getSeat();
}
