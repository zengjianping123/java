package com.fms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fms.dao.ConsumerDao;
import com.fms.entity.Card;
import com.fms.entity.CardInfo;
import com.fms.entity.Consumer;
import com.fms.util.Jdbc;

public class ConsumerImpl implements ConsumerDao{
	//用餐人员登录
	@Override
	public Card login(int id, int pwd) {
		Card card = null;
		try {
			String sql = "select * from tb_card where flag=1 and card_id=? and card_pwd=? ";
			ResultSet rs = Jdbc.getrs(sql, id,pwd);
			while(rs.next()) {
				card = new Card(id, rs.getInt("c_number"), rs.getInt("remaning"), pwd,rs.getBoolean("flag"),rs.getInt("bd_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}		
		return card;
	}
	//修改密码
	@Override
	public int updatePwd(Card card) {
		String sql = "update tb_card set card_pwd=? where card_id=?";
		int flag = Jdbc.getflag(sql, card.getPwd(),card.getId());
		Jdbc.getclose();
		return flag;
	}
	//查询用餐人信息
	@Override
	public Consumer checkConsumer(Card card) {
		Consumer consumer = null;
		try {
			String sql = "select * from tb_consumer where number=?";
			ResultSet rs = Jdbc.getrs(sql, card.getcNumber());
			while(rs.next()) {
				consumer = new Consumer(card.getcNumber(), rs.getString("username"), rs.getString("sex"), rs.getBoolean("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}
		return consumer;
	}
	//饭卡充值
	@Override
	public int insertMoney(Card card, int money) {		
		/*String sql = "update tb_card set remaning=remaning+? where card_id=?";
		int flag = Jdbc.getflag(sql,money,card.getId());
		Jdbc.getclose();
		String sql2 = "update bandcard set remaning=remaning-? where c_number=?";
		int flag2 =Jdbc.getflag(sql2, money,card.getcNumber());
		Jdbc.getclose();
		return flag+flag2;*/		
		Connection con = Jdbc.getcon();	
		PreparedStatement ps = null;
		int flag = 0;
		try {
			con.setAutoCommit(false);
			String sql = "update tb_card set remaning=remaning+? where card_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, money);
			ps.setInt(2, card.getId());
			flag = ps.executeUpdate();
			String sql2 = "update bandcard set remaning=remaning-? where bandcard_id=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, money);
			ps.setInt(2,card.getBandId());
			flag += ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				if(con!=null) {
					con.rollback();
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			Jdbc.getclose(con, ps,null);
		}
		return flag;
	}
	//查看饭卡余额
	@Override
	public int lookRemaing(Card card) {
		int remaning = 0;
		try {
			String sql = "select remaning from tb_card where card_id=?";
			ResultSet rs = Jdbc.getrs(sql, card.getId());
			while(rs.next()) {
				remaning = rs.getInt("remaning");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}
		return remaning;
	}
	//查看消费记录
	@Override
	public ArrayList<CardInfo> lookRecord(Card card) {
		ArrayList<CardInfo> list = null;
		try {
			String sql = "select * from tb_card_info where card_id=?";
			ResultSet rs = Jdbc.getrs(sql, card.getId());
			while(rs.next()) {
				list.add(new CardInfo(rs.getInt("card_id"), rs.getString("date_time"), rs.getInt("op"), rs.getInt("remaing")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}
		return list;
	}
	//查看银行卡余额
	@Override
	public int BandRemaning(Card card, int pwd) {
		int remaning = 0;
		try {
			String sql = "select remaning from bandcard where bandcard_id=? and pwd=?";
			ResultSet rs = Jdbc.getrs(sql, card.getBandId(),pwd);
			while(rs.next()) {
				remaning = rs.getInt("remaning");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remaning;
	}
	@Override
	//饭卡提现
	public int cash(Card card, int money) {
		Connection con = Jdbc.getcon();	
		PreparedStatement ps = null;
		int flag = 0;
		try {
			con.setAutoCommit(false);
			String sql = "update tb_card set remaning=remaning-? where card_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, money);
			ps.setInt(2, card.getId());
			flag = ps.executeUpdate();
			String sql2 = "update bandcard set remaning=remaning+? where bandcard_id=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, money);
			ps.setInt(2,card.getBandId());
			flag += ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				if(con!=null) {
					con.rollback();
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			Jdbc.getclose(con, ps,null);
		}
		return flag;
	}
	//挂失
	@Override
	public int loss(Card card) {
		String sql = "update tb_card set flag=0 where card_id=?";
		int flag = Jdbc.getflag(sql, card.getId());
		Jdbc.getclose();
		return flag;
	}
	//判断密码是否真确
	@Override
	public int bandPwd(Card card,int pwd) {
		int flag = 0;
		String sql = "select c_number,pwd from bandcard where bandcard_id=? and pwd=?";		
		try {
			ResultSet rs = Jdbc.getrs(sql, card.getBandId(),pwd);
			while(rs.next()) {
				flag++; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.getclose();
		}	
		return flag;
	}
}
