package com.xjy.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjy.bean.Order;
import com.xjy.bean.People;
import com.xjy.service.MyCartService;
import com.xjy.service.OrderService;

/**
 * Servlet implementation class SubmitCartServlet
 */
@WebServlet("/SubmitCartServlet")
public class SubmitCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		People people = (People)request.getSession().getAttribute("people");
		if(people == null){
			response.sendRedirect("/Web04/login.jsp?err="+URLEncoder.encode("不可以直接访问哦,请先登录", "utf-8"));
			return;
		}

		MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
		if(cartService != null){
			Order order = new Order();
			order.setUserid(people.getUserid());
			order.setOrderDate(new Date());
			order.setPayMode(request.getParameter("paymode"));
			order.setPay(Boolean.parseBoolean(request.getParameter("payed")));
			order.setTotalPrice(cartService.getTotalPrice());
			
			OrderService orderService = new OrderService();
			try{
				orderService.submitOrder(people, cartService, order);
				request.getRequestDispatcher("/orderSuccess.jsp").forward(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				request.getRequestDispatcher("/MyCartServlet?sum="+cartService.getTotalPrice()).forward(request, response);
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
