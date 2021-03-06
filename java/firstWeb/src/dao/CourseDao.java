package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.MysqlDrive;
import model.Course;

public class CourseDao {
	private Connection con;
	private Statement st;
	//初始化
	public CourseDao()
	{
		con = MysqlDrive.getCon();
		try
		{
			st = con.createStatement();
		}catch(SQLException e)
		{
			System.out.println("statement初始化失败");
		}
	}
	//查询
	public List<Course> get(List<Map> wh) throws SQLException
	{
		String sql = "select * from course where 1=1";
		for(int i=0;i<wh.size();i++)
		{
			//System.out.println(wh.get(i).keySet());
			sql = sql+" "+wh.get(i).get("pre")+" "+wh.get(i).get("field")+wh.get(i).get("relation")+wh.get(i).get("value");
		}	
		//System.out.println(sql);		
		ResultSet res = st.executeQuery(sql);
		List<Course> r = new ArrayList();
		while(res.next())
		{
			Course n = new Course();
			n.setId(res.getInt("id"));
			n.setName(res.getString("name"));
			n.setClassDate(res.getString("classDate"));
			n.setStartTime(res.getString("startTime"));
			n.setEndTime(res.getString("endTime"));
			n.setTeacher(res.getString("teacher"));
			n.setClassNumber(res.getInt("classNumber"));
			n.setStudentNumber(res.getInt("studentNumber"));
			n.setPrice(res.getFloat("price"));
			n.setStatus(res.getInt("status"));
			n.setSeason(res.getString("season"));
			n.setClassLevel(res.getInt("classLevel"));
			n.setClassGategory(res.getInt("classGategory"));
			
			r.add(n);
		}
		return r;
	}
	//添加
	public int add(Course nc) throws SQLException
	{
		String sql = "insert into course values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, nc.getName());
		pst.setString(2, nc.getClassDate());
		pst.setString(3, nc.getStartTime());
		pst.setString(4, nc.getEndTime());
		pst.setString(5, nc.getTeacher());
		pst.setInt(6, nc.getClassNumber());
		pst.setInt(7, nc.getStudentNumber());
		pst.setFloat(8, nc.getPrice());
		pst.setInt(9, nc.getStatus());
		pst.setString(10, nc.getSeason());
		pst.setInt(11, nc.getClassLevel());
		pst.setInt(12, nc.getClassGategory());
		
		pst.executeUpdate();
		ResultSet res = pst.getGeneratedKeys();
		if(res.next())
		{
			return res.getInt(1);
		}
		return 0;
	}
	
//	public boolean del(int id)
//	{
//		
//	}
//	
}
