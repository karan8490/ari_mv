package com.kpmg.mw.rest;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.kpmg.mw.config.CommonConfigBean;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password, HttpServletResponse response) {

		if (username == null && password == null) {
			return new ResponseEntity<String>("{success:false, message:'Username/password missing'}",
					HttpStatus.BAD_REQUEST);
		}

		final String uri = commonConfigBean.getBaseURL() + "/j_spring_security_check";
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("j_username", username);
		body.add("j_password", password);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

			/**
			 * Reaching here means there is no unauthorized exception (meaning
			 * authentication succeeded. Try to extract JSESSION ID from
			 * response.
			 */

			String JID = null;
			Optional<String> jsessionID = responseEntity.getHeaders().get("Set-Cookie").stream()
					.filter(s -> s.startsWith("JSESSIONID")).findFirst();
			if (jsessionID.isPresent()) {
				JID = jsessionID.get().substring(jsessionID.get().indexOf("="));
			}

			/**
			 * Call user?ref API to get logged in user information by passing
			 * username/password using http basic authentication.
			 */

			HttpHeaders userRequestHeaders = new HttpHeaders();
			String plainCredentials = username + ":" + password;
			byte[] plainCredsBytes = plainCredentials.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			userRequestHeaders.add("Authorization", "Basic " + base64Creds);
			userRequestHeaders.add("Cookie", "JSESSIONID=" + JID);

			HttpEntity<Object> userRequestEntity = new HttpEntity<Object>(null, userRequestHeaders);
			String userRequestURI = commonConfigBean.getCompleteRequestURI("user?self");

			HttpHeaders finalHeaders = new HttpHeaders();
			finalHeaders.add("Set-Cookie", "JSESSIONID=" + JID);
			/*finalHeaders.add("Access-Control-Allow-Origin", "*");
			finalHeaders.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			finalHeaders.add("Access-Control-Allow-Headers", "*" );
			*/			

			/**
			 * Set response of user?ref and JID received from
			 * j_spring_security_check call to construct a response
			 */

			ResponseEntity<String> userResponseEntity = restTemplate.exchange(userRequestURI, HttpMethod.GET,
					userRequestEntity, String.class);

			ResponseEntity<String> finalResponseEntity = new ResponseEntity<String>(userResponseEntity.getBody(),
					finalHeaders, HttpStatus.OK);

			return finalResponseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			HttpHeaders finalHeaders = new HttpHeaders();
			/**
			 * Exception occurred, clear cookie and send an unauthorized status code
			 */
			finalHeaders.add("Set-Cookie", "JSESSIONID=;max-age=0");
			
			ResponseEntity<String> res = new ResponseEntity<String>("login failed", finalHeaders,
					HttpStatus.UNAUTHORIZED);
			return res;

		}

	}

	/*
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> user(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password, HttpServletResponse response) {

		logger.debug("received user name as " + username);
		logger.debug("received password as " + password);

		if (username == null && password == null) {
			return new ResponseEntity<>("{success:false, message:'Username/password missing'}", HttpStatus.BAD_REQUEST);
		} else {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String plainCredentials = username + ":" + password;
			byte[] plainCredsBytes = plainCredentials.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			headers.add("Authorization", "Basic " + base64Creds);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> responseEntity = null;
			try {
				final String uriString = commonConfigBean.getCompleteRequestURI("user?self");
				URI uri = new URIBuilder(uriString).build();
				responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

				logger.debug("Received body [" + responseEntity.getBody() + "]");

				return responseEntity;

			} catch (RestClientException e) {
				logger.error("error occurred", e);
				if (responseEntity != null)
					return responseEntity;
				else
					return new ResponseEntity<>("Internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (URISyntaxException e) {
				logger.error("URI syntax error");
				return new ResponseEntity<>("Internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}
	*/
}
