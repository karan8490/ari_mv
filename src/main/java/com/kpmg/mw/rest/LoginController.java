package com.kpmg.mw.rest;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kpmg.mw.config.CommonConfigBean;
import com.kpmg.mw.vo.ResponseVO;

@RestController
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseVO authenticate(HttpServletRequest request, HttpServletResponse response) {

		logger.info("Inside authenticate method");

		final String uri = commonConfigBean.getBaseURL() + "/j_spring_security_check";
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		body.add("j_username", request.getParameter("username"));
		body.add("j_password", request.getParameter("password"));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
			logger.info("headers" + responseEntity.getHeaders());

			String JID = null;
			Optional<String> jsessionID = responseEntity.getHeaders().get("Set-Cookie").stream()
					.filter(s -> s.startsWith("JSESSIONID")).findFirst();
			if (jsessionID.isPresent()) {
				JID = jsessionID.get().substring(jsessionID.get().indexOf("="));
			}

			logger.info("JID value is " + JID);
			response.addCookie(new Cookie("JSESSIONID", JID));
			return new ResponseVO(true, "Login successful");
		} catch (RestClientException e) {
			Cookie cookie = new Cookie("JSESSIONID", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			if (responseEntity != null)
				response.setStatus(responseEntity.getStatusCodeValue());
			return new ResponseVO(false, "Login failed");
		}

	}

}
