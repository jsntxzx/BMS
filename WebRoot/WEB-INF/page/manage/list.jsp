<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>会员</title>
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
	</head>
	<body>
		<p class="dqgongneng"> 
			书籍借出记录
		</p>
		<br />

		<form action="manage/list.html" id="myform" method="post">
			<div class="anniuweizhi">			
		 		<a href="manage/add.html"><div>+ 借阅书籍</div></a>
			</div>
			<div class="search">
					<select name="eid" class="chosen-select" style="width: 300px" >
							<option value="">
										请选择
								</option>
							<c:forEach items="${el}" var="v">
									<option value="${v.id}" <c:if test="${eid eq v.id}">selected="selected"</c:if>>
										${v.name }
									</option>
							</c:forEach>
						</select>	
					<select name="bid" class="chosen-select" style="width: 300px" >
							<option value="">
										请选择
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
						序号
					</th>
					<th bgcolor="#F4F5F9">
						借阅人
					</th>
					<th bgcolor="#F4F5F9">
						借阅书籍
					</th>
					<th bgcolor="#F4F5F9">
						借阅时间
					</th>
					<th bgcolor="#F4F5F9">
						状态
					</th>
					<th bgcolor="#F4F5F9" width="100">
						操作
					</th>
				</tr>
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
							<c:if test="${v.status eq 0}"><font color=red>未还</font></c:if>
							<c:if test="${v.status eq 1}"><font color=blue>已还</font></c:if>
						</td>
						
											
						<td style="text-align: center">
							<a href="manage/return/${v.id}.html"><img
									src="image/update.png" title="还书" /></a>&nbsp;					

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
