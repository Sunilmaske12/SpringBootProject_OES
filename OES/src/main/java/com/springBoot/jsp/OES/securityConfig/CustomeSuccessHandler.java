package com.springBoot.jsp.OES.securityConfig;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class CustomeSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println("==============================");
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/Admin/adminPannel");
		}
		else if(roles.contains("ROLE_USER"))
			response.sendRedirect("/");
	}

}
