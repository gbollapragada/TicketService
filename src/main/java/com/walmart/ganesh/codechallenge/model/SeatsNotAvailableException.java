package com.walmart.ganesh.codechallenge.model;

/**
 * SeatsNotAvailableException : SeatsNotAvailableException is thrown when
 * requested seats are not available
 * 
 * @author gbollapragada
 *
 */
@SuppressWarnings("serial")
public class SeatsNotAvailableException extends Exception {

	/**
	 * Exception thrown when the requested seats are not available
	 * 
	 * @param message
	 *            - initialized with the message that has to be shown to the
	 *            user
	 */
	public SeatsNotAvailableException(String message) {
		super(message);
	}
}
