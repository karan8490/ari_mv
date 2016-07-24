package com.kpmg.mw.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kpmg.mw.config.CommonConfigBean;

@RestController
public class EnvRestController {

	private Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/environment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAnEnv(@PathVariable("id") String id) {

		logger.debug("Invoked GET API for an environment : " + id);

		final String uriString = commonConfigBean.getCompleteRequestURI("environment") + "/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity,
					String.class);
			String response = responseEntity.getBody();
			logger.debug("Received response as " + response);
			return response;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Get all environments
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/environment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAllEnv() {

		logger.debug("Invoked GET API for all environment : ");

		final String uriString = commonConfigBean.getCompleteRequestURI("environment");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity,
					String.class);
			String response = responseEntity.getBody();
			logger.debug("Received response as " + response);
			return response;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Create environment runtime
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/environment/{id}/runtime", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> createRuntime(@PathVariable("id") String id) {
		logger.debug("Creating environment runtime");
		final String uriString = commonConfigBean.getCompleteRequestURI("environment") + "/" + id + "/runtime";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.POST, entity,
					String.class);
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
