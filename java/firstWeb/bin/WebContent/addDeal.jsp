<%@page import="action.CourseAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="newCourse" class="model.Course" scope="page" />
<jsp:setProperty property="*" name="newCourse"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
newCourse.setStatus(0);
CourseAction hd = new CourseAction();
if(hd.addNewCourse(newCourse))
{
%>

<script type="text/javascript">
alert("添加成功！");
window.location.href="addCourse.jsp";
</script>


<%
}
%>

</body>
</html>









