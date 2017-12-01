package com.xjy.test;

import java.util.HashSet;
import java.util.Set;

import com.xjy.bean.Book;
import com.xjy.tool.MD5_Tools;

public class Test {
	public static void main(String[] args) {
		Book b = new Book();
		b.setId(3);
		Book b1 = new Book();
		b1.setId(4);
		Set<Book> s = new HashSet<>();
		s.add(b);
		s.add(b1);
		System.out.println(s.size());
		
	}
	
}
