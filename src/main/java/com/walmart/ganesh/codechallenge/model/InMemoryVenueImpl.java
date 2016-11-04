package com.walmart.ganesh.codechallenge.model;

import java.util.List;
import java.util.Map;

/**
 * InMemoryTicketServiceImpl : In-Memory implementation of IVenue, There is only
 * one Venue instance in our application
 * 
 * @author gbollapragada
 */
public class InMemoryVenueImpl extends AbstractVenu {

	private static volatile InMemoryVenueImpl instance;

	private InMemoryVenueImpl(Map<String, List<ISeat>> seatsList) {
		super(seatsList);
		if (instance != null) {
			throw new IllegalStateException("Venu is already intitialized");
		}
	}

	/**
	 * Use this to get an instance not the new operator
	 * 
	 * @return
	 */
	public static InMemoryVenueImpl getInstance(Map<String, List<ISeat>> seatsList) {
		InMemoryVenueImpl result = instance;
		if (result == null) {
			synchronized (InMemoryVenueImpl.class) {
				result = instance;
				if (result == null) {
					instance = result = new InMemoryVenueImpl(seatsList);
				}
			}
		}
		return result;
	}

	// This is only for JunitTests. Not to be used in any of the applications
	public static InMemoryVenueImpl createInstance(Map<String, List<ISeat>> seatsList) {
		return new InMemoryVenueImpl(seatsList);
	}

}
