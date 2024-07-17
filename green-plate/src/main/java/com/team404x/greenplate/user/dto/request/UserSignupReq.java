package com.team404x.greenplate.user.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSignupReq {
	String email;
	String password;
	String name;
	String nickname;
	LocalDate birthday;
}
