package com.myspring.pro28.ex04;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAsync
public class MailController {
    @Autowired
    private MailService mailService;
 
    @RequestMapping(value = "/sendMail.do", method = RequestMethod.GET)
    public void sendSimpleMail(HttpServletRequest request, HttpServletResponse response) 
                                                          throws Exception{
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
        
    	PrintWriter out = response.getWriter();
        
        StringBuffer sb = new StringBuffer();
 	    
        sb.append("<html><body>");
 		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
 		sb.append("<h1>"+"멍멍이 소개"+"<h1><br>");
 		sb.append("새로운 개새끼를 소개합니다.<br><br>");
// 		sb.append("<a href='http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788956746425&orderClick=LAG&Kc=#N'>");
// 		sb.append("<img  src='http://image.kyobobook.co.kr/images/book/xlarge/425/x9788956746425.jpg' /> </a><br>");
 		sb.append("<img  src='https://mblogthumb-phinf.pstatic.net/20160520_138/rlaantjd8204_1463747610911xH1HI_JPEG/%B9%E9%B1%B82.jpg?type=w2' /> </a><br>");
// 		sb.append("</a>");
// 		sb.append("<a href='http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788956746425&orderClick=LAG&Kc=#N'>��ǰ����</a>");
 		sb.append("</body></html>");
 		
 		String str=sb.toString();
 		
 		mailService.sendMail("jin03192@naver.com","새로운 강아지를 소개합니다.",str);
      
        out.print("메일을 보냈습니다!!");
    }
}


