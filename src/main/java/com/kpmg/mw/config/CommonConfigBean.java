package com.kpmg.mw.config;

public class CommonConfigBean {

	private String baseURL;
	private String apiVersion;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getCompleteRequestURI(String uri) {
		return baseURL + "/api/" + apiVersion + "/" + uri;
		//return baseURL;
	}

}
