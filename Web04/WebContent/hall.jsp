<%@page import="com.xjy.bean.People"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.xjy.service.BookService"%>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xjy.bean.Book"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物大厅</title>
<script type="text/javascript" language="javascript">
	function getPageNow(){
		var pageNows = document.getElementById("pageNow");
		window.location = "http://localhost:8080/Web04/hall.jsp?pageNow="+pageNows.value;
	}
</script>
</head>
<body>
	<% 
		People people = (People)session.getAttribute("people");
		if(people == null){
			response.sendRedirect("/Web04/login.jsp?err="+URLEncoder.encode("不可以直接访问哦,请先登录", "utf-8"));
			return;
		}
	
		/* <c:if test="${empty people }">
			<c:redirect url=""></c:redirect>
		</c:if> */
	
		int k = 4;
		int pageSize = 4;
		int pageNow;
		long pageCount;
		String getPageNow = request.getParameter("pageNow");
		if(getPageNow == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(getPageNow);
		}
		
		BookService bookService = new BookService();
		pageCount = bookService.getPageCount(pageSize);
		if(pageNow < 1 || pageNow > pageCount){
	%>
			<jsp:forward page="/login.jsp"></jsp:forward>
	<% 
		}
		ArrayList<Book> bookList = bookService.getBooks(pageNow, pageSize);
		
	%>
	<div align="center">
		<h1>欢迎光临购物大厅</h1>
		<table border="1">
			<tr>
				<th width="80px">序列号</th>
				<th width="200px">书名</th>
				<th width="100px">价格</th>
				<th width="200px">出版社</th>
				<th width="100px">操作</th>
			</tr>
			<%
				for(int i = 0 ; bookList != null && i < bookList.size() ; i++){
					Book book = bookList.get(i);
			%>
			
			<tr>
				<th><%=book.getId() %></th>
				<th><%=book.getBookname() %></th>
				<th><%=book.getPrice() %>元</th>
				<th><%=book.getPublisher() %></th>
				<th><a href="/Web04/CartCLServlet?id=<%=book.getId() %>&pageNow=<%=pageNow %>&type=add">购买</a></th>
			</tr>
			<%
				}
			%>
		</table>
		<a href="/Web04/hall.jsp?pageNow=1">首页</a>
		<% 
			if(pageNow > 1){	
		%>
				<a href="/Web04/hall.jsp?pageNow=<%=pageNow - 1 %>">上一页</a>
		<%
			}
			int start;
			if(pageNow > pageCount - k)
				start = (int)pageCount - k + 1;
			else 
				start = pageNow;
			for(int i = start ; i < start + k ; i++)
			{				
		%>
		<a href="/Web04/hall.jsp?pageNow=<%=i %>"><%=i %></a>
		<%
				}
			%>
		<% 
				if(pageNow < pageCount){
			%>
		<a href="/Web04/hall.jsp?pageNow=<%=pageNow + 1 %>">下一页</a>
		<%
				}
			%>
		<a href="/Web04/hall.jsp?pageNow=<%=pageCount%>">尾页</a> 跳转到：第<select
			name='pageNow' id='pageNow'>
			<%
				for(int i = 1 ; i <= pageCount ; i++){
			%>
			<option value="<%=i %>"><%=i %>页
			</option>
			<%				
				}
			%>
		</select> <input type="submit" value="跳转" onClick="getPageNow();" />
		<p>
			当前页 第<%=pageNow %>页/总页数 <%=pageCount %>页
		</p>
		<br> 
		<a href="/Web04/CartCLServlet?pageNow=<%=pageNow %>&type=check"   style="color: red; text-decoration: none; font-weight: bold;" >查看购物车</a>|
		<a href="/Web04/login.jsp" style="color: red; text-decoration: none; font-weight: bold;">返回重新登录</a>
	</div>
</body>
</html>