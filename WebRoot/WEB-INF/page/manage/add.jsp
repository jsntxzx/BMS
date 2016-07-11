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
		<link rel="stylesheet" href="css/chosen.css">
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
			action="manage/borrowcheck.html" >
			<p class="dqgongneng"> 
				借书操作 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<c:if test="${addf eq 1 }"><font color="red"><spring:message code="msg1"></spring:message></font></c:if>
				<c:if test="${addf eq 2 }"><font color="red"><spring:message code="msg2"></spring:message></font></c:if>
				<c:if test="${addf eq 3 }"><font color="red"><spring:message code="msg3"></spring:message></font></c:if>
				<c:if test="${addf eq 4 }"><font color="red"><spring:message code="msg4"></spring:message></font></c:if>
				<c:if test="${addf eq 5 }"><font color="blue"><spring:message code="msg5"></spring:message></font></c:if>
			</p>
			<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0" >
				<tr>
					<td bgcolor="#F4F5F9" width="180">
						员工姓名
					</td>
					<td>
						<select name="eid" class="chosen-select" style="width: 500px" >
							<option value="">
										请选择
								</option>
							<c:forEach items="${el}" var="v">
									<option value="${v.id}">
										${v.name }
									</option>
							</c:forEach>
						</select>	
					</td>
				</tr>
				<tr>
					<td bgcolor="#F4F5F9" width="180">
						书籍标题
					</td>
					<td>
						<select name="bid" class="chosen-select" style="width: 500px" >
							<option value="">
										请选择
								</option>
							<c:forEach items="${bl}" var="v">
									<option value="${v.id}">
										${v.title}
									</option>
							</c:forEach>
						</select>		
					</td>
				</tr>
			</table>
			<br />
			<br />
			<div style="text-align : center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="提交" class="tjan">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" class="tjan" onclick=javascript:history.go(-1);;>
			<br />
			<br />
			</div>
		</form>	

		<script src="js/chosen.jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
	$(function() {
		$('.chosen-select').chosen( {
			'search_contains' : true,
			no_results_text : '未搜索到'
		});
	});
</script>
	</body>
</html>
