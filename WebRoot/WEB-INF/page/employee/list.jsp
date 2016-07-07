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
			员工管理
		</p>
		<br />


		<form action="employee/list.html" id="myform" method="post">
			<div class="anniuweizhi">
				<select class="select_obj" name="key">
					<option value="">
						--请选择--
					</option>
					<option value="name%"
						<c:if test="${key eq 'name%'}">selected="selected"</c:if>>
						姓名
					</option>
					<option value="enumber%"
						<c:if test="${key eq 'enumber%'}">selected="selected"</c:if>>
						工号
					</option>					
				</select>
				<input type="text" class="sousuo" name="value" value="${value}">
				<input type="image" src="image/sousuo.png" />
				<a href="employee/add.html"><div>
						+ 添加新员工
					</div>
				</a>
			</div>
			<table width="96%" cellpadding="0" cellspacing="0" class="taba"
				border="0">
				<tr>
					<th bgcolor="#F4F5F9" width="60">
						序号
					</th>
					<th bgcolor="#F4F5F9">
						姓名
					</th>
					<th bgcolor="#F4F5F9">
						工号
					</th>
					<th bgcolor="#F4F5F9" width="140">
						操作
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
									src="image/update.png" title="修改" />
							</a>&nbsp;							
							<a href="employee/delete/${v.id}.html" onclick="return confirm('删除以后数据将不可恢复\n确定删除吗？')"> <img src="image/del.png"
									title="删除员工" />
							</a>&nbsp;
							<a href="employee/listborrow/${v.id}.html"> 
								<img src="image/zhishi.png" title="查看借书" />
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
