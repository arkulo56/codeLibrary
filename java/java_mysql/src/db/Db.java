package db;
import java.sql.*;

public class Db {
	private static String url = "jdbc:mysql://192.168.99.100:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false";
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
	
	public static void main(String[] args){
		try{

			//step3:创建语句
			Statement stt = con.createStatement();
			//3.1查询
			String sql = "select * from goddess";
			ResultSet res = stt.executeQuery(sql);
			while(res.next())
			{
				System.out.println("记录："+res.getInt("id")+" "+res.getString("name")+" "+res.getInt("age")+" "+res.getString("phone")+" "+res.getDate("addtime"));
			}
			//3.2添加
			//stt.executeQuery("set names utf8");
//			String sql = "insert into goddess values(null,'娜娜',30,'18645542233')";
//			int rs = stt.executeUpdate(sql);
//			if(rs!=-1)
//			{
//				System.out.println("插入成功！");
//			}else
//			{
//				System.out.println("插入失败!");
//			}
//			
			//3.3更新
//			String sql = "update goddess set age=28";
//			int rs = stt.executeUpdate(sql);
//			System.out.println("更新数量:"+rs);
//			if(rs!=-1)
//			{
//				System.out.println("更新成功！");
//			}else
//			{
//				System.out.println("更新失败!");
//			}			
			

		}catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}
