package com.myspring.pro28.ex05;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
// 인터셉터
public class LocaleInterceptor extends  HandlerInterceptorAdapter{
	   
	   @Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
	      
		   HttpSession session=request.getSession();
	       
		   String locale=request.getParameter("locale");	// 브라우저에서 전달한 locale 정보를 가져온다
	      
		   if(locale==null)
	         locale="ko";
		   	 session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE",new Locale(locale));
		   	 // LOCALE 속성 값을 세션에 저장해, SessionLocaleResolver가 사용할수 있게 한다
		   	 
		   	 
	      return true;
	   }

	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response,
	                           Object handler, ModelAndView modelAndView) throws Exception {
	   }

	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
	                                    Object handler, Exception ex)    throws  Exception {
	   }
	}
