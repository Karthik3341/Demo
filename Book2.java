package com.chainsys.day2;

import java.util.Scanner;

public class Book2 {

	public static void main(String[] args) {
		Book book = new Book();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Name:");
		String bookName = sc.next();
		System.out.println("Enter Book Number:");
		int bookNumber = sc.nextInt();
		System.out.println(book.bookNumber);
		System.out.println(book.bookName);
	}

}
