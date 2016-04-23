<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.*,action.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>添加课程</h1>
<h4><a href="index.jsp">返回主页</a></h4>
<hr>
<form action="addDeal.jsp" method="post" name="courseForm">
<table>
<tr>
	<td>课程名称：</td>
	<td><input type="text" name="name" /></td>
</tr>
<tr>
	<td>上课日期：</td>
	<td><input type="text" name="classDate" /></td>
</tr>
<tr>
	<td>上课时间：</td>
	<td><input type="text" name="startTime" /></td>
</tr>
<tr>
	<td>下课时间：</td>
	<td><input type="text" name="endTime" /></td>
</tr>
<tr>
	<td>教师姓名：</td>
	<td><input type="text" name="teacher" /></td>
</tr>
<tr>
	<td>总次数：</td>
	<td><input type="text" name="classNumber" /></td>
</tr>
<tr>
	<td>价格：</td>
	<td><input type="text" name="price" /></td>
</tr>
<tr>
	<td>季节：</td>
	<td><input type="text" name="season" /></td>
</tr>
<tr>
	<td>年级：</td>
	<td>
	<select name="classLevel">
		<option value="1">小学1年级</option>
		<option value="2">小学2年级</option>
		<option value="3">小学3年级</option>
		<option value="4">小学4年级</option>
		<option value="5">小学5年级</option>
		<option value="6">小学6年级</option>
		<option value="7">初中1年级</option>
		<option value="8">初中2年级</option>
		<option value="9">初中3年级</option>
		<option value="10">高中1年级</option>
		<option value="11">高中2年级</option>
		<option value="12">高中3年级</option>
	</select>
	</td>
</tr>
<tr>
	<td>课程类型：</td>
	<td>
	<select name="classGategory">
		<option value="1">语文</option>
		<option value="2">数学</option>
		<option value="3">英语</option>
		<option value="4">物理</option>
		<option value="5">化学</option>
		<option value="6">生物</option>
		<option value="7">地理</option>
	</select>
	</td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="提交" /></td>
</tr>
</table>
</form>
</body>
</html>