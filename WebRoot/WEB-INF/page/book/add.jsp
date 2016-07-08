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

		<title>内容添加</title>

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
			action="book/save.html" enctype="multipart/form-data">
			<p class="dqgongneng"> 
				书籍基本信息
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<font color="red"> ${addf}</font>
			</p>
			<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0" >
				<tr>
					<td bgcolor="#F4F5F9" >
						书名
					</td>
					<td>
						<input type="text" name="title" value="${dhl.title}"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
						SN号
					</td>
					<td>
						<input type="text" name="sn" value="${dhl.sn}"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
						馆藏数目
					</td>
					<td>
						<input type="text" name="totalcopy" value="${dhl.totalcopy}"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
						可借数目
					</td>
					<td>
						<input type="text" name="leftcopy" value="${dhl.leftcopy}"/>
					</td>
				</tr>				
				</table>

			<input type="hidden" name="id" value="${dhl.id}">
			<br />
			<br />
			<div style="text-align : center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="提交" class="tjan">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" class="tjan" onclick="javascript:history.go(-1);">
			<br />
			<br />
			</div>
		</form>		
	</body>
</html>
