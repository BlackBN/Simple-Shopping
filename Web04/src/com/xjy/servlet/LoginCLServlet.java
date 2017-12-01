package com.xjy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xjy.bean.People;
import com.xjy.service.MyCartService;
import com.xjy.service.PeopleService;

/**
 * Servlet implementation class LoginCLServlet
 */
@WebServlet("/LoginCLServlet")
public class LoginCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String checkcode1 = (String) request.getSession().getAttribute("checkcode");
		String checkcode2 = request.getParameter("check");
		System.out.println(checkcode1);
		System.out.println(checkcode2);
		if(!checkcode1.equals(checkcode2)){
			request.getRequestDispatcher("/login.jsp?err=验证码错误").forward(request, response);
			return;
		}
		/**
		 * 获取登录名和登录密码
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String remem = request.getParameter("remem");
		if("remem".equals(remem)){
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
		}
		People people = new People();
		people.setUsername(username);
		people.setPasswd(password);
		PeopleService peopleService = new PeopleService();
		boolean b = peopleService.getPeople(people);
		if(b){
			/**
			 * 获取独自的购物车对象
			 */
			MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
			/**
			 * 获取用户
			 */
			People p = (People) request.getSession().getAttribute("people");
			
			if(cartService == null){
				/**
				 * 如果为空，则创建一个购物车对象
				 */
				cartService = new MyCartService();
			}
			
			if(p != null){
				//输入另外一个账号密码则需要创建一个新的购物车对象
				if(p.getUserid() != people.getUserid()){
					request.getSession().setAttribute("people", people);
					cartService = new MyCartService();
				}
			}else{
				request.getSession().setAttribute("people", people);
			}
			
			request.getSession().setAttribute("myCart", cartService);
			request.getRequestDispatcher("/hall.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/login.jsp?err=输入的账号或密码错误").forward(request, response);
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
