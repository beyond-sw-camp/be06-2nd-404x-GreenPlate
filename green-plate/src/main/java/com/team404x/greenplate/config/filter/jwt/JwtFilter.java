package com.team404x.greenplate.config.filter.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.team404x.greenplate.common.GlobalMessage;
import com.team404x.greenplate.company.model.entity.Company;
import com.team404x.greenplate.config.filter.login.CustomUserDetails;
import com.team404x.greenplate.user.model.entity.User;
import com.team404x.greenplate.utils.jwt.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
TODO
JWT token에 어떤 정보를 담을 지 정하기
* */
public class JwtFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;

	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
		ServletException,
		IOException {
		String authorization = request.getHeader(GlobalMessage.AUTHORIZATION_HEADER.getMessage());

		if (authorization == null || !authorization.startsWith(GlobalMessage.AUTHORIZATION_VALUE.getMessage())) {
			filterChain.doFilter(request, response);

			return;
		}

		String token = authorization.split(" ")[1];

		if (jwtUtil.isExpired(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		Long id = jwtUtil.getId(token);
		String email = jwtUtil.getEmail(token);
		String role = jwtUtil.getRole(token);

		CustomUserDetails customUserDetails = null;

		if (role.equals(GlobalMessage.ROLE_USER.getMessage())) {
			User user = User.builder()
				.id(id)
				.email(email)
				.role(role)
				.build();
			customUserDetails = new CustomUserDetails(user);
		} else if (role.equals(GlobalMessage.ROLE_COMPANY.getMessage())) {
			Company company = Company.builder()
				.id(id)
				.email(email)
				.role(role)
				.build();
			customUserDetails = new CustomUserDetails(company);
		}


		Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
			customUserDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}
}

