package com.xjy.test;

public class SendToSome {

	    /* 
	    public static void main(String []args){ 
	         
	        SendMailToSomeone sts=new SendMailToSomeone();       
	        sts.send("邮件测试",  
	                "<meta http-equiv=content-Type content=text/html;charset=gb2312><div align=center><h1 style=\"color: red\">这是邮箱测试 （-.-）</h1></div>", 
	                "2352456955@qq.com", 
	                "xail@sohu.com", 
	                "wxh3",  
	                "smtp.sohu.com"); 
	    } 
	    */  
	    /** 
	     * 发送电子邮件到指定的信箱 
	     * @param title 邮件标题  
	     * @param mailbody 邮件内容【一个网页，表格ok】 
	     * @param sendTo 发送给谁 hanshunping@tsinghua.org.cn 
	     * @param from 从哪些发送 admin@sohu.com 
	     * @param passwd 密码 123456 
	     * @param sendStmp 发送邮件的smtp smtp.sohu.com [smtp.163.com] [smtp.sina.com] 
	     */  
	    public void send(String title,String mailbody,String sendTo,String from,String passwd,String sendStmp){  
	          
	        //指明让那个smtp转发(搜狐)   
	        SendEmail sendEmail = new SendEmail(sendStmp); 
	        //校验身份  
	        sendEmail.setNeedAuth(true);  
	          
	        //邮件标题  
	        if(sendEmail.setSubject(title) == false) return;  
	        //将要发送的 内容放入邮件体   
	        if(sendEmail.setBody(mailbody) == false) return;  
	          
	        //发送到哪里  
	        if(sendEmail.setTo(sendTo) == false) return;  
	          
	        //谁发送的  
	        if(sendEmail.setFrom(from) == false) return;  
	      
	      
	        //if(sendEmail.addFileAffix("c:\\1.rar") == false) return;  
	          
	              
	    //  if()  
	        //将在sohu网站上的邮件用户名和 密码 放入邮件体   
	        sendEmail.setNamePass("xhwil@sohu.com","h123");  
	          
	        //发送  
	        if(sendEmail.sendout() == false) return;  
	    }   
}
