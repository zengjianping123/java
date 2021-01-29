package com.fms.dao;

import java.util.ArrayList;

import com.fms.entity.Admin;
import com.fms.entity.Card;

public interface AdminDao {
	//登录
	Admin login(String username, String password);
	//查询用餐人员信息
	ArrayList<Card> lookcard();
	//添加饭卡
	int addcard(Card card);
	//取消挂失
	int notLoss(int id);
	//注销用户
	int delete(int id);
	//统计用户支出
	int getmoney(int ch);

}
