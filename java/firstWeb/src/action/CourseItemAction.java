package action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.SimpleFormatter;

import dao.CourseDao;
import dao.SeriesItemDao;
import model.Course;
import model.CourseSeriesItem;

public class CourseItemAction {
	//附表数据插入
	public int addItem(CourseSeriesItem csi) throws SQLException
	{
		SeriesItemDao hd = new SeriesItemDao();
		return hd.add(csi);
	}
	
	//查询数据group by
	public List<CourseSeriesItem> get(Course cr,List<Integer> notin) throws SQLException
	{
		String ni="(0";
		for(int i=0;i<notin.size();i++)
		{
			ni += ","+notin.get(i);
		}
		ni += ")";
		
		List<Map> wh = new ArrayList();
		Map k = new HashMap();
		k.put("pre", "and");
		k.put("field", "classDate");
		k.put("relation", "=");
		k.put("value", "'"+cr.getClassDate()+"'");
		wh.add(k);
		Map m = new HashMap();
		m.put("pre", "and");
		m.put("field", "seriesId");
		m.put("relation", " not in ");
		m.put("value", ni);
		wh.add(m);
		
		SeriesItemDao hd = new SeriesItemDao();
		return hd.get(wh);
		
	}
	
	//普通数据获取
	public List<CourseSeriesItem> getCommon(Course cr,List<Integer> in) throws SQLException
	{
		String ni="(0";
		for(int i=0;i<in.size();i++)
		{
			ni += ","+in.get(i);
		}
		ni += ")";
		
		List<Map> wh = new ArrayList();
		Map m = new HashMap();
		m.put("pre", "and");
		m.put("field", "courseId");
		m.put("relation", " in ");
		m.put("value", ni);
		wh.add(m);
		
		SeriesItemDao hd = new SeriesItemDao();
		return hd.get(wh);
	}
	
	//最普通的get
	public List<CourseSeriesItem> getCommon(List<Map> wh) throws SQLException
	{
		SeriesItemDao hd = new SeriesItemDao();
		return hd.get(wh);
	}
}
