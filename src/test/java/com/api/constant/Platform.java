package com.api.constant;

public enum Platform {
	FRONT_DESK(2), FST(3);

	int code;

	private Platform(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
