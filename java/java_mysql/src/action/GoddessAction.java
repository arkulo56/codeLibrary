package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GoddessGao;
import model.Goddess;

public class GoddessAction {
	//添加女神
	public boolean addGoddess(Goddess gd)
	{
		GoddessGao tmp = new GoddessGao();
		
		if(tmp.add(gd))
		{
			return true;
		}
		
		return false;
	}
	//修改女神
	public int modifyGoddess(Goddess gd) throws SQLException
	{
		GoddessGao tmp = new GoddessGao();
		
		return tmp.set(gd);
	}
	//删除女神
	public boolean delGoddess(int id) throws SQLException
	{
		GoddessGao tmp = new GoddessGao();
		return tmp.del(id);
	}
	//获得一个女神的信息
	public Goddess getGoddess(List<Map<String, String>> m) throws SQLException
	{
		GoddessGao tmp = new GoddessGao();
//		List<Map<String, String>> wh = new ArrayList<Map<String, String>>();
//		Map<String, String> m = new HashMap<>();
//		m.put("name", "name");
//		m.put("relation", "=");
//		m.put("value", "'胡琳'");

		Goddess goddess = tmp.get(m);
		
		return goddess;
	}
	//获取批量女神信息
	public List<Goddess> getGoddessAll() throws SQLException
	{
		GoddessGao tmp = new GoddessGao();
		List<Goddess> res = tmp.get();
		return res;
	}
	
//	public static void main(String[] args) throws SQLException {
//		GoddessAction tmp = new GoddessAction();
//		Goddess newGoddes = new Goddess();
////		if(tmp.addGoddess(newGoddes))
////		{
////			System.out.println("添加女神成功！");
////		}else
////		{
////			System.out.println("添加女神失败！");
////		}
//		
////		System.out.println(tmp.modifyGoddess(newGoddes));
//
////		if(tmp.delGoddess(7))
////		{
////			System.out.println("添加女神成功！");
////		}else
////		{
////			System.out.println("添加女神失败！");
////		}
////		newGoddes = tmp.getGoddess();
////		System.out.println("女神的信息："+newGoddes.getName()+"/"+newGoddes.getPhone()+"/"+newGoddes.getAge());
////		List<Goddess> res = tmp.getGoddessAll();
////		for(int i=0;i<res.size();i++)
////		{
////			System.out.println("女神的信息："+res.get(i).getName()+"/"+res.get(i).getPhone()+"/"+res.get(i).getAge());
////		}
//	}
}
