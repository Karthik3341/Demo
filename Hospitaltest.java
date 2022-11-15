package com.chainsys.day2;

public class Hospitaltest {

	public static void main(String[] args) {
		Hospital hosp1=new Hospital();
		hosp1.openingYear=2020;
		hosp1.hospitalName="Apollo Hospital";
		hosp1.hospitalAddress="Chennai";
		hosp1.ambulanceService=true;
		hosp1.rating=9.5f;
		System.out.println(hosp1.openingYear);
		System.out.println(hosp1.hospitalName);
		System.out.println(hosp1.hospitalAddress);
		System.out.println(hosp1.ambulanceService);
		System.out.println(hosp1.rating);
		

	}

}
