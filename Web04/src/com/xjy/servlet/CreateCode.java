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
		// 7.��ֹ������������ͼƬ
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 6.֪ͨ�ͻ�����ͼƬ��ʽ�򿪷��͹�ȥ������
		response.setHeader("Content-Type", "image/jpeg");
		// 1.���ڴ��д���һ��ͼƬ
		BufferedImage image = new BufferedImage(90, 20, BufferedImage.TYPE_INT_RGB);
		// 2.��ͼƬ��д����
		Graphics g = image.getGraphics();
		// �豳��ɫ
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 90, 10);
		// 3.����д�����ݵ���ɫ������
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		// 4.��ͼƬ��д����
		String num = makeNum();
		// ��仰���ǰ�������ɵ���ֵ�����浽session
		request.getSession().setAttribute("checkcode", num); // ͨ��session�Ϳ���ֱ��ȥ���漴���ɵ���֤����
		g.drawString(num, 0, 20);
		// 5.��д�����ݵ�ͼƬ����������
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	// �ú���ʱ�������7λ����
	public String makeNum() {
		Random r = new Random();
		// 9999999 ��������7λ
		String num = r.nextInt(9999999) + "";
		StringBuffer sb = new StringBuffer();
		// �������7λ��ǰ�油��
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
