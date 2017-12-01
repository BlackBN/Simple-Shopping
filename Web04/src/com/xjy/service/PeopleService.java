package com.xjy.service;

import java.util.ArrayList;

import com.xjy.bean.People;
import com.xjy.tool.DBUtil;
import com.xjy.tool.MD5_Tools;

public class PeopleService {
	public boolean getPeople(People people){
		String sql = "select * from people where username=? and passwd=?";
		Object[] paras = {people.getUsername() , MD5_Tools.MD5(people.getPasswd())};
		ArrayList<Object[]> list = DBUtil.executeQuery(sql, paras);
		if(list.size() == 0 ){
			return false;
		}else{
			Object[] o = list.get(0);
			people.setUserid((Integer)o[0]);
			people.setUsername((String)o[1]);
			people.setTruename((String)o[2]);
			people.setPasswd((String)o[3]);
			people.setEmail((String)o[4]);
			people.setPhone((String)o[5]);
			people.setAddress((String)o[6]);
			people.setPostcode((String)o[7]);
			people.setGrade((int)o[8]);
			people.setPhoto((String)o[9]);
			return true;
		}
	}
	public boolean checkLogin(People people){
		String sql = "select passwd from people where username=?";
		Object[] paras = {people.getUsername()};
		ArrayList<Object[]> list = DBUtil.executeQuery(sql, paras);
		//list的长度要么为1要么为0
		if(list.size() == 0 ){
			return false;
		}
		else{
			Object[] o = list.get(0);
			if(((String)o[0]).equals(MD5_Tools.MD5(people.getPasswd()))){
				return true;
			}
			else{
				return false;
			}
		}
		
	}
}
