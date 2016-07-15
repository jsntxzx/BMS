<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title><spring:message code="manage-BorrowBook"></spring:message></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="css/chosen.css">
	<style>
	.anniuweizhi div{
		width : 100px ;
	}
	.anniuweizhi .select_obj {		
		margin-top : 15px ; 
		float:left ;
	}
	.anniuweizhi input {
		margin-top : 15px ; 
		float:left ;
	}
	
	.search{
		margin-left:20px;
	}
	</style>
	<script type="text/javascript">
  		var string = "<spring:message code='manage-NotFound' javaScriptEscape='true' />"
	</script>
	</head>
	<body>
		<p class="dqgongneng"> 
			<spring:message code="manage-BorrowRecord"></spring:message>
		</p>
		<br />

		<form action="manage/list.html" id="myform" method="post">
			<div class="anniuweizhi">			
		 		<a href="manage/add.html"><div><spring:message code="manage-AddBorrowBook"></spring:message></div></a>
			</div>
			<div class="search">
					<select name="eid" class="chosen-select" style="width: 300px" >
							<option value="">
										<spring:message code="manage-PleaseSelect1"></spring:message>
								</option>
							<c:forEach items="${el}" var="v">
									<option value="${v.id}" <c:if test="${eid eq v.id}">selected="selected"</c:if>>
										${v.name }
									</option>
							</c:forEach>
						</select>	
					<select name="bid" class="chosen-select" style="width: 300px" >
							<option value="">
										<spring:message code="manage-PleaseSelect2"></spring:message>
								</option>
							<c:forEach items="${bl}" var="v">
									<option value="${v.id}" <c:if test="${bid eq v.id}">selected="selected"</c:if>> 
										${v.title}
									</option>
							</c:forEach>
						</select>
						<input type="image" src="image/sousuo.png" />
				</div>
			<table width="96%" cellpadding="0" cellspacing="0" class="taba" border="0">
				<tr>
					<th bgcolor="#F4F5F9" width="60">
						<spring:message code="manage-Sequence"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="manage-EmpolyeeName"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="manage-BorrowedBookTitle"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="manage-BorrowedTime"></spring:message>
					</th>
					<th bgcolor="#F4F5F9">
						<spring:message code="manage-Status"></spring:message>
					</th>
					<th bgcolor="#F4F5F9" width="100">
						<spring:message code="manage-Operation"></spring:message>
					</th>
				</tr>
				<c:if test="${paging.totalPage==0}">
					<tr>
						<td bgcolor="#F4F5F9" colspan="6" align="center" height="300">
							<center>
								<spring:message code="noresult"></spring:message>
							</center>
						</td>
					</tr>
				</c:if>
				<c:forEach items="${paging.list}" var="v" varStatus="index">
					<tr>
						<td align="center">
							${index.index+1}
						</td>
						<td style="text-align: center">
							${v.eid.name}
						</td>
						<td style="text-align: center">
							${v.bid.title}
						</td>
						<td style="text-align: center">
							<fmt:formatDate value="${v.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td style="text-align: center">
							<c:if test="${v.status eq 0}"><font color=red><spring:message code="manage-NotReturn"></spring:message></font></c:if>
							<c:if test="${v.status eq 1}"><font color=blue><spring:message code="manage-AlreadyReturn"></spring:message></font></c:if>
						</td>
						
											
						<td style="text-align: center">
							<a href="manage/return/${v.id}.html"><img
									src="image/update.png" title="<spring:message code="manage-ReturnBook"></spring:message>" /></a>&nbsp;					

						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="fenleiDiv">
				<p:outpage pageSize="${paging.pageSize}"
					totalPage="${paging.totalPage}" curPage="${paging.curPage}" language="${cookie.myAppLocaleCookie.value}"/>
			</div>
			<br />
		</form>
			<script src="js/chosen.jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function() {
			$('.chosen-select').chosen( {
				search_contains : true,
				no_results_text :  string
			});
		});
	</script>
	</body>
</html>
