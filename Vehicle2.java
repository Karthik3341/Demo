package com.chainsys.day2;

import java.util.Scanner;

public class Vehicle2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle vehicle = new Vehicle();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Customer Name:");
		String Name = sc.next();
		System.out.println("Enter Vehicle Number:");
		int vehicleNumber = sc.nextInt();
		System.out.println(vehicle.vehicleNumber);
		System.out.println(vehicle.customerName);
	}

}
