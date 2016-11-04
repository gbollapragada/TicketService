package com.walmart.ganesh.codechallenge.model;

/**
 * InMemoryReservationImpl : In-Memory implementation of IHold
 * 
 * @author gbollapragada
 *
 */
public class InMemoryReservationImpl extends AbstractReservation {

	InMemoryReservationImpl(ISeat seat, String owner) {
		super(seat, owner);
	}

}
