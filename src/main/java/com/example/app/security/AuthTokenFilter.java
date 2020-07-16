package com.example.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.app.domain.User;
import com.example.app.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private UserService userService;

	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean valid = jwtService.isValidToken(token);
		if (valid) {
			autenticate(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticate(String token) {
		Long userId = jwtService.getUserId(token);
		User user = userService.findById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
