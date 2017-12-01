<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
</head>
<body>
<%-- <%	
	Cookie[] cookies = request.getCookies();
	String name = "";
	if(cookies != null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("username")){
				name = cookie.getValue();
			}
		}
	}
%> --%>

	<c:forEach items="${pageContext.request.cookies }" var="c">
		<c:if test="${fn:contains(c.name,'username') }">
			<c:set var="name" value="${c.value }" scope="page"/>
		</c:if> 
	</c:forEach>

	<div align="center">
		<form action="/Web04/LoginCLServlet" method="post">
			<table>
				<tr>
					<td align="right" width="190px">用户名:</td>
					<td><input type="text" name="username" id="username" value="${name }" style="width: 190px;" /></td>
				</tr>
				<tr>
					<td align="right" width="190px">密　码:</td>
					<td><input type="password" name="password" id="password" value="" style="width: 190px;" /></td>
				</tr>
				<tr>
					<td align="right" width="150px">验证码:</td>
					<td><input type="text" name="check" id="check" style="width: 100px;" /><img src="/Web04/CreateCode"></td>
				</tr>
				<tr>
					<td align="right"><input type="radio" name="remem" value="remem" id="remem"/></td>
					<td>记住账号</td>
				</tr>
				<tr>
					<td align="right"><input type="submit" value="登录"></td>
					<td><input type="reset" value="清空" ></td> 
				</tr>
			</table>
		</form>
	</div>
	<%-- 
		String err = request.getParameter("err");
		if(err != null){
		}
	--%>
		<c:if test="${not empty param.err }">
		<div align="center"><p style="color: red;font-weight: bold;">${param.err }</p></div>
		</c:if>
	
</body>
</html>