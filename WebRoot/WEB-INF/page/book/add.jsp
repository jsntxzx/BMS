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
			action="book/save.html" enctype="multipart/form-data">
			<p class="dqgongneng"> 
				<spring:message code="book-BasicBookInfo"></spring:message>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<c:if test="${addf eq 1 }"><font color="red"><spring:message code="book-error1"></spring:message></font></c:if>
					<c:if test="${addf eq 2 }"><font color="red"><spring:message code="book-error2"></spring:message></font></c:if>
			</p>
			<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0" >
				<tr>
					<td bgcolor="#F4F5F9" >
						<spring:message code="book-BookTitle"></spring:message>
					</td>
					<td>
						<input type="text" name="title" id="title" value="${dhl.title}"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
						<spring:message code="book-SNNumber"></spring:message>
					</td>
					<td>
						<input type="text" name="sn" id="sn" value="${dhl.sn}" />
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" >
						<spring:message code="book-CopiesTotal"></spring:message>
					</td>
					<td>
						<input type="text" name="totalcopy" id="totalcopy" value="${dhl.totalcopy}"/>
					</td>
				</tr>		
				</table>

			<input type="hidden" name="id" value="${dhl.id}">
			<br />
			<br />
			<div style="text-align : center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value=<spring:message code="book-Submit"></spring:message> class="tjan">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value=<spring:message code="book-Return"></spring:message> class="tjan" onclick="javascript:history.go(-1);">
			<br />
			<br />
			</div>
		</form>	
		<script src="js/jquery.validate.js"></script>
		<c:if test="${cookie.myAppLocaleCookie.value eq 'zh_CN' }">
			<script src="js/localization/messages_zh.js"></script>
		</c:if>
		<script>
		$().ready(function() {
			$("#myform").validate({
			rules: {
				title: {
					required: true,
					minlength: 2,
					maxlength: 100
				},
				sn: {
					required: true,
					minlength: 5,
					maxlength: 100
				},
			
				totalcopy: {
					required: true,
					digits: true,
					range: [ 1, 1000 ]
				}
				}
			});
		});
		</script>
	</body>
</html>
