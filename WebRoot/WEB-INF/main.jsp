<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="welcome-title"></spring:message></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  	<style>
  		.banner {
  			width : 96% ;
  			margin-left:20px;
  			margin-top:10px;
  			max-height:600px;
  			margin-bottom:10px;
  		}
  	</style>
  </head>
  <body>
  	<div class="waikuang_div">
			<div class="title_bg_div">
				<div class="title_wz_div">
					<spring:message code="welcome-head"></spring:message> 
				</div>
			</div>
			
			<table width="100%" cellspacing="0" border="0">
				<tbody><tr>
					<td width="300" height="40">
						&nbsp;&nbsp;&nbsp;<spring:message code="welcome-num1"></spring:message>
					</td>
					<td width="300">
						${num1}<spring:message code="welcome-unit1"></spring:message>
					</td>
					<td width="300"> 
						&nbsp;&nbsp;&nbsp;<spring:message code="welcome-num2"></spring:message>
					</td>
					<td>
						${num2}<spring:message code="welcome-unit2"></spring:message>
					</td>
				</tr>
				<tr>
					<td height="40" width="300">
						&nbsp;&nbsp;&nbsp;<spring:message code="welcome-num3"></spring:message>
					</td>
					<td>
						${num3}<spring:message code="welcome-unit3"></spring:message>
					</td>
					<td>
						&nbsp;&nbsp;&nbsp;<spring:message code="welcome-num4"></spring:message>
					</td>
					<td width="300">
						${num4}<spring:message code="welcome-unit4"></spring:message>
					</td>
				</tr>
			</tbody></table>
			
			
		</div>
  </body>
</html>
