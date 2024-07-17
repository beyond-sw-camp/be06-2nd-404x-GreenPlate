package com.team404x.greenplate.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.team404x.greenplate.common.BaseResponse;
import com.team404x.greenplate.common.BaseResponseMessage;
import com.team404x.greenplate.user.dto.request.UserSignupReq;
import com.team404x.greenplate.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	/* TODO
	예외 로직: ControllerAdvice를 이용
	*   */
	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public BaseResponse signup(@RequestBody UserSignupReq userSignupReq) {
		try {
			userService.signup(userSignupReq);
			return new BaseResponse<>(BaseResponseMessage.USER_SIGNUP_SUCCESS);
		} catch (Exception e) {
			return new BaseResponse(null);
		}
	}

}
