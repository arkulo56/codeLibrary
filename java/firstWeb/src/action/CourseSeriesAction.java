package action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.SeriesDao;
import dao.SeriesItemDao;
import model.CourseSeries;
import model.CourseSeriesItem;

public class CourseSeriesAction {
	//主表数据插入
	public int addSeries(CourseSeries cs) throws SQLException
	{
		SeriesDao shd = new SeriesDao();	
		return shd.add(cs);
	}
	//读取系列数据
	public List<CourseSeries> get(List<Map> wh) throws SQLException
	{
		SeriesDao shd = new SeriesDao();	
		return shd.get(wh);
	}
	
}
