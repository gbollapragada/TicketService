package com.walmart.ganesh.codechallenge;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import com.walmart.ganesh.codechallenge.model.BookingNotAvailableException;
import com.walmart.ganesh.codechallenge.model.IFindAndHoldSeats;
import com.walmart.ganesh.codechallenge.model.INumSeatsAvailable;
import com.walmart.ganesh.codechallenge.model.IReserveSeat;
import com.walmart.ganesh.codechallenge.model.ISeat;
import com.walmart.ganesh.codechallenge.model.ISeatHold;
import com.walmart.ganesh.codechallenge.model.ITicketService;
import com.walmart.ganesh.codechallenge.model.IVenue;
import com.walmart.ganesh.codechallenge.model.InMemoryFindAndHoldSeatsImpl;
import com.walmart.ganesh.codechallenge.model.InMemoryNumSeatsAvailableImpl;
import com.walmart.ganesh.codechallenge.model.InMemoryReserveSeatImpl;
import com.walmart.ganesh.codechallenge.model.InMemorySeatImpl;
import com.walmart.ganesh.codechallenge.model.InMemoryTicketServiceImpl;
import com.walmart.ganesh.codechallenge.model.InMemoryVenueImpl;
import com.walmart.ganesh.codechallenge.model.SeatsNotAvailableException;

import junit.framework.TestCase;

/**
 * These are the test cases to test the public API for the ITicketService and
 * the in memory implementation
 * 
 * @author gbollapragada
 *
 */
public class TestTicketServer extends TestCase {

	ITicketService ticketServer;
	volatile int availableSeats;
	int allocate4;
	int allocateOver;
	int allocateNegtive;

	public static void main(String args[]) {
		
		org.junit.runner.JUnitCore.main("com.walmart.ganesh.codechallenge.TestTicketServer");
	}

	/**
	 * setUp for the test case
	 */
	// @Before
	protected void setUp() throws Exception {
		super.setUp();

		Map<String, List<ISeat>> seatsList = new ConcurrentHashMap<String, List<ISeat>>();
		IVenue venueObj;
		INumSeatsAvailable numSeatsAvailable;

		// Initializing with 306 seats
		allocate4 = 4;
		allocateOver = 400;
		allocateNegtive = -2;

		List<String> rows;

		rows = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

		for (String row : rows) {
			List<ISeat> seats = new CopyOnWriteArrayList<ISeat>();
			for (int i = 0; i <= 33; i++) {
				ISeat seat = new InMemorySeatImpl(Integer.toString(i), row);
				seats.add(seat);
			}
			seatsList.put(row, seats);
		}

		availableSeats = rows.size() * 34;
		int numSeats = 0;
		Iterator<List<ISeat>> seatsIterator = seatsList.values().iterator();
		while (seatsIterator.hasNext()) {
			numSeats = numSeats + seatsIterator.next().size();
		}

		// venueObj = VenueImpl.getInstance(seatsList);
		// Only for test case using createInstance. Actual clients should use
		// getInstance
		venueObj = InMemoryVenueImpl.createInstance(seatsList);
		// Behavior instances
		numSeatsAvailable = new InMemoryNumSeatsAvailableImpl();
		IFindAndHoldSeats fidAndHoldSeats = new InMemoryFindAndHoldSeatsImpl();
		IReserveSeat reserveSeats = new InMemoryReserveSeatImpl();

		ticketServer = new InMemoryTicketServiceImpl(fidAndHoldSeats, reserveSeats, numSeatsAvailable, venueObj);

	}

	/**
	 * tearDown code for the test case
	 */
	// @After
	protected void tearDown() throws Exception {
		super.tearDown();
		ticketServer = null;
	}

	/**
	 * Test the initial seats available before exercising hold
	 */
	@Test
	public void testInitializeVenue() {
		System.out.println("Test the initial seats available before exercising hold");
		assertEquals("Expected seats in the venue are ", availableSeats, ticketServer.numSeatsAvailable());
	}

