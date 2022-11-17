package com.chainsys.day2;

public class testbus {

	public static void main(String[] args) {

		Bus bus1 = new Bus();
		bus1.nonStop = false;
		bus1.busSource = "Chennai";
		bus1.busDestination = "Madurai";
		System.out.println(bus1.busDestination);
		System.out.println(bus1.busId);
	}

}
