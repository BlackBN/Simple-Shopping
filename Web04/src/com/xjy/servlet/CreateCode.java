package com.xjy.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCode
 */
@WebServlet("/CreateCode")
public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		// 7.禁止浏览器缓存随机图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 6.通知客户机以图片方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		// 1.在内存中创建一副图片
		BufferedImage image = new BufferedImage(90, 20, BufferedImage.TYPE_INT_RGB);
		// 2.向图片上写数据
		Graphics g = image.getGraphics();
		// 设背景色
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 90, 10);
		// 3.设置写入数据的颜色和字体
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		// 4.向图片上写数据
		String num = makeNum();
		// 这句话就是把随机生成的数值，保存到session
		request.getSession().setAttribute("checkcode", num); // 通过session就可以直接去到随即生成的验证码了
		g.drawString(num, 0, 20);
		// 5.把写好数据的图片输出给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	// 该函数时随机生成7位数字
	public String makeNum() {
		Random r = new Random();
		// 9999999 可以生成7位
		String num = r.nextInt(9999999) + "";
		StringBuffer sb = new StringBuffer();
		// 如果不够7位，前面补零
		for (int i = 0; i < 7 - num.length(); i++) {
			sb.append("0");
		}
		num = sb.toString() + num;
		return num;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
