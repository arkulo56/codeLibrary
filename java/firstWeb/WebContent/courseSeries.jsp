<%@page import="action.CourseItemAction"%>
<%@page import="model.*"%>
<%@page import="action.CourseAction"%>
<%@page import="java.util.*"%>
<%@page import="action.CourseSeriesAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程系列列表</title>
<style type="text/css">
li{line-height: 200%}
</style>
</head>
<body>

<%
CourseAction ca = new CourseAction();
CourseSeriesAction csa = new CourseSeriesAction();
CourseItemAction cia = new CourseItemAction();
int courseId = Integer.parseInt(request.getParameter("courseId"));

//查询课程数据
List<Map> wh = new ArrayList();
Map k = new HashMap();
k.put("pre", "and");
k.put("field", "id");
k.put("relation","=");
k.put("value",courseId);
wh.add(k);
List<Course> courseRes = ca.get(wh);


//查询课程推荐系列
List<Map> wh1 = new ArrayList();
//时间
Map k1 = new HashMap();
k1.put("pre", "and");
k1.put("field", "classDate");
k1.put("relation","=");
k1.put("value","'"+courseRes.get(0).getClassDate()+"'");
wh1.add(k1);
//年级
Map k2 = new HashMap();
k2.put("pre", "and");
k2.put("field", "classLevel");
k2.put("relation", "=");
k2.put("value", courseRes.get(0).getClassLevel());
wh1.add(k2);
List<CourseSeries> courseSeriesRes = csa.get(wh1);
%>
<h1>推荐课程系列 <%=courseRes.get(0).getClassDate() %></h1>
<h4><a href="＃" onclick="javascript:history.back(-1);">返回上一层</a></h4>
<hr>
<%
for(int i=0;i<courseSeriesRes.size();i++)
{
	int seriesId = courseSeriesRes.get(i).getId();
	
	//查询课程系列详情
	List<Map> whItem = new ArrayList();
	Map t = new HashMap();
	t.put("pre", "and");
	t.put("field", "seriesId");
	t.put("relation", "=");
	t.put("value", seriesId);
	whItem.add(t);
	List<CourseSeriesItem> resItem = cia.getCommon(whItem);
	int sign = 0;
	String ot = "<ul>";
	for(int j=0;j<resItem.size();j++)
	{
		//课程系列详情（课程详情）
		List<Map> item = new ArrayList();
		Map m = new HashMap();
		m.put("pre", "and");
		m.put("field", "id");
		m.put("relation","=");
		m.put("value",resItem.get(j).getCourseId());
		item.add(m);
		List<Course> rr = ca.get(item);
		Course r = rr.get(0);
		String[] startTime = r.getStartTime().split(" ");
		String[] endTime = r.getEndTime().split(" ");
		String li = "<li>";
		if(r.getId()==courseId)
		{
			sign = 1;
			li = "<li style='color:green'>";
		}
		
		ot += li+r.getId()+" / "+r.getName()+" / "+startTime[1]+" / "+endTime[1]+" / ¥"+r.getPrice()+"</li>";

	}
	ot += "</ul>";
	if(sign==1)
	{
		out.println("课程系列编号："+seriesId);
		out.println(ot);
		out.println("<hr>");
	}
	
}
%>







</body>
</html>