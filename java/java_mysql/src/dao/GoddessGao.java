package dao;

import model.Goddess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import db.Db;

public class GoddessGao {
	private Connection con;
	private Statement st;
	//初始化
	public GoddessGao()
	{
		con = Db.getCon();
		try{
			st = con.createStatement();
		}catch(SQLException e)
		{
			System.out.println("语句初始化错误");
		}
	}
	//添加
	public boolean add(Goddess gd){
		try{
		String sql = "insert into goddess(name,age,phone,addtime) values(?,?,?,?)";
		
		java.sql.PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,gd.getName());
		pst.setInt(2, gd.getAge());
		pst.setString(3, gd.getPhone());
		pst.setDate(4, new Date(gd.getAddtime().getTime()));
		
		if(pst.executeUpdate()!=-1)
		{
			return true;
		}
		
		}catch(SQLException e)
		{
			System.out.println("sql语句执行错误:");
			System.out.println(e);
		}		
		return false;
	}
	//修改
	public int set(Goddess gd) throws SQLException{
		String sql = "update goddess set "
				+ "name=?,age=?,phone=?,addtime=? "
				+ "where id=? limit 1";
		java.sql.PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, gd.getName());
		pst.setInt(2, gd.getAge());
		pst.setString(3, gd.getPhone());
		pst.setDate(4, new Date(gd.getAddtime().getTime()));
		pst.setInt(5, gd.getId());
		return pst.executeUpdate();
	}
	//删除
	public boolean del(int id) throws SQLException{
		String sql = "delete from goddess "
				+ "where id=? limit 1";
		java.sql.PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1,id);
		int rs = pst.executeUpdate();		
		if(rs>0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	//但条记录获取
	public Goddess get(List<Map<String, String>> wh) throws SQLException{
		Goddess gd = new Goddess();
		String sql = "select * from goddess where 1=1";
		for(int i=0;i<wh.size();i++){
			sql = sql+" and "+wh.get(i).get("name")+wh.get(i).get("relation")+wh.get(i).get("value");
		}
		ResultSet res = st.executeQuery(sql);
		if(res.next())
		{
			gd.setName(res.getString("name"));
			gd.setAge(res.getInt("age"));
			gd.setPhone(res.getString("phone"));
			gd.setId(res.getInt("id"));
			gd.setAddtime(res.getDate("addtime"));
		}

		return gd;
	}
	
	public List<Goddess> get() throws SQLException{
		List<Goddess> li = new ArrayList();
		String sql = "select * from goddess";
		ResultSet res = st.executeQuery(sql);
		while(res.next())
		{
			Goddess ng = new Goddess();
			ng.setId(res.getInt("id"));
			ng.setName(res.getString("name"));
			ng.setAge(res.getInt("age"));
			ng.setPhone(res.getString("phone"));
			ng.setAddtime(res.getDate("addtime"));
			li.add(ng);
		}
		//System.out.println(li.size());
		return li;
	}
	
	
}
