<%@page import="model.Course"%>
<%@page import="action.CourseAction"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程表</title>
<style type="text/css">
td{line-height: 200%}
</style>
</head>
<body>
<h1>课程表</h1>
<a href="index.jsp">返回首页</a>
<hr>

<table border="1">
<tr>
<td>课程编号</td>
<td>课程名称</td>
<td>上课日期</td>
<td>上课时间</td>
<td>下课时间</td>
<td>教师</td>
<td>总次数</td>
<td>限报人数</td>
<td>价格</td>
<td>状态</td>
<td>季节</td>
<td>年级</td>
<td>课程类型</td>
<td>查看课程系列</td>
</tr>
<%
CourseAction ca = new CourseAction();
List wh = new ArrayList();
List<Course> res = ca.get(wh);
for(int i=0;i<res.size();i++)
{
	Course t = res.get(i);
%>
<tr>
<td><%=t.getId() %></td>
<td><%=t.getName() %></td>
<td><%=t.getClassDate() %></td>
<td><%=t.getStartTime() %></td>
<td><%=t.getEndTime() %></td>
<td><%=t.getTeacher() %></td>
<td><%=t.getClassNumber() %></td>
<td><%=t.getStudentNumber() %></td>
<td>¥<%=t.getPrice() %></td>
<td><%=t.getStatus() %></td>
<td><%=t.getSeason() %></td>
<td><%=t.getClassLevel() %></td>
<td><%=t.getClassGategory() %></td>
<td><a href="courseSeries.jsp?courseId=<%=t.getId() %>">查看课程系列</a></td>
</tr>





<%
}
%>
</table>
</body>
</html>