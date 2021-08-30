package com.myspring.pro28.ex03;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*@Service("mailService")*/
public class MailService {
	@Autowired
	 private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    @Async
	public void sendMail(String to, String subject, String body) {
      MimeMessage message = mailSender.createMimeMessage();	// MimeMessage 타입 객체를 생성한다
      try {
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
		messageHelper.setCc("smathj@naver.com");
		messageHelper.setFrom("smathj007@gmail.com", "나태쿤");
		messageHelper.setSubject(subject);
		messageHelper.setTo(to); 
		messageHelper.setText(body);
		
		mailSender.send(message);
		
      }catch(Exception e){
		e.printStackTrace();
	  }
	}
 
	@Async
	public void sendPreConfiguredMail(String message) {
	// mail-context.xml에서 미리 설정한 수신 주소로 메일 내용을 보냅니다  
	  SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	  
	  mailMessage.setText(message);
	  
	  mailSender.send(mailMessage);
	  
	}
	
}

