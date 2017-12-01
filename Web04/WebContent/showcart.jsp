<%@page import="com.xjy.bean.People"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.xjy.bean.Book"%>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.xjy.service.BookService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bookcart.css" />
<script type="text/javascript" language="javascript">
	function goCleat(){
		window.location.href="/Web04/CartCLServlet?type=clear";
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
	LinkedHashMap<Book , Integer> map =(LinkedHashMap<Book , Integer>) request.getAttribute("cartBook");
	double sum = 0.0;
	String pageNow = request.getParameter("page");
	if(pageNow == null)
		pageNow = 1+"";
%>

	<a href="/Web04/login.jsp">安全退出</a>|<a href="/Web04/hall.jsp?pageNow=<%=pageNow %>">返回购物大厅</a>
	<br>
	<br>
	<div align="center">
		<h2>购物车</h2>
		<form action="/Web04/CartCLServlet?type=update" method="post">
			<table border="1">
				<tr>
					<th width="80px">BookId</th>
					<th width="200px">书名</th>
					<th width="100px">价格</th>
					<th width="200px">出版社</th>
					<th width="100px">数量</th>
					<th width="100px">操作</th>
				</tr>
					<%
					if(map != null){
						if(map.size() != 0){
						 	for(Book book : map.keySet()){ 
					%>
							<tr>
								<th><%=book.getId() %><input type="hidden" name="ids" value="<%=book.getId() %>"></th>
								<th><%=book.getBookname() %></th>
								<th><%=book.getPrice() %></th>
								<th><%=book.getPublisher() %></th>
								<th><input type="text" name="num" id="num" value="<%=map.get(book) %>" style="width: 50px;"  >本</th>
								<th><a href="/Web04/CartCLServlet?id=<%=book.getId() %>&type=del">删除</a></th>
								<% sum += map.get(book) * book.getPrice(); %>
							</tr>
				<%
							}
				%>
							<tr>
								<th colspan="1">总价:</th>
								<th colspan="1"><%=sum%></th>
								<th colspan="2"><input type="submit" value="更新" /></th>
								<th colspan="2"><input type="button" value="清空" onclick="goCleat()"/></th>
							</tr>
				<%
						}else{
				%>
					<tr>
						<th colspan="6"><font color="green">回购物大厅选一些书籍吧</font></th>
					</tr>
				<%
						}
					}
				%>				
			</table>
		</form>
		<br>
		<a href="/Web04/MyCartServlet?sum=<%=sum %>">提交订单</a>
	</div>
</body>
</html>