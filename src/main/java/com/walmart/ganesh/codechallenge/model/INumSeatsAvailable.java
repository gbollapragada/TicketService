package com.walmart.ganesh.codechallenge.model;

/**
 * INumSeatsAvailable : This is the behavior API that is to be implemented,
 * which is used by ITicketService to find the seats that are available in the
 * venue The number of seats in the venue that are neither held nor reserved.
 * This behavior can be swapped out when rules change
 * 
 * @author gbollapragada
 */
public interface INumSeatsAvailable {

	int numSeatsAvailable(ITicketService ticketServiceInterface);
}
