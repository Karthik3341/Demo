package com.chainsys.day2;

import java.util.Scanner;

public class BusUsingScanner {

	public static void main(String[] args) {
		Bus bus = new Bus();
Scanner sc=new Scanner(System.in);
System.out.println("Enter fare");
System.out.println("Is it non stop?");

int fare = sc.nextInt();
bus.fare=fare;
boolean busNonStop = sc.nextBoolean();
bus.nonStop=busNonStop;

	}

}
