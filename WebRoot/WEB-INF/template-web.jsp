<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>Group3 Book Manage Sysytem - <sitemesh:write property='title' />
		</title>
		<link rel="stylesheet" href="css/agentStyle.css" type="text/css"></link>
		<link rel="stylesheet" href="css/style.css" type="text/css"></link>
		<script src="js/jquery-1.8.3.min.js"></script>
		<sitemesh:write property='head' />
		<!--[if IE]>
		<script>
		(function(){if(!/*@cc_on!@*/0)return;var e = "abbr,article,aside,audio,bb,canvas,datagrid,datalist,details,dialog,eventsource,figure,footer,header,hgroup,mark,menu,meter,nav,output,progress,section,time,video".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
		</script>
		<![endif]-->

		<script language="javascript">
			function tuichu() {
				return confirm("确定退出系统吗？");
			}
		</script>
	</head>
	<body>
		<div class="ymst"></div>
		<div class="htlogo">
			<table width="1140" cellpadding="0" cellspacing="0" align="center"
				border="0">
				<tr>
					<td class="htlogo_m">
						<img src="image/logo.jpg"></img>
						书籍管理系统
						<span class="beat">beta</span>
					</td>
					<td>
						&nbsp;
					</td>
					<td width="200">
						<table width="190" cellpadding="0" cellspacing="0" align="right"
							border="0">
							<tr>
								<td width="65" valign="top">
									<img src="image/admin.jpg" style="width: 52px" />
								</td>
								<td style="line-height: 30px">
									<p>
										<span class="zgly">图书管理员</span>
									</p>									
								</td>
							</tr>
						</table>
					</td>
					<td class="touxiang" width="260" align="right">
					<a href="welcome.html">主页</a> &nbsp;
						
						<!-- 
						<font color="#E7E7EB">|</font>&nbsp;
						<a href="login/passWord.html">修改密码</a> &nbsp;
						<font color="#E7E7EB">|</font>&nbsp;
						<a href="login/quit.html" onclick="return tuichu()">退出</a> -->
					</td>
				</tr>
			</table>
		</div>
		<br />
		<br />
		<table width="1140" cellpadding="0" cellspacing="0" align="center"
			border="0" bgcolor="#ffffff" style="border: 1px solid #D9DADC;">
			<tr>
				<td width="210" class="menu_td" valign="top">
					<br />
					<p>
						<img src="image/icon_menu_statistics.png" align="absmiddle" />
						&nbsp; 员工管理
					</p>
					<ul class="m_ul">
						<li>
							<a href="employee/list.html" id="dltj">员工列表</a>
						</li>				
					</ul>
					<hr class="m_hr" />
					<p>
						<img src="image/shezhi.png" align="absmiddle" />
						&nbsp; 书籍管理
					</p>
					<ul class="m_ul">
						<li>
							<a href="book/list.html" id="qyhzy">馆藏图书</a>
						</li>		
					</ul>
					<hr class="m_hr" />
					<p>
						<img src="image/shezhi.png" align="absmiddle" />
						&nbsp; 图书操作
					</p>
					<ul class="m_ul">
						<li>
							<a href="manage/list.html" id="scc">借阅列表</a>
						</li>						
					</ul>
				
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<p style="font-size: 12px;">
						反馈电话：025-12345678
					</p>
					<br />
				</td>
				<td valign="top" style="min-height: 600px;">
					<sitemesh:write property='body' />
				</td>
			</tr>
		</table>
		<br />
		<div class="guanyu">
			<div class="guanyu_neirong">
				 Copyright © 2016-2018 Trend School Group 3 .All Rights Reserved.
			</div>
		</div>
	
		<script type="text/javascript">
          /* 当鼠标移到表格上是，当前一行背景变色 */
	      $(document).ready(function(){
	            $(".tab tr td").mouseover(function(){
	                $(this).parent().find("td").css("background-color","#E0EAF6");
	            });
	      })
	      /* 当鼠标在表格上移动时，离开的那一行背景恢复 */
	      $(document).ready(function(){ 
	            $(".tab tr td").mouseout(function(){
	                var bgc = $(this).parent().attr("bg");
	                $(this).parent().find("td").css("background-color",bgc);
	            });
	      })
	      
	      $(document).ready(function(){ 
	            var color="#e6fae7"
	            $(".tab tr:even td").css("background-color",color);  //改变偶数行背景色
	            /* 把背景色保存到属性中 */
	            $(".tab tr:even").attr("bg",color);
	            $(".tab tr:odd").attr("bg","#ffffff");
	      })
      </script>
	</body>
</html>
