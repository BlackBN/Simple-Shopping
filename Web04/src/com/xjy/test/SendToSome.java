package com.xjy.test;

public class SendToSome {

	    /* 
	    public static void main(String []args){ 
	         
	        SendMailToSomeone sts=new SendMailToSomeone();       
	        sts.send("�ʼ�����",  
	                "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">����������� ��-.-��</h1></div>", 
	                "2352456955@qq.com", 
	                "xail@sohu.com", 
	                "wxh3",  
	                "smtp.sohu.com"); 
	    } 
	    */  
	    /** 
	     * ���͵����ʼ���ָ�������� 
	     * @param title �ʼ�����  
	     * @param mailbody �ʼ����ݡ�һ����ҳ�����ok�� 
	     * @param sendTo ���͸�˭ hanshunping@tsinghua.org.cn 
	     * @param from ����Щ���� admin@sohu.com 
	     * @param passwd ���� 123456 
	     * @param sendStmp �����ʼ���smtp smtp.sohu.com [smtp.163.com] [smtp.sina.com] 
	     */  
	    public void send(String title,String mailbody,String sendTo,String from,String passwd,String sendStmp){  
	          
	        //ָ�����Ǹ�smtpת��(�Ѻ�)   
	        SendEmail sendEmail = new SendEmail(sendStmp); 
	        //У�����  
	        sendEmail.setNeedAuth(true);  
	          
	        //�ʼ�����  
	        if(sendEmail.setSubject(title) == false) return;  
	        //��Ҫ���͵� ���ݷ����ʼ���   
	        if(sendEmail.setBody(mailbody) == false) return;  
	          
	        //���͵�����  
	        if(sendEmail.setTo(sendTo) == false) return;  
	          
	        //˭���͵�  
	        if(sendEmail.setFrom(from) == false) return;  
	      
	      
	        //if(sendEmail.addFileAffix("c:\\1.rar") == false) return;  
	          
	              
	    //  if()  
	        //����sohu��վ�ϵ��ʼ��û����� ���� �����ʼ���   
	        sendEmail.setNamePass("xhwil@sohu.com","h123");  
	          
	        //����  
	        if(sendEmail.sendout() == false) return;  
	    }   
}
