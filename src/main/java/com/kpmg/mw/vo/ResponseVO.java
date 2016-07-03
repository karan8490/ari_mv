package com.kpmg.mw.vo;

public class ResponseVO {

	protected Boolean success;
	protected String message;
	

	public ResponseVO() {

	}

	public ResponseVO(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
