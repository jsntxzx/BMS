<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><spring:message code="book-AddNewBook"></spring:message></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		td > input{
			line-height : 20px ;
			width: 98% ;
		}

	</style>
	

	</head>

	<body>
		<form id="myform" name="myform" method="post"
			action="employee/save.html" enctype="multipart/form-data">
			<p class="dqgongneng"> 
			<spring:message code="empolyee-BasicEmployeeInfo"></spring:message>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<font color="red"> ${addf}</font>
			</p>
			<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0" >
				<tr>
					<td bgcolor="#F4F5F9" >
					<spring:message code="empolyee-Name"></spring:message>
					</td>
					<td>
						<input type="text" name="name" value="${dhl.name }"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
					<spring:message code="empolyee-ID"></spring:message>
					</td>
					<td>
						<input type="text" name="enumber" value="${dhl.enumber}"/>
					</td>
				</tr>				
				</table>

			<input type="hidden" name="id" value="${dhl.id}">
			<br />
			<br />
			<div style="text-align : center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value=<spring:message code="empolyee-Submit"></spring:message> class="tjan">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value=<spring:message code="empolyee-Return"></spring:message> class="tjan" onclick="javascript:history.go(-1);">
			<br />
			<br />
			</div>
		</form>		
	</body>
</html>
