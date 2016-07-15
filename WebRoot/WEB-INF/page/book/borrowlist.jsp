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
		<spring:message code="empolyee-BorrowStatus"></spring:message>
		</p>
		<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba" 	border="0">
		<tr>
			<td bgcolor="#F4F5F9" width="150"><spring:message code="book-BookTitle"></spring:message></td>
			<td>
				${basic.title }
			</td>
		</tr>
		<tr>
			<td bgcolor="#F4F5F9" width="150"><spring:message code="book-SNNumber"></spring:message></td>
			<td>
				${basic.sn }
			</td>
		</tr>
		<tr>
			<td bgcolor="#F4F5F9" width="150"><spring:message code="book-CopiesTotal"></spring:message></td>
			<td>
				${basic.totalcopy }
			</td>
		</tr>
		<tr>
			<td bgcolor="#F4F5F9" width="150"><spring:message code="book-CopiesAvailable"></spring:message></td>
			<td>
				${basic.leftcopy }
			</td>
		</tr>
							
						
		<tr>
			<td bgcolor="#F4F5F9"><spring:message code="empolyee-CurrentlyBorrowed"></spring:message></td>
			<td>
			<c:if test="${fn:length(list) gt 0}">
				<table frame="void" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th><spring:message code="empolyee-Name"></spring:message></th>
						<th><spring:message code="empolyee-ID"></spring:message></th>
						<th><spring:message code="empolyee-BorrowedTime"></spring:message></th>	
						<th><spring:message code="empolyee-Operation"></spring:message></th>						
					</tr>
					<c:forEach items="${list}" var="v" varStatus="index">
						<tr>
							<td>${v.eid.name }</td>
							<td>${v.eid.enumber }</td>
							<td><fmt:formatDate value="${v.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							<td><a href="manage/return/${v.id}.html"><img
									src="image/update.png" title="<spring:message code="manage-ReturnBook"></spring:message>" /></a>
							 </td>
						</tr>
					</c:forEach>
				</table>
				</c:if>	
				<c:if test="${fn:length(list) eq 0}">
					<spring:message code="empolyee-CurrentlyNotBorrowed"></spring:message>
				</c:if>
			</td>
		</tr>
				
		
	</table>
	<br/>
	</body>
	
</html>
