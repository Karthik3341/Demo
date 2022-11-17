package com.chainsys.day2;

public class Vehicletest {

	public static void main(String[] args) {
		Vehicle veh1 = new Vehicle();
		veh1.vehicleNumber = 9626;
		veh1.regDate = "20.nov.2017";
		veh1.company = "Pulsar";
		veh1.vehicleModel = "R15";
		veh1.price = 150000;
		veh1.customerName = "Thangarasu";
		System.out.println(veh1.vehicleNumber);
		System.out.println(veh1.regDate);
		System.out.println(veh1.company);
		System.out.println(veh1.vehicleModel);
		System.out.println(veh1.price);
		System.out.println(veh1.customerName);

	}

}
