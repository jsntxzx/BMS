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

		<title><spring:message code="manage-BorrowBook"></spring:message></title>

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
	<script type="text/javascript">
  		var string = "<spring:message code='manage-NotFound' javaScriptEscape='true' />"
	</script>
	</head>

	<body>
		<form id="myform" name="myform" method="post"
			action="manage/borrowcheck.html" >
			<p class="dqgongneng"> 
				<spring:message code="manage-BorrowBookAction"></spring:message>
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
					<spring:message code="manage-EmployeeName"></spring:message>
					</td>
					<td>
						<select name="eid" class="chosen-select" style="width: 500px" >
							<option value="">
										<spring:message code="manage-PleaseSelect"></spring:message>
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
						<spring:message code="manage-BookTitle"></spring:message>
					</td>
					<td>
						<select name="bid" class="chosen-select" style="width: 500px" >
							<option value="">
										<spring:message code="manage-PleaseSelect"></spring:message>
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
			<input type="submit" value=<spring:message code="manage-Submit"></spring:message> class="tjan">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value=<spring:message code="manage-Return"></spring:message> class="tjan" onclick=javascript:history.go(-1);;>
			<br />
			<br />
			</div>
		</form>	

		<script src="js/chosen.jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
	$(function() {
		$('.chosen-select').chosen( {
			'search_contains' : true,
			no_results_text : string
		});
	});
</script>
	</body>
</html>
