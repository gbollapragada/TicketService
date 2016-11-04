package com.walmart.ganesh.codechallenge.model;

/**
 * BookingNotAvailableException : BookingNotAvailableException is thrown when
 * the requested booking is invalid
 * 
 * @author gbollapragada
 *
 */
@SuppressWarnings("serial")
public class BookingNotAvailableException extends Exception {

	public BookingNotAvailableException(String message) {
		super(message);
	}
}
