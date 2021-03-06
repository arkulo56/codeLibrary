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
import model.CourseSeries;
import model.CourseSeriesItem;

public class SeriesDao {
	private Connection con;
	private Statement st;
	//初始化
	public SeriesDao()
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
	//添加
	public int add(CourseSeries csi) throws SQLException
	{
		String sql = "insert into courseSeries values(null,?,?)";
		PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, csi.getClassDate());
		pst.setInt(2, csi.getClassLevel());
		pst.executeUpdate();
		
		ResultSet res = pst.getGeneratedKeys();
		if(res.next())
		{
			return res.getInt(1);
		}
		return 0;
	}	
	//通用的get
	public List<CourseSeries> get(List<Map> wh) throws SQLException
	{
		String sql = "select * from courseSeries where 1=1";
		for(int i=0;i<wh.size();i++)
		{
			sql = sql+" "+wh.get(i).get("pre")+" "+wh.get(i).get("field")+wh.get(i).get("relation")+wh.get(i).get("value");
		}
		
		//System.out.println(sql);		
		ResultSet res = st.executeQuery(sql);
		List<CourseSeries> r = new ArrayList();
		while(res.next())
		{
			CourseSeries n = new CourseSeries();
			n.setId(res.getInt("id"));
			n.setClassDate(res.getDate("classDate").toString());		
			r.add(n);
		}
		return r;
	}	
}
