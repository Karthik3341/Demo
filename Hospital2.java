package com.chainsys.day2;

import java.util.Scanner;

public class Hospital2 {

	public static void main(String[] args) {
		Hospital hospital = new Hospital();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Hospital Name:");
		String hospitalName = sc.next();
		System.out.println("Enter opening year:");
		int openingYear = sc.nextInt();
		System.out.println(hospital.hospitalName);
		System.out.println(hospital.openingYear);

	}

}
