<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title><spring:message code="empolyee-BorrowBook"></spring:message></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<style>
.anniuweizhi a div {
	width: 120px;
}

.anniuweizhi .select_obj {
	margin-top: 15px;
	float: left;
}

.anniuweizhi input {
	margin-top: 15px;
	float: left;
}
</style>
	</head>
	<body>
		<p class="dqgongneng">
			<spring:message code="empolyee-EmployeeManage"></spring:message>
		</p>
		<br />


		<form action="employee/list.html" id="myform" method="post">
			<div class="anniuweizhi">
				<select class="select_obj" name="key">
					<option value="">
						<spring:message code="empolyee-PleaseSelect"></spring:message>
					</option>
					<option value="name%"
						<c:if test="${key eq 'name%'}">selected="selected"</c:if>>
						<spring:message code="empolyee-Name"></spring:message>
					</option>
					<option value="enumber%"
						<c:if test="${key eq 'enumber%'}">selected="selected"</c:if>>
						<spring:message code="empolyee-ID"></spring:message>
					</option>					
				</select>
				<input type="text" class="sousuo" name="value" value="${value}">
				<input type="image" src="image/sousuo.png" />
				<a href="employee/add.html"><div>
				<spring:message code="empolyee-AddNewEmployee"></spring:message>
					</div>
				</a>
			</div>
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0">
				<tr>
					<th bgcolor="#F4F5F9" width="60">
						<spring:message code="empolyee-Sequence"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="empolyee-Name"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="empolyee-ID"></spring:message>
					</th>
					<th bgcolor="#F4F5F9" width="140">
						<spring:message code="empolyee-Operation"></spring:message>
					</th>
				</tr>
				<c:forEach items="${paging.list}" var="v" varStatus="index">
					<tr>
						<td align="center">
							${index.index+1}
						</td>						
						<td style="text-align: center">
							${v.name }
						</td>
						<td style="text-align: center">
							${v.enumber }
						</td>					
						<td style="text-align: center">
							<a href="employee/update/${v.id}.html"><img
									src="image/update.png" title=<spring:message code="empolyee-Modify"></spring:message> />
							</a><%-- &nbsp;							
							<a href="employee/delete/${v.id}.html" onclick="return confirm(<spring:message code="empolyee-Warnning"></spring:message>)"> <img src="image/del.png"
									title=<spring:message code="empolyee-Delete"></spring:message> />
							</a> --%>&nbsp;
							<a href="employee/listborrow/${v.id}.html"> 
								<img src="image/zhishi.png" title=<spring:message code="empolyee-LookForBorrowed"></spring:message> />
							</a>	
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="fenleiDiv">
				<p:outpage pageSize="${paging.pageSize}"
					totalPage="${paging.totalPage}" curPage="${paging.curPage}" />
			</div>
			<br />
		</form>
	</body>
	
</html>
