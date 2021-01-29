package com.fms.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc {
	static Properties p = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Connection con = null; 
	static {
		try (InputStream is = Jdbc.class.getResourceAsStream("/peizhi.properties")){
			p = new Properties();
			p.load(is);
			String classname = p.getProperty("classname");
			Class.forName(classname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static  Connection getcon() {		
		try {
			con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("pwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("获得连接");
		return con;		
	}
	
	public static void getclose() {
		if(rs!=null) {
			try {
				rs.close();
				System.out.println("关闭连接");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void getclose(Connection con,PreparedStatement ps ,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
				System.out.println("关闭连接");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static  ResultSet getrs(String sql) {
		try {
			con = getcon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public static  ResultSet getrs(String sql,String...str) {
		try {
			con = getcon();
			ps = con.prepareStatement(sql);
			for(int i=1;i<=str.length;i++) {
				ps.setString(i, str[i-1]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public static  ResultSet getrs(String sql,int...in) {
		try {
			con = getcon();
			ps = con.prepareStatement(sql);
			for(int i=1;i<=in.length;i++) {
				ps.setInt(i, in[i-1]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public static int getflag(String sql,int...in) {
		int flag = 0;
		try {
			con = getcon();
			ps = con.prepareStatement(sql);
			for(int i =1;i<=in.length;i++) {
				ps.setInt(i, in[i-1]);
			}
			flag = ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;		
	}
}
