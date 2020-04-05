package com.xxl.permission.core.exception;

import com.xxl.permission.core.result.ReturnT;

import java.io.Serializable;

/**
 * 自定义异常
 * @author xuxueli
 */
public class WebException extends RuntimeException implements Serializable {
	public static final long serialVersionUID = 42L;

	public int code;
	public String msg;

	public WebException() {
	}

	public WebException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public WebException(String msg) {
		this.code = ReturnT.FAIL_CODE;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
