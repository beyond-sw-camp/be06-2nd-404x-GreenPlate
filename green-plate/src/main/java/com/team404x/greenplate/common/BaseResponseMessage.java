package com.team404x.greenplate.common;

public enum BaseResponseMessage {
	USER_SIGNUP_SUCCESS(true, 1010, "정상적으로 회원가입 되었습니다."),
	USER_SIGNUP_FAIL_PASSWORD(false, 1200, "올바르게 비밀번호를 입력하세요"),
	USER_SIGNUP_FAIL_EMPTY_NICKNAME(false, 1201, "닉네임을 입력해주세요"),
	USER_SIGNUP_FAIL_EMPTY_PASSWORD(false, 1202, "패스워드를 입력해주세요"),
	USER_SIGNUP_FAIL_EMPTY_KEYWORD(false, 1203,"키워드를 입력해주세요."),
	USER_SIGNUP_FAIL_EXISTING_EMAIL(false, 1204, "이미 존재하는 이메일 주소입니다"),
	USER_SIGNUP_FAIL_NOT_CONFIRMED_CODE(false, 1205, "인증번호를 확인해주세요"),
	USER_SIGNUP_FAIL_EXPIRED_CODE(false, 1206, "만료된 인증번호입니다"),
	USER_SIGNUP_FAIL_PAYLOAD_INVALID(false, 1207,"입력값을 확인해주세요")
	;

	private Boolean success;
	private Integer code;
	private String message;

	BaseResponseMessage(Boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
