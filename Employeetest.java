package com.chainsys.day2;

public class Employeetest {

	public static void main(String[] args) {
		Employee emp1=new Employee();
		emp1.empId=3341;
		emp1.empName="Karthik";
		emp1.domain="Trainee";
		emp1.phoneNumber=9025513968l;
		emp1.bloodGroup="AB+ve";
		emp1.address="Chennai";
		emp1.mailId="tkarthikips@gmail.com";
		System.out.println(emp1.empId);
		System.out.println(emp1.empName);
		System.out.println(emp1.domain);
		System.out.println(emp1.phoneNumber);
		System.out.println(emp1.bloodGroup);
		System.out.println(emp1.address);
		System.out.println(emp1.mailId);
	}

}
