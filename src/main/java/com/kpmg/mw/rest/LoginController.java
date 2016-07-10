package com.kpmg.mw.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

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

@RestController
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String authenticate(HttpServletRequest request, HttpServletResponse response) {

		final String uriString = commonConfigBean.getCompleteRequestURI("user?self");
		HttpHeaders headers = new HttpHeaders();
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> responseEntity = null;
		try {

			URI uri = new URIBuilder(uriString).addParameter("user", user).addParameter("password", password).build();
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

			String JID = null;
			Optional<String> jsessionID = responseEntity.getHeaders().get("Set-Cookie").stream()
					.filter(s -> s.startsWith("JSESSIONID")).findFirst();
			if (jsessionID.isPresent()) {
				JID = jsessionID.get().substring(jsessionID.get().indexOf("="));
			}

			response.addCookie(new Cookie("JSESSIONID", JID));
			return responseEntity.getBody();

		} catch (RestClientException e) {
			logger.error("error occurred", e);
			if (responseEntity != null)
				response.setStatus(responseEntity.getStatusCodeValue());
			return null;
		} catch (URISyntaxException e) {
			logger.error("URI syntax error");
			return null;
		}
	}
}
