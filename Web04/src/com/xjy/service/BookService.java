package com.xjy.service;

import java.util.ArrayList;

import com.xjy.bean.Book;
import com.xjy.tool.DBUtil;

public class BookService {
	public long getPageCount(int pageSize){
		long pageCount;
		String sql = "select count(*) from book";
		ArrayList<Object[]> list = DBUtil.executeQuery(sql, null);
		Object[] o = list.get(0);
		long rowCount = (long)o[0];
		pageCount = (rowCount - 1) / pageSize + 1;
		return pageCount;
	}
	public Book getBook(int id){
		String sql = "select * from book where id = ?";
		Object[] paras = {id};
		ArrayList<Object[]> list = DBUtil.executeQuery(sql, paras);
		if(list.size() == 0 )
			return null;
		//根据id号查询书，会创建一个新的Book对象
		Book b = new Book();
		Object[] o = list.get(0);
		b.setId((int)o[0]);
		b.setBookname((String)o[1]);
		b.setAuthor((String)o[2]);
		b.setPublisher((String)o[3]);
		b.setPrice((double)o[4]);
		b.setNums((int)o[5]);
		return b;
	}
	
	public ArrayList<Book> getBooks(int pageNow , int pageSize){
		ArrayList<Book> bookList = new ArrayList<>();
		String sql = "select * from book limit ?,?";
		Object[] paras = {(pageNow-1) * pageSize , pageSize};
		ArrayList<Object[]> lists = DBUtil.executeQuery(sql, paras);
		for(int i = 0 ; i < lists.size() ; i++){
			Object[] o = lists.get(i);
			Book b = new Book();
			b.setId((int)o[0]);
			b.setBookname((String)o[1]);
			b.setAuthor((String)o[2]);
			b.setPublisher((String)o[3]);
			b.setPrice((double)o[4]);
			b.setNums((int)o[5]);
			bookList.add(b);
		}
		return bookList;
	}
}
