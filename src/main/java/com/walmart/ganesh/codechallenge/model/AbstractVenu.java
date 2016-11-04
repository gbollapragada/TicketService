package com.walmart.ganesh.codechallenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * AbstractVenu : Venue container to hold all seats in the venue
 * 
 * @author gbollapragada
 * 
 */
public abstract class AbstractVenu implements IVenue {

	// This the structure that holds the seats, row and the list of seats
	private final Map<String, List<ISeat>> seatsList;

	/**
	 * Initialized with the available rows and seats
	 * 
	 * @param seatsList
	 *            - list of all the rows and seats
	 */
	public AbstractVenu(Map<String, List<ISeat>> seatsList) {
		assert seatsList != null;
		this.seatsList = seatsList;
	}

	/**
	 * This method returns the list of holds in the venue seats
	 */
	@Override
	public List<IBooking> getHolds() {
		List<IBooking> listOfHolds = new ArrayList<IBooking>();
		synchronized (seatsList) {
			for (Entry<String, List<ISeat>> entry : seatsList.entrySet()) {
				List<ISeat> seats = entry.getValue();
				for (ISeat seatInterface : seats) {
					if (seatInterface.getBooking() instanceof AbstractHold) {
						AbstractHold hold = (AbstractHold) seatInterface.getBooking();
						listOfHolds.add(hold);
					}
				}
			}
			return listOfHolds;
		}
	}

	/**
	 * Returns the reservations on the venue
	 */
	@Override
	public List<IBooking> getReservations() {
		List<IBooking> listOfReservations = new ArrayList<IBooking>();
		synchronized (seatsList) {
			for (Entry<String, List<ISeat>> entry : seatsList.entrySet()) {
				List<ISeat> seats = entry.getValue();
				for (ISeat seatInterface : seats) {
					if (seatInterface.getBooking() instanceof AbstractReservation) {
						AbstractReservation reservation = (AbstractReservation) seatInterface.getBooking();
						listOfReservations.add(reservation);
					}
				}
			}
			return listOfReservations;
		}
	}

	/**
	 * Returns all bookings on the seat on hold and reserved
	 */
	@Override
	public List<IBooking> getBookings() {
		List<IBooking> listOfBookings = new ArrayList<IBooking>();
		synchronized (seatsList) {
			for (Entry<String, List<ISeat>> entry : seatsList.entrySet()) {
				List<ISeat> seats = entry.getValue();
				for (ISeat seatInterface : seats) {
					IBooking bookings = (IBooking) seatInterface.getBooking();
					if (bookings != null)
						listOfBookings.add(bookings);
				}
			}
			return listOfBookings;
		}
	}

	/**
	 * Returns the number of available seats in the venue, considers both hold
	 * and reserved seats
	 */
	public int getNumOfAvailableSeats() {
		int numberOfSeats = 0;
		synchronized (seatsList) {
			for (Entry<String, List<ISeat>> entry : seatsList.entrySet()) {
				List<ISeat> seats = entry.getValue();
				for (ISeat seatInterface : seats) {
					if (!seatInterface.isOccupied()) {
						numberOfSeats++;
					}
				}
			}
			return numberOfSeats;
		}
	}

	/**
	 * Returns a list of open seats also considers on hold and reserved seats
	 */
	@Override
	public List<ISeat> getOpenSeats() {
		List<ISeat> openSeats = new ArrayList<ISeat>();
		synchronized (seatsList) {
			for (Entry<String, List<ISeat>> entry : seatsList.entrySet()) {
				List<ISeat> seats = entry.getValue();
				for (ISeat seatInterface : seats) {
					if (!seatInterface.isOccupied()) {
						openSeats.add(seatInterface);
					}
				}
			}
			return openSeats;
		}
	}

}
