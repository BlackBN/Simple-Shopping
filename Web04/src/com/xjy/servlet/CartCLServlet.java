package com.xjy.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjy.bean.Book;
import com.xjy.service.BookService;
import com.xjy.service.MyCartService;

/**
 * Servlet implementation class CartCLServlet
 */
@WebServlet("/CartCLServlet")
public class CartCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCLServlet() {
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
		String type = request.getParameter("type");
		
		if("add".equals(type)){
			addBook(request, response);
		}else if("del".equals(type)){
			delBook(request, response);
		}else if("update".equals(type)){
			updateCart(request, response);
		}else if("check".equals(type)){
			checkCart(request, response);
		}else if("clear".equals(type)){
			String getPageNow = request.getParameter("pageNow");
			int pageNow;
			if(getPageNow == null){
				pageNow = 1;
			}else{
				pageNow = Integer.parseInt(getPageNow);
			}
			MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
			cartService.clearCart();
			request.setAttribute("cartBook", cartService.getMap());	
			//该参数可以防盗链  System.out.println(request.getHeader("Referer"));
			if("http://localhost:8080/Web04/SubmitCartServlet".equals(request.getHeader("Referer"))){
				response.sendRedirect("/Web04/hall.jsp");
			}else{
				request.getRequestDispatcher("/showcart.jsp?page="+pageNow).forward(request, response);
			}
		}
	}

	public void checkCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getPageNow = request.getParameter("pageNow");
		int pageNow;
		if(getPageNow == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(getPageNow);
		}
		MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
		request.setAttribute("cartBook", cartService.getMap());	
		request.getRequestDispatcher("/showcart.jsp?page="+pageNow).forward(request, response);
	}
	/**
	 * 更新购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getPageNow = request.getParameter("pageNow");
		int pageNow;
		if(getPageNow == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(getPageNow);
		}
		String[] ids = request.getParameterValues("ids");
		String[] nums = request.getParameterValues("num");
		if(ids != null && nums != null){
			MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
			for(int i = 0 ; i < ids.length ; i++){
				int id = Integer.parseInt(ids[i]);
				int num = Integer.parseInt(nums[i]);
				cartService.updateBook(id , num);
			}
			request.setAttribute("cartBook", cartService.getMap());	
			request.getRequestDispatcher("/showcart.jsp?page="+pageNow).forward(request, response);
		}
	}
	/**
	 * 购物车删除已选中的书籍
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String getPageNow = request.getParameter("pageNow");
		int pageNow;
		if(getPageNow == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(getPageNow);
		}
		String getId = request.getParameter("id");
		int id = 0;
		if(getId != null){
			id = Integer.parseInt(getId);
		}
		MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
		if(cartService != null){
			cartService.delBook(id);
			request.setAttribute("cartBook", cartService.getMap());	
		}
		request.getRequestDispatcher("/showcart.jsp?page="+pageNow).forward(request, response);
	}
	/**
	 * 购物车增加书籍
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UnsupportedEncodingException {
		String pageNow = request.getParameter("pageNow");
		String getId = request.getParameter("id");
		int id = 0;
		if(getId != null){
			id = Integer.parseInt(getId);
		}
		MyCartService cartService = (MyCartService) request.getSession().getAttribute("myCart");
		
		if(cartService != null){
			boolean bools = false;
			LinkedHashMap<Integer, Book> hashMap = cartService.getHashMap();
			for(int ids : hashMap.keySet()){
				if(ids == id){
					bools = true;
				}
			}
			if(bools){
				cartService.addBook(hashMap.get(id));
			}else{
				cartService.addBook(id , new BookService().getBook(id) );
			}
			request.setAttribute("cartBook", cartService.getMap());
			request.getRequestDispatcher("/showcart.jsp?page="+pageNow).forward(request, response);
		}else{
			response.sendRedirect("/Web04/login.jsp?err="+URLEncoder.encode("请先登录", "utf-8"));
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
