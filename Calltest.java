package com.chainsys.day2;

import java.util.Scanner;

public class Calltest {

	public static void main(String[] args) {
		Call call=new Call();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any two numbers to perfoem arithmetic opertions");
		int num1=sc.nextInt();
		int num2=sc.nextInt();
		call.a=num1;
		call.b=num2;
		System.out.println("Sum of given number:"+(num1+num2));
		System.out.println(number1+number2);
	}

}
