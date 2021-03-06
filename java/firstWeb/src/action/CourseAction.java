package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CourseDao;
import model.Course;
import model.CourseSeries;
import model.CourseSeriesItem;

public class CourseAction {
	
	public boolean addNewCourse(Course cr) throws SQLException
	{
		//初始化action
		CourseSeriesAction csa = new CourseSeriesAction();
		CourseItemAction cia = new CourseItemAction();
		
		//读取冲突的课程
		List<Course> cst = this.get(cr);//这里是读取冲突课程
		List<Integer> xx = new ArrayList();
		for(int i=0;i<cst.size();i++)
		{
			xx.add(cst.get(i).getId());
		}		
		List<CourseSeriesItem> cs = cia.getCommon(cr, xx);//读取系列附表，返回那些不能组合的seriesId
		
		//插入课程数据
		int courseId = this.addCourse(cr);
		cr.setId(courseId);
		
		//读取主表可以组合的系列
		//把附表中读取到的系列编号(不能组合的)在这里做not in条件
		String ni = "(0";
		for(int i=0;i<cs.size();i++)
		{
			ni += ","+cs.get(i).getSeriesId();
		}
		ni += ")";
		List<Map> tt = new ArrayList();
		Map mm = new HashMap();
		mm.put("pre", "and");
		mm.put("field", "id");
		mm.put("relation", " not in ");
		mm.put("value", ni);
		tt.add(mm);
		Map xxb = new HashMap();
		xxb.put("pre", "and");
		xxb.put("field", "classLevel");
		xxb.put("relation", "=");
		xxb.put("value", cr.getClassLevel());
		tt.add(xxb);
		List<CourseSeries> csRes = csa.get(tt);
			
		//先添加一个单次的系列和item
		int courseSeriesId = this._setSeries(cr.getClassDate(),cr.getClassLevel());
		this._setItem(cr.getClassDate(), cr.getId(), courseSeriesId);
		//循环添加
		for(int i=0;i<csRes.size();i++)
		{
			//生成系列主表
			int csId = this._setSeries(cr.getClassDate(),cr.getClassLevel());
			//查询系列附表的所有元素
			List<Map> wh = new ArrayList();
			Map m = new HashMap();
			m.put("pre", "and");
			m.put("field", "seriesId");
			m.put("relation", "=");
			m.put("value", csRes.get(i).getId());
			wh.add(m);
			List<CourseSeriesItem> res = cia.getCommon(wh);
			//先录入一条自己
			this._setItem(cr.getClassDate(), cr.getId(), csId);
			for(int j=0;j<res.size();j++)
			{
				//重复录入取到的其他课程系列元素
				this._setItem(cr.getClassDate(), res.get(j).getCourseId(), csId);
			}
		}

		
		return true;
	}
	private int _setSeries(String dt,int classLevel) throws SQLException
	{
		//初始化action
		CourseSeriesAction csa = new CourseSeriesAction();
		//添加单独的一个系列主表
		CourseSeries newCsa = new CourseSeries();	
		newCsa.setClassDate(dt);
		newCsa.setClassLevel(classLevel);
		return csa.addSeries(newCsa);
	
	}
	private void _setItem(String dt,int courseId,int courseSeriesId) throws SQLException
	{
		CourseItemAction cia = new CourseItemAction();
			
		//添加单独的一个系列附表
		CourseSeriesItem csi = new CourseSeriesItem();
		csi.setClassDate(dt);
		csi.setCourseId(courseId);
		csi.setSeriesId(courseSeriesId);
		csi.setSortCourse(0);
		cia.addItem(csi);
	}
	
	//插入主表数据
	public int addCourse(Course cr) throws SQLException
	{
		CourseDao hd = new CourseDao();		
		return hd.add(cr);
	}	
	
	//查询冲突课程
	private List<Course> get(Course cr) throws SQLException
	{		
		CourseDao hd = new CourseDao();	
		List<Map> wh = new ArrayList();
		//开始时间小于当前课程结束时间
		Map k = new HashMap();
		k.put("pre", "and");
		k.put("field", "startTime");
		k.put("relation", "<");
		k.put("value", "'"+cr.getEndTime()+"'");
		wh.add(k);
		//结束时间大于当前课程开始时间
		Map e = new HashMap();
		e.put("pre", "and");
		e.put("field", "endTime");
		e.put("relation", ">");
		e.put("value", "'"+cr.getStartTime()+"'");
		wh.add(e);
		//开课日期相等
		Map ed = new HashMap();
		ed.put("pre", "and");
		ed.put("field", "classDate");
		ed.put("relation", "=");
		ed.put("value", "'"+cr.getClassDate()+"'");
		wh.add(ed);
		//年级相同
		Map nj = new HashMap();
		nj.put("pre", "and");
		nj.put("field", "classLevel");
		nj.put("relation", "=");
		nj.put("value", cr.getClassLevel());
		wh.add(nj);
		
		return hd.get(wh);		
	}
	//普通查询
	public List<Course> get(List<Map> wh) throws SQLException
	{
		CourseDao hd = new CourseDao();	
		return hd.get(wh);
	}

}
