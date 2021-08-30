package com.myspring.pro28.ex03;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@Controller*/
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
        //                        받는이            제목                내용
        mailService.sendMail("smathj@naver.com","테스트 메일","안녕하세요, 테스트 메일입니다.");
        
        // 정기적으로 보낼경우 xml에 미리 설정한 내용에
        // 내용 부분만 채우는 방식
        // 보내는이, 받는이, 제목 은 미리 설정
        mailService.sendPreConfiguredMail("테스트 메일입니다.");	// 메세지 내용
        
        out.print("메일을 보냈습니다!!");
    }
}


