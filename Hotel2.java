package com.chainsys.day2;

import java.util.Scanner;

public class Hotel2 {

	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Hotel Name:");
		String hotelName = sc.next();
		System.out.println("Enter Number of Rooms:");
		int noOfRooms = sc.nextInt();
		System.out.println(hotel.hotelNumber);
		System.out.println(hotel.noOfRooms);
	}

}
