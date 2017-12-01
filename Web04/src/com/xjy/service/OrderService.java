package com.xjy.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.xjy.bean.Book;
import com.xjy.bean.Order;
import com.xjy.bean.People;
import com.xjy.tool.DBUtil;

public class OrderService{
	private Connection con;
	private PreparedStatement ps ;
	private ResultSet rs;
	public void submitOrder(People people , MyCartService myCartService , Order order){
		String sql = "insert into orders(userid,orderDate,payMode,isPay,totalPrice) values(?,?,?,?,?)";
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			//��������������뼶�����߼��𣬷�ֹ����û�ͬʱ�ύ�����ᵼ��ȡ�õ�orderId֮�䲻��Ӧ
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);//�ɴ��л�����
			
			//��Ӷ���
			ps = con.prepareStatement(sql);
			ps.setInt(1, people.getUserid());
			ps.setDate(2, new Date(order.getOrderDate().getTime()));
			ps.setString(3, order.getPayMode());
			ps.setBoolean(4, order.isPay());
			ps.setDouble(5, order.getTotalPrice());
			ps.executeUpdate();
			
			//ȡ�øո����ɵĶ���id��
			sql = "select max(orderId) from orders";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			int orderId = rs.getInt(1);
			order.setOrderId(orderId);
			 
			//��Ӷ���ϸ�ڱ�
			LinkedHashMap<Book, Integer> map = myCartService.getMap();
			for(Book book : map.keySet()){
				sql = "insert into orderdetail(orderId,goodId,nums) values(?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setInt(3, map.get(book));
				ps.executeUpdate();
			}
			
			//�����ύ
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			DBUtil.close(con, ps, rs);
		}
		
	}
}