	/**
	 * Test if the proper exception is thrown if number of seats requested is
	 * not available
	 */
	@Test
	public void testExtraSeatsBooking() {
		System.out.println("Test if the proper exception is thrown if number of seats requested is not available");

		boolean exception = false;
		try {
			List<ISeatHold> holds = ticketServer.findAndHoldSeats(400, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
			exception = true;
		}

		assertEquals("Expected exception will be thrown", true, exception);
		assertEquals("Expected exception will be thrown", availableSeats, ticketServer.numSeatsAvailable());

	}

	/**
	 * Test if holds result in reduction in available seats
	 */
	@Test
	public void testHolds() {
		System.out.println("Test if holds result in reduction in available seats");
		try {
			List<ISeatHold> holds = ticketServer.findAndHoldSeats(allocate4, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
		}

		int computedAvailableSeats = availableSeats - allocate4;
		assertEquals("Expected exception will be thrown", computedAvailableSeats, ticketServer.numSeatsAvailable());
	}

	/**
	 * Test if holds result in reduction in available seats
	 */
	@Test
	public void testNegtiveHolds() {
		System.out.println("Test if holds result in reduction in available seats");
		boolean exception = false;
		try {
			List<ISeatHold> holds = ticketServer.findAndHoldSeats(allocateNegtive, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
			exception = true;
		}

		assertEquals("Expected exception will be thrown", true, exception);
		assertEquals("Expected exception will be thrown", availableSeats, ticketServer.numSeatsAvailable());
	}

	/**
	 * Test case for reserving a booking
	 */
	@Test
	public void testReserveOnHold() {
		System.out.println("Test case for reserving a booking");
		List<ISeatHold> holds = null;
		boolean exception = false;
		try {
			holds = ticketServer.findAndHoldSeats(1, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
		}

		assertEquals("Test case for reserving a booking", holds != null, true);

		for (ISeatHold hold : holds) {
			try {
				String reservation = ticketServer.reserveSeats(hold.getHoldID(), "test.a@email.com");
				assertEquals("Ethe expeced reservation id is ", hold.getHoldID(), reservation);
				// System.out.println("HOLD ID : " + hold.getHoldID());
			} catch (BookingNotAvailableException e) {
				exception = true;
			}
		}

		int computedAvailableSeats = availableSeats - 1;
		assertEquals("Seat allocation is successful", computedAvailableSeats, ticketServer.numSeatsAvailable());
		assertEquals("Expected exception should not be thrown", false, exception);

	}

	/**
	 * Test case for reserving a booking
	 */
	@Test
	public void testReserveWithInvalidMail() {
		System.out.println("Test case for reserving a booking");
		List<ISeatHold> holds = null;
		boolean exception = false;
		try {
			holds = ticketServer.findAndHoldSeats(1, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
		}

		assertEquals("Test case for reserving a booking", holds != null, true);

		for (ISeatHold hold : holds) {
			try {
				String reservation = ticketServer.reserveSeats(hold.getHoldID(), "test.b@email.com");
				assertEquals("Ethe expeced reservation id is ", hold.getHoldID(), reservation);
			} catch (BookingNotAvailableException e) {
				exception = true;
			}
		}

		int computedAvailableSeats = availableSeats - 1;
		assertEquals("Seat allocation is successful", computedAvailableSeats, ticketServer.numSeatsAvailable());
		assertEquals("Expected exception should not be thrown", true, exception);

	}

	/**
	 * Test case for reserve invalid hold
	 */
	@Test
	public void testReserveOnInvalidHold() {
		System.out.println("Test case for reserve invalid hold");
		String holdID = "0cecbb86-70ae-43df-8daa-e2b5d3caaa13";
		boolean exception = false;
		try {
			String reservation = ticketServer.reserveSeats(holdID, "test.b@email.com");
		} catch (BookingNotAvailableException e) {
			exception = true;
		}

		assertEquals("Test case for reserve invalid Hold, Exception expected", true, exception);
	}

	/**
	 * Test case for reserve expired hold
	 */
	@Test
	public void testReserveOnReleasedExpiredHold() {
		System.out.println("Test case for reserve expired hold");
		List<ISeatHold> holds = null;
		boolean exception = false;
		try {
			holds = ticketServer.findAndHoldSeats(1, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
		}

		assertEquals("Test case for reserving a booking", holds != null, true);

		for (ISeatHold hold : holds) {
			try {
				Thread.sleep(9000);
				String reservation = ticketServer.reserveSeats(hold.getHoldID(), "test.b@email.com");
			} catch (BookingNotAvailableException e) {
				exception = true;
			} catch (InterruptedException e) {
				// added for thread sleep
			}
		}

		assertEquals("Seat allocation is successful", availableSeats, ticketServer.numSeatsAvailable());
		assertEquals("Test case for reserve expired hold Exception expected", true, exception);
	}

	/**
	 * Test case to check release on holds
	 */
	@Test
	public void testReleaseOnExpiredHold() {
		System.out.println("Test case to check release on holds");
		List<ISeatHold> holds = null;
		try {
			holds = ticketServer.findAndHoldSeats(5, "test.a@email.com");

		} catch (SeatsNotAvailableException e) {
		}

		assertEquals("Test case for reserving a booking", holds != null, true);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// added for thread sleep
		}
		assertEquals("Seat allocation is successful", availableSeats, ticketServer.numSeatsAvailable());
	}

}
