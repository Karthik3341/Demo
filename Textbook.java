package com.chainsys.day2;

import java.util.Scanner;

public class Textbook {

	public static void main(String[] args) {
		Book book=new Book();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter bookName: ");
		System.out.println("Enter bookNumber");
		String bookName=sc.next();
		book.bookName=bookName;
		int bookNumber=sc.nextInt();
		book.bookNumber=bookNumber;
		System.out.println(book.bookName);
		System.out.println(book.bookNumber);
	}

}
