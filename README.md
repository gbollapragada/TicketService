
# Introduction

This project is a simple in-memory implementation of the Ticket Service Coding Challenge.

# Objectives

	1) I have taken the approach to keep this project simple
	2) Written in Java
	3) Designed the required interfaces and in-memory implementations
	4) Used Maven as the build tool
	5) Entirely in-memory, can be easily extended to disk-base, REST or other desired implementations
	6) Adhered to standard camel case naming conventions, and other best practices
	7) Uses Junit as the client to simulate and run the tests against the Ticket Service Interface API methods
	8) Build is self-contained and can be tested from command line 

# Assumptions / Notes to developers

	1) Have implemented a simple in-memory version of the designed API
	2) Don’t believe I have used any Java 8 conventions
	3) Have used Strategy and Singleton pattern in the design
	4) Have only used a simple timer to delegate seat hold release in 6 seconds if not reserved
	5) No dependencies other that Jnuit for testing
	6) Junit is the client to test, no separate class with main method is available to test the implementation
	7) Code is implemented to be thread safe
	8) IDE specific files are not checked in, instruction to generate Eclipse project provided
	9) No optimizations have been performed
	10) Only the main ITicketService interface is tested
	11) Can easily be extended to provide different behavior for findAndHoldSeats, numSeatsAvailable and reserveSeats
	12) Can easily extend and provide implementation for disk-based implementation

# Instruction to build

Prerequisites to build:

	1) Java SDK (Tested with: build 1.8.0_45-b15)
	2) Git (Tested with: version 2.8.3)
	3) Maven (Tested with: Apache Maven 3.3.3)

Clone from GitHub

	URL: https://github.com/gbollapragada/TicketingService.git
	1) git checkout https://github.com/gbollapragada/TicketingService.git
	2) cd TicketService

Command to Build

	1) cd TicketService
	2) mvn -U clean install

Command to test

	1) cd target
	2) java -jar TicketingService-1.0-SNAPSHOT-fat-tests.jar

Command to Build eclipse project files

	1) cd TicketService
	2) mvn eclipse:eclipse
