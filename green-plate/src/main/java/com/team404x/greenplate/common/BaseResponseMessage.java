package com.team404x.greenplate.common;

public enum BaseResponseMessage {

	COMPANY_SIGNUP_SUCCESS(true, 1000, "정상적으로 회원가입되었습니다."),
	COMPANY_LOGIN_SUCCESS(true, 1001, "정상적으로 로그인되었습니다."),
	USER_SIGNUP_SUCCESS(true, 1010, "정상적으로 회원가입 되었습니다."),
	USER_SIGNUP_FAIL_PASSWORD(false, 1200, "올바르게 비밀번호를 입력하세요."),
	USER_SIGNUP_FAIL_EMPTY_NICKNAME(false, 1201, "닉네임을 입력해주세요."),
	USER_SIGNUP_FAIL_EMPTY_PASSWORD(false, 1202, "패스워드를 입력해주세요."),
	USER_SIGNUP_FAIL_EMPTY_KEYWORD(false, 1203,"키워드를 입력해주세요."),
	USER_SIGNUP_FAIL_EXISTING_EMAIL(false, 1204, "이미 존재하는 이메일 주소입니다."),
	USER_SIGNUP_FAIL_NOT_CONFIRMED_CODE(false, 1205, "인증번호를 확인해주세요."),
	USER_SIGNUP_FAIL_EXPIRED_CODE(false, 1206, "만료된 인증번호입니다."),
	USER_SIGNUP_FAIL_PAYLOAD_INVALID(false, 1207,"입력값을 확인해주세요."),
	USER_LOGIN_SUCCESS(true, 1070, "정상적으로 로그인되었습니다."),
	USER_LOGIN_FAIL_EMAIL(false, 1800, "이메일을 정확히 입력하세요."),
//	5000 orders
	ORDERS_CREATED_SUCCESS(true, 5100, "주문이 완료되었습니다."),
	ORDERS_CANCEL_SUCCESS(true, 5110, "주문이 취소되었습니다."),
	ORDERS_USER_FAIL_LIST(false, 5001, "주문 내역 조회에 실패하였습니다."),
	ORDERS_SEARCH_FAIL_USER(false, 5052, "유저가 없습니다."),
	ORDERS_UPDATE_SUCCESS_CHANGE(false, 5070, "주문 상태가 변경되었습니다."),
	ORDERS_UPDATE_FAIL_CHANGE(false, 5071, "주문 상태 변경에 실패하였습니다."),
	ORDERS_UPDATE_SUCCESS_INVOICE(false, 5080, "송장 입력이 완료되었습니다."),
	ORDERS_UPDATE_FAIL_INVOICE(false, 5081, "송장 입력에 실패하였습니다."),
	ORDERS_CREATED_FAIL(false, 5401, "주문 실패하였습니다."),
	ORDERS_CREATED_FAIL_STOCK(false, 5401, "상품재고가 부족합니다."),
	ORDERS_SEARCH_FAIL_ORDERED(false, 5111, "해당주문을 찾을 수 없습니다."),

	USER_CREATE_SUCCESS(true, 3200, "상품 등록이 완료되었습니다"),
	USER_CREATE_FAIL(false,3201,"상품을 등록하는데 실패했습니다"),
	USER_CREATE_FAIL_ISEMPTY(false,3202,"비워져있는 값이 있습니다"),
	USER_CREATE_FAIL_PRICE(false,3203,"할인 금액이 정가보다 큽니다"),
	USER_CREATE_FAIL_QUANTITYANDPRICE(false, 3204,"수량이나 금액이 0이하입니다"),

	USER_UPDATE_SUCCESS(true,3220,"상품 수정이 완료되었습니다"),
	USER_UPDATE_FAIL(false,3221,"상품을 등록하는데 실패했습니다"),
	USER_UPDATE_FAIL_ISEMPTY(false,3223,"비워져 있는 값이 있습니다"),
	USER_UPDATE_FAIL_PRICE(false,3224,"할인금액이 정가보다 큽니다"),
	USER_UPDATE_FAIL_QUANTITYANDPRICE(false,3225,"해당 판매자의 상품이 아닙니다"),
	USER_UPDATE_FAIL_NULL(false,3226,"존재하지 않는 상품입니다"),

	USER_STATE_UPDATE_SUCCESS(true, 3240,"상품 상태 수정이 완료되었습니다"),
	USER_STATE_UPDATE_FAIL(false,3241,"상품의 상태를 수정하는데 실패하였습니다"),
	USER_STATE_UPDATE_FAIL_ISEMPITY(false,3241,"상품의 상태를 수정하는데 실패하였습니다"),
	USER_STATE_UPDATE_FAIL_QUANTITY(false,3242,"수량이 0인 제품은 판매를 할 수 없습니다"),
	USER_STATE_UPDATE_FAIL_NOTSELLER(false,3243,"해당 판매자의 상품이 아닙니다"),
	USER_STATE_UPDATE_FAIL_NULL(false,3244,"상품이 존재하지 않습니다"),

	USER_DELETE_SUCCESS(true,3260,"상품 삭제에 성공하였습니다"),
	USER_DELETE_FAIL(false,3261,"상품을 삭제하는데 실패했습니다"),
	USER_DELETE_FAIL_NOTSELLER(false,3262,"해당 판매자의 상품이 아닙니다"),
	USER_DELETE_FAIL_NULL(false,3263,"상품이 존재하지 않습니다"),

	USER_READ_LIST_SUCCESS(true,3280,"상품 검색에 성공했습니다"),
	USER_READ_LIST_FAIL(false,3281,"상품을 불러오는데 실패하였습니다"),
	USER_READ_LIST_FAIL_NULL(false,3283,"해당 판매자의 상품이 없습니다"),

	USER_READ_DETAIL_SUCCESS(true,3300,"상품 상세 정보를 성공적으로 불러왔습니다"),
	USER_READ_DETAIL_FAIL(false,3301,"상품 상세 정보를 불러오는데 실패하였습니다"),
	USER_READ_DETAIL_FAIL_NOTSELLER(false,3303,"해당 판매자의 상품이 아닙니다"),
	USER_READ_DETAIL_FAIL_NULL(false,3304,"해당 상품이 존재하지 않습니다"),

	USER_SEARCH_SUCCESS(true,3320,"상품 검색에 성공했습니다"),
	USER_SEARCH_FAIL(false,3321,"상품을 불러오는데 실패하였습니다"),
	USER_SEARCH_FAIL_ATLEAST(false,3322,"검색어를 2글자 이상 입력해주세요"),
	USER_SEARCH_FAIL_NOTFOUND(false,3323,"검색어에 맞는 상품이 없습니다");

















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
