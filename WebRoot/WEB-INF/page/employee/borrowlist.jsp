<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>商品编辑</title>
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
			员工借书情况
		</p>
		<br />
			<table width="96%" cellpadding="0" cellspacing="0" class="taba" 	border="0">
		<tr>
			<td bgcolor="#F4F5F9" width="150">员工姓名</td>
			<td>
				${basic.name }
			</td>
		</tr>
		<tr>
			<td bgcolor="#F4F5F9" width="150">员工工号</td>
			<td>
				${basic.enumber }
			</td>
		</tr>
		
							
						
		<tr>
			<td bgcolor="#F4F5F9">在借图书</td>
			<td>
			<c:if test="${fn:length(list) gt 0}">
				<table frame="void" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>书籍标题</th>
						<th>书籍SN</th>
						 <th>借书时间</th>						
					</tr>
					<c:forEach items="${list}" var="v" varStatus="index">
						<tr>
							<td>${v.bid.title }</td>
							<td>${v.bid.sn }</td>
							<td><fmt:formatDate value="${v.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
						</tr>
					</c:forEach>
				</table>
				</c:if>	
				<c:if test="${fn:length(list) eq 0}">
					当前未借图书
				</c:if>
			</td>
		</tr>
				
		
	</table>
	<br/>
	</body>
	
</html>
