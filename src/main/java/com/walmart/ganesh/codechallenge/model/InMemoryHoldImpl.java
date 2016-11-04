package com.walmart.ganesh.codechallenge.model;

/**
 * InMemoryHoldImpl : In-Memory implementation of IBooking, represents a hold on
 * a seat
 * 
 * @author gbollapragada
 *
 */
public class InMemoryHoldImpl extends AbstractHold {

	InMemoryHoldImpl(ISeat seat, String owner) {
		super(seat, owner);
	}

}
