package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDrive {
	private static String url = "jdbc:mysql://192.168.99.100:3306/course?useUnicode=true&characterEncoding=utf8&useSSL=false";
	private static String use = "root";
	private static String passwd = "123456";
	private static Connection con = null;
	
	static{
		try{
			//step1:动态加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//step2:连接数据库
			con = DriverManager.getConnection(url, use, passwd);
		}catch(ClassNotFoundException e)
		{
			System.out.println("加载mysql驱动失败");
		}catch(SQLException e)
		{
			System.out.println("连接数据库失败");
		}
	}
	
	public static Connection getCon()
	{
		return con;
	}

}
