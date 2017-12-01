package com.xjy.service;

import java.util.LinkedHashMap;


import com.xjy.bean.Book;

public class MyCartService {
	private LinkedHashMap<Integer, Book> hashMap = new LinkedHashMap<>();
	private LinkedHashMap<Book, Integer> map = new LinkedHashMap<>();
	//添加书
	public void addBook(int id , Book book){
		hashMap.put(id, book);
		map.put(book, 1);
	}
	public void addBook(Book book){
		map.put(book , map.get(book) + 1);
	}
	
	public void delBook(int id){
		if(hashMap.get(id) != null){
			map.remove(hashMap.get(id));
		}
		hashMap.remove(id);
	}
	//更新书
	public void updateBook(int id , int nums){
		if(nums < 1){
			map.remove(hashMap.get(id));
			hashMap.remove(id);
		}
		else
			map.put(hashMap.get(id), nums);
	}
	//清空购物车
	public void clearCart(){
		map.clear();
		hashMap.clear();
	}
	/**
	 * 
	 * @return
	 */
	public double getTotalPrice(){
		double sum = 0.0;
		for(Book book : map.keySet()){
			sum += (book.getPrice() * map.get(book));
		}
		return sum;
	}
	public LinkedHashMap<Integer, Book> getHashMap() {
		return hashMap;
	}

	public LinkedHashMap<Book, Integer> getMap() {
		return map;
	}

	
	
	
}
