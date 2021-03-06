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
import model.CourseSeries;
import model.CourseSeriesItem;

public class SeriesItemDao {
	private Connection con;
	private Statement st;
	//初始化
	public SeriesItemDao()
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
	public List<CourseSeriesItem> get(List<Map> wh) throws SQLException
	{
		String sql = "select courseId,seriesId from courseSeriesItem where 1=1";
		for(int i=0;i<wh.size();i++)
		{
			//System.out.println(wh.get(i).keySet());
			sql = sql+" "+wh.get(i).get("pre")+" "+wh.get(i).get("field")+wh.get(i).get("relation")+wh.get(i).get("value");
		}
		
		sql += " group by seriesId,courseId";
		//System.out.println(sql);		
		ResultSet res = st.executeQuery(sql);
		List<CourseSeriesItem> r = new ArrayList();
		while(res.next())
		{
			CourseSeriesItem n = new CourseSeriesItem();
			//n.setId(res.getInt("id"));
			//n.setClassDate(res.getDate("classDate").toString());
			n.setCourseId(res.getInt("courseId"));
			n.setSeriesId(res.getInt("seriesId"));
			//n.setSortCourse(res.getInt("sortCourse"));			
			r.add(n);
		}
		return r;
	}	
	//添加
	public int add(CourseSeriesItem csi) throws SQLException
	{
		String sql = "insert into courseSeriesItem values(null,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, csi.getClassDate());
		pst.setInt(2, csi.getCourseId());
		pst.setInt(3, csi.getSeriesId());
		pst.setInt(4, csi.getSortCourse());
		pst.executeUpdate();
		
		ResultSet res = pst.getGeneratedKeys();
		if(res.next())
		{
			return res.getInt(1);
		}
		return 0;
	}
	
	//通用的get
	public List<CourseSeriesItem> getCommon(List<Map> wh) throws SQLException
	{
		String sql = "select seriesId from courseSeriesItem where 1=1";
		for(int i=0;i<wh.size();i++)
		{
			sql = sql+" "+wh.get(i).get("pre")+" "+wh.get(i).get("field")+wh.get(i).get("relation")+wh.get(i).get("value");
		}
		
		//System.out.println(sql);		
		ResultSet res = st.executeQuery(sql);
		List<CourseSeriesItem> r = new ArrayList();
		while(res.next())
		{
			CourseSeriesItem n = new CourseSeriesItem();
			n.setId(res.getInt("id"));
			n.setClassDate(res.getDate("classDate").toString());
			n.setCourseId(res.getInt("courseId"));
			n.setSeriesId(res.getInt("seriesId"));
			n.setSortCourse(res.getInt("sortCourse"));			
			r.add(n);
		}
		return r;
	}
	
}
