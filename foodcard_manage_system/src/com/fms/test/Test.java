package com.fms.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.fms.dao.AdminDao;
import com.fms.dao.ConsumerDao;
import com.fms.dao.impl.AdminImpl;
import com.fms.dao.impl.ConsumerImpl;
import com.fms.entity.Admin;
import com.fms.entity.Card;
import com.fms.entity.CardInfo;
import com.fms.entity.Consumer;

public class Test {
	static Scanner sc = new Scanner(System.in);
	static AdminDao ad = new AdminImpl();
	static ConsumerDao cd = new ConsumerImpl();
	public static void main(String[] args) {
		while(true) {
			System.out.println("1:管理员登录，2：用餐人员登录,3:退出系统");
			int choice = sc.nextInt();
			if(choice==1) {//1:管理员登录，
				System.out.println("请输入用户名");
				String username = sc.next();
				System.out.println("请输入密码");
				String password = sc.next();
				Admin admin = ad.login(username,password);				
				if(admin!=null) {					
					System.out.println("登录成功");
					admin(admin);										
				}else {
					System.out.println("登录失败");
				}	
			}else if(choice==2) {//2：用餐人员登录,
				System.out.println("请输入卡号");
				int id = sc.nextInt();
				System.out.println("请输入密码");
				int password = sc.nextInt();
				Card card = cd.login(id,password);
				if(card!=null) {
					System.out.println("登录成功");
					card(card);
				}else {
					System.out.println("登录失败");
				}
			}else {//3:退出系统
				break;
			}
		}
	}
	private static void card(Card card) {
		while(true) {
			System.out.println("1查询个人信息，2：修改饭卡密码，3：查看饭卡余额，4：查看银行卡余额，5：饭卡充值，6：提现,7：查看消费记录，8：挂失，9:退出登录");
			int cho = sc.nextInt();
			if(cho==1) {//1查询个人信息
				Consumer consumer = cd.checkConsumer(card);
				if(consumer!=null) {
					System.out.println("个人信息"+consumer.toString());
				}else {
					System.out.println("查询个人信息失败");
				}
			}else if(cho==2) {//2：修改饭卡密码
				System.out.println("请输入新密码");
				int pwd1 = sc.nextInt();
				System.out.println("请输入确认密码");
				int pwd2 = sc.nextInt();
				if(pwd1==pwd2) {
					card.setPwd(pwd1);
					int flag = cd.updatePwd(card);
					if(flag>0) {
						System.out.println("修改密码成功");
					}else {
						System.out.println("修改失败");
					}
				}else {
					System.out.println("新密码和确认密码不匹配");
				}
			}else if(cho==3) {//3：查看饭卡余额
				int remaning = cd.lookRemaing(card);
				System.out.println(remaning);
			}else if(cho==4) {//4：查看银行卡余额，
				System.out.println("请输入银行卡密码");
				int pwd = sc.nextInt();
				int flag = cd.bandPwd(card,pwd);
				if(flag>0) {
					int remaning = cd.BandRemaning(card,pwd);
					System.out.println(remaning);
				}else {
					System.out.println("银行卡密码输入错误");
				}				
			}else if(cho==5){//5：饭卡充值
				System.out.println("请输入银行卡密码");
				int pwd = sc.nextInt();
				int flag1 = cd.bandPwd(card,pwd);
				if(flag1>0) {
					System.out.println("请输入您要充值的金额；");
					int money = sc.nextInt();				
					if(money<=cd.BandRemaning(card, pwd)) {
						int flag = cd.insertMoney(card,money);
						if(flag>1) {
							int remaning = cd.lookRemaing(card);
							System.out.println("充值成功，余额为："+remaning);
						}else {
							System.out.println("充值失败");
						}
					}else {
						System.out.println("充值失败，您输入的金额大于银行卡余额!");
					}
				}else {
					System.out.println("银行卡密码输入错误");
				}				
			}else if(cho==6){//6：提现
				System.out.println("请输入银行卡密码");
				int pwd = sc.nextInt();
				int flag1 = cd.bandPwd(card,pwd);
				if(flag1>0) {
					System.out.println("请输入提现金额：");
					int money = sc.nextInt();
					if(money<=cd.lookRemaing(card)) {
						int flag = cd.cash(card,money);
						if(flag>1) {
							System.out.println("提现成功！");
						}else {
							System.out.println("提现失败");
						}
					}else {
						System.out.println("提现失败，您输入的金额大于饭卡余额");
					}
				}else {
					System.out.println("银行卡密码输入错误");
				}			
				
			}else if(cho==7){//,7：查看消费记录，
				ArrayList<CardInfo> list = cd.lookRecord(card);
				if(list!=null) {
					list.forEach(s->System.out.println(s));
				}else {
					System.out.println("您还没有消费记录");
				}
			}else if(cho==8){//8：挂失
				System.out.println("您确认要挂失？Y：确认    N:否");
				String answer = sc.next();
				if(answer.equals("Y")) {
					int flag = cd.loss(card);
					if(flag>0) {
						System.out.println("挂失成功");
					}else {
						System.out.println("挂失失败");
					}
				}
			}else {//9:返回上一级
				break;
			}
		}		
	}
	private static void admin(Admin admin) {
		while(true) {
			System.out.println("1查询用餐人员，2：修改用餐人员信息，3：办卡，4：解除挂失，5：注销,6消费统计7:退出登录");
			int cho = sc.nextInt();
			if(cho==1) {//1查询用餐人员
				ArrayList<Card> list = ad.lookcard();
				if(list!=null) {
					list.forEach(s->System.out.println(s));
				}
			}else if(cho==2) {//2：修改用餐人员信息
				System.out.println("1:");
			}else if(cho==3) {//3：：添加用餐人员
				System.out.println("请输入卡号：");
				int id = sc.nextInt();
				System.out.print("请输入用餐人员编号");
				int cNumber = sc.nextInt();
				System.out.println("请输入密码");
				int pwd = sc.nextInt();
				System.out.println("请输入银行账户：");
				int bandId = sc.nextInt();
				Card card = new Card(id, cNumber, 0, pwd, true, bandId);
				int flag = ad.addcard(card);
				if(flag>0) {
					System.out.println("添加成功"+card.toString());
				}
			}else if(cho==4) {//4：取消挂失
				System.out.println("请输入卡号：");
				int id = sc.nextInt();
				int flag = ad.notLoss(id);
				if(flag>0) {
					System.out.println("成功解除挂失状态");
				}else {
					System.out.println("操作失败");
				}
			}else if(cho==5){//，5：注销
				System.out.println("请输入用餐人卡号：");
				int id = sc.nextInt();
				int flag = ad.delete(id);
				if(flag>0) {
					System.out.println("该用户已注销");
				}else {
					System.out.println("注销失败");
				}
			}else if(cho==6) {//，6：消费统计
				while(true) {
					System.out.println("1:日消费总和，2：月消费总和，3：季度消费总和，4：年消费总和,5:返回上一步");
					int ch = sc.nextInt();
					int money = 0;
					if(ch==1) {
						money =ad.getmoney(ch);
						System.out.println("日消费总和："+money);
					}else if(ch==2) {
						money =ad.getmoney(ch);
						System.out.println("月消费总和："+money);
					}else if(ch==3) {
						money =ad.getmoney(ch);
						System.out.println("本季度消费总和："+money);
					}else if(ch==4) {
						money =ad.getmoney(ch);
						System.out.println("年消费总和："+money);
					}else {
						break;
					}
				}				
			}else {//7:返回上一级
				break;
			}
		}
	}
}
