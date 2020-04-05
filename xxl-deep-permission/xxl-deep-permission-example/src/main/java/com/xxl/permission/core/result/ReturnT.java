package com.xxl.permission.core.result;

import java.io.Serializable;

/**
 * @author xuxueli 2015-3-29 18:27:32
 * @param <T>
 */
public class ReturnT<T> implements Serializable {
	public static final long serialVersionUID = 42L;

	public static final int SUCCESS_CODE = 200;
	public static final int FAIL_CODE = 500;

	public static final ReturnT<String> SUCCESS = new ReturnT<String>(SUCCESS_CODE, null);
	public static final ReturnT<String> FAIL = new ReturnT<String>(FAIL_CODE, null);

	public int code;
	public String msg;
	private T content;

	public ReturnT(T content) {
		this.code = SUCCESS_CODE;
		this.content = content;
	}
	public ReturnT(int code, String msg) {
		this.code = code;
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

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
