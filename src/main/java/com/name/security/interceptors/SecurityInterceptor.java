
package com.name.security.interceptors;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.name.security.api.Security;


@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	Security security;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


		String userName = request.getHeader("userName");
		System.out.println(request.getMethod() + ", " + request.getRequestURL() + ", " + request.getRequestURI());
		// by passing security api's
		if(!request.getRequestURI().contains("/api/shop/v1")) {
			return true;
		}
		
		if (true || security.hasAccess(request.getRequestURI(), userName)) {
			return true;
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString() + " Sent to Handler :: Current Time="
				+ System.currentTimeMillis());
		// we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
