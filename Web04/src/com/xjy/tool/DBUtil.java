package com.xjy.tool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBUtil {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static String drivername ="";
	private static String url = "";
	private static String username = "";
	private static String password = "";
	
	static{
		//获取src目录下的文件输入流
		InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			drivername = properties.getProperty("dbDriver");
			url = properties.getProperty("dbUrl");
			username = properties.getProperty("dbUserName");
			password = properties.getProperty("dbPassWord");
			Class.forName(drivername);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection(){
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	//查询语句 select
	public static ArrayList<Object[]> executeQuery(String sql , Object[] paras){
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			if(paras != null){
				for(int i = 0 ; i < paras.length ; i++){
					ps.setObject(i+1, paras[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int cow = resultSetMetaData.getColumnCount();
			while(rs.next()){
				Object[] o = new Object[cow];
				for(int i = 1 ; i <= cow ; i++){
					o[i-1] = (Object) rs.getObject(i);
				}
				list.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, ps, rs);
		}
		return list;
	}
	
	//数据操作语言DML  update、delete、insert
	public static void executeUpdate(String sql , Object[] paras){
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			if(paras != null){
				for(int i = 0 ; i < paras.length ; i++){
					ps.setObject(i+1, paras[i]);
				}
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			close(con, ps, rs);
		}
	}
	
	public static void close(Connection con , PreparedStatement ps , ResultSet rs){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
