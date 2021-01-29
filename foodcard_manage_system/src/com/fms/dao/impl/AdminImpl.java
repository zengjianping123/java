package com.fms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fms.dao.AdminDao;
import com.fms.entity.Admin;
import com.fms.entity.Card;
import com.fms.util.Jdbc;

public class AdminImpl implements AdminDao{

	@Override
	public Admin login(String username, String password) {
		String sql = "select * from tb_admin where username=? and password=?";
		ResultSet rs = Jdbc.getrs(sql, username,password);
		Admin a = null;
		try {
			while(rs.next()) {
				a = new Admin(rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		
			Jdbc.getclose();
		}
		return a;
	}
	//查询饭卡
	@Override
	public ArrayList<Card> lookcard() {
		ArrayList<Card> list  = new ArrayList<>();
		String sql = "select * from tb_card";	
		try {
			ResultSet rs = Jdbc.getrs(sql);
			while(rs.next()) {	
				list.add(new Card(rs.getInt("card_id"), rs.getInt("c_number"),rs.getInt("card_pwd"), rs.getInt("remaning"), rs.getBoolean("flag"),rs.getInt("bd_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}
		return list;
	}
	//添加饭卡
	@Override
	public int addcard(Card card) {
		String sql = "insert into tb_card (card_id,c_number,card_pwd,bd_id) values (?,?,?,?)";
		int flag = Jdbc.getflag(sql, card.getId(),card.getcNumber(),card.getPwd(),card.getBandId());		
		Jdbc.getclose();
		return flag;
	}
	//取消挂失状态
	@Override
	public int notLoss(int id) {
		String sql = "update tb_card set flag =true where card_id=?";
		int flag = Jdbc.getflag(sql, id);
		Jdbc.getclose();
		return flag;
	}
	@Override
	public int delete(int id) {
		String sql = "delete from tb_card where card_id=?";
		int flag = Jdbc.getflag(sql, id);
		Jdbc.getclose();
		return flag;
	}
	@Override
	public int getmoney(int ch) {
		String sql = null;
		int sum = 0;
		if(ch==1) {
			sql = "select op from tb_card_info where date_format(date_time,'%Y-%m-%d')=curdate() and op<0";
		}else if(ch==2) {
			sql = "select op from tb_card_info where date_format(date_time,'%Y-%m')=date_format(curdate(),'%Y-%m') and op<0";
		}else if(ch==3) {
			sql = "select op from tb_card_info where quater(date_time)=quater(curdate()) and op<0";
		}else {
			sql = "select op from tb_card_info where date_format(date_time,'%Y')=date_format(curdate(),'%Y') and op<0";
		}		
		try {
			ResultSet rs = Jdbc.getrs(sql);
			while(rs.next()) {
				sum += rs.getInt("op");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}
		return sum;
	}
}
