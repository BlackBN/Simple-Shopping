<%@page import="java.net.URLEncoder"%>
<%@page import="com.xjy.bean.People"%>
<%@page import="com.xjy.bean.Book"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>确认订单</title>
<!-- <script type="text/javascript" language="javascript">
	function goSubmit(){
		window.location.href="/Web04/SubmitCartServlet";
	}

</script> -->
</head>
<body>
<%
	People people = (People)session.getAttribute("people");
	if(people == null){
		response.sendRedirect("/Web04/login.jsp?err="+URLEncoder.encode("不可以直接访问哦,请先登录", "utf-8"));
		return;
	}
	String sum = (String)request.getAttribute("sum");
	LinkedHashMap<Book , Integer> map = (LinkedHashMap<Book , Integer>)request.getAttribute("booklist");
%>
	<a href="/Web04/login.jsp">安全退出</a>|
	<a href="/Web04/hall.jsp">返回购物大厅</a>
	<div align="center">
		
		<%
			if(map.size() == 0){
		%>
			<font size="5" color="red">你的购物车没有商品哦，请选择一些商品在下单吧</font>
		<%
			}else {
		%>
		<h3>我的订单</h3>
		<table width="680px" border="1">
			<tbody>
				<tr>
					<th colspan="2">用户个人信息</th>
				</tr>
				<tr>
					<th width="80px">用户ID</th>
					<th><%=people.getUserid() %></th>
				</tr>
				<tr>
					<th>用户名</th>
					<th><%=people.getUsername() %></th>
				</tr>
				<tr>
					<th>email</th>
					<th><%=people.getEmail() %></th>
				</tr>
				<tr>
					<th>联系方式</th>
					<th><%=people.getPhone() %></th>
				</tr>
				<tr>
					<th>地址</th>
					<th><%=people.getAddress() %></th>
				</tr>
			</tbody>
		</table>
		<br>
		<table width="680px" border="1">
				<tr>
					<th width="80px">BookId</th>
					<th width="200px">书名</th>
					<th width="100px">价格</th>
					<th width="200px">出版社</th>
					<th width="100px">数量</th>
				</tr>
				<%
				if(map != null){
					for(Book book : map.keySet()){
				%>
				<tr>
					<th><%=book.getId()%></th>
					<th><%=book.getBookname()%></th>
					<th><%=book.getPrice()%></th>
					<th><%=book.getPublisher()%></th>
					<th><%=map.get(book)%>本</th>
				</tr>
				<%
					}
				}
				%>
				<tr>
					<th>总价:</th>
					<th><%=sum %></th>
				</tr>
		</table>
		<form action="/Web04/SubmitCartServlet" method="post">
			支付方式:<select name="paymode">
						<option value="zhifubao">支付宝</option>
						<option value="wangyin">网银</option>
						<option value="caifutong">财富通</option>
						<option value="weixin">微信</option>
					</select>
			是否付款: <input type="radio" name="payed" vlaue="yes"/>是 
					 <input type="radio" name="payed" vlaue="no" />否
			<input type="submit" value="确认订单"/>
		</form>
		<%
			}
		%>
		
		
		
	</div>
</body>
</html>