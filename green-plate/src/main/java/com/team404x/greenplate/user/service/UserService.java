package com.team404x.greenplate.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.team404x.greenplate.user.dto.request.UserSignupReq;
import com.team404x.greenplate.user.entity.User;
import com.team404x.greenplate.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public void signup(UserSignupReq userSignupReq) throws Exception {
		User user = User.builder()
			.email(userSignupReq.getEmail())
			.password(passwordEncoder.encode(userSignupReq.getPassword()))
			.name(userSignupReq.getName())
			.nickName(userSignupReq.getNickname())
			.birthday(userSignupReq.getBirthday())
			.build();

		userRepository.save(user);
	}
}
