package com.xjy.bean;

public class Book {
	private Integer id;
	private String bookname;
	private String author;
	private String publisher;
	private double price;
	private int nums;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	/*
	
	public boolean equals(Book book) { 
		if(book != null)
			return book.getId() == this.getId();
		else
			return false;
    }  
	public int hashCode() {  
		return this.getId();      
	}  */ 
	
}
