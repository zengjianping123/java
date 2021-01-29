package com.fms.dao;

import java.util.ArrayList;

import com.fms.entity.Card;
import com.fms.entity.CardInfo;
import com.fms.entity.Consumer;

public interface ConsumerDao {
	//用餐人员登录
	Card login(int id, int pwd);
	//修改密码
	int updatePwd(Card card);
	//查看个人信息
	Consumer checkConsumer(Card card);
	//充值
	int insertMoney(Card card, int money);
	//查看余额
	int lookRemaing(Card card);
	//查看消费记录
	ArrayList<CardInfo> lookRecord(Card card);
	//查看银行卡余额
	int BandRemaning(Card card, int pwd);
	//提现
	int cash(Card card, int money);
	//挂失
	int loss(Card card);
	//查看银行卡密码是否正确
	int bandPwd(Card card,int pwd);

}
