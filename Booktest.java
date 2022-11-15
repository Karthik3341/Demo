package com.chainsys.day2;

public class Booktest {

	public static void main(String[] args) {
		Book book1=new Book();
		book1.bookName="Wings Of Fire";
		book1.bookAuthor="Dr.APJ Abdulkalam";
		book1.bookNumber=12345;
		book1.publishedDate="12.nov.2018";
		book1.rating=9.5d;
		System.out.println(book1.bookName);
		System.out.println(book1.bookAuthor);
		System.out.println(book1.bookNumber);
		System.out.println(book1.publishedDate);
		System.out.println(book1.rating);
	}

}
