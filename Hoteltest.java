package com.chainsys.day2;

public class Hoteltest {

	public static void main(String[] args) {
		Hotel hotel1 = new Hotel();
		hotel1.hotelName = "Anandham";
		hotel1.hotelNumber = 123456;
		hotel1.Manager = "Gokul";
		hotel1.noOfRooms = 50;
		hotel1.airconditionerAvailable = true;
		System.out.println(hotel1.hotelName);
		System.out.println(hotel1.hotelNumber);
		System.out.println(hotel1.Manager);
		System.out.println(hotel1.noOfRooms);
		System.out.println(hotel1.airconditionerAvailable);

	}

}
