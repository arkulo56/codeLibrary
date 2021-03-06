package dao;

import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import db.MysqlDrive;

public class StudentDao {
	private Connection con;
	private Statement st;
	
	public StudentDao()
	{
		con = MysqlDrive.getCon();
		try
		{
			st = con.createStatement();
		}catch(SQLException e)
		{
			System.out.println("数据库初始化失败");
		}
	}
	//获取学生数据
	public List<Student> get(List<Map<String,String>> wh) throws SQLException
	{
		List<Student> result = new ArrayList();
		String sql = "select * from student where 1=1";
		for(int i =0;i<wh.size();i++)
		{
			sql = sql+" and "+wh.get(i).get("field")+wh.get(i).get("relation")+wh.get(i).get("value");
		}
		ResultSet res = st.executeQuery(sql);
		while(res.next())
		{
			Student sd = new Student();
			sd.setId(res.getInt("id"));
			sd.setName(res.getString("name"));
			sd.setClassLevel(res.getInt("classLevel"));
			sd.setGradeLevel(res.getInt("gradeLevel"));
			
			result.add(sd);
		}
		
		return result;
	}
}
