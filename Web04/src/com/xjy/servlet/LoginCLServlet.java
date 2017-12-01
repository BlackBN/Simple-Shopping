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
			request.getRequestDispatcher("/login.jsp?err=��֤�����").forward(request, response);
			return;
		}
		/**
		 * ��ȡ��¼���͵�¼����
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
			 * ��ȡ���ԵĹ��ﳵ����
			 */
			MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
			/**
			 * ��ȡ�û�
			 */
			People p = (People) request.getSession().getAttribute("people");
			
			if(cartService == null){
				/**
				 * ���Ϊ�գ��򴴽�һ�����ﳵ����
				 */
				cartService = new MyCartService();
			}
			
			if(p != null){
				//��������һ���˺���������Ҫ����һ���µĹ��ﳵ����
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
			request.getRequestDispatcher("/login.jsp?err=������˺Ż��������").forward(request, response);
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
