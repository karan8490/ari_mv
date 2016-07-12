package com.kpmg.mw.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.kpmg.mw.config.CommonConfigBean;

/**
 * This controller would contain all the REST APIs for Project resource
 * 
 * @author
 *
 */

@RestController
public class ProjectRestController {

	private Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	/**
	 * Returns details of project with passed identifier
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> projects() {

		final String uriString = commonConfigBean.getCompleteRequestURI("project");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity,
					String.class);
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Returns details of project with passed identifier
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> projectDetails(@PathVariable("id") String id) {

		final String uriString = commonConfigBean.getCompleteRequestURI("project") + "/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity,
					String.class);
			return responseEntity;

		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/topology", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deploymentNames() {
		final String uriString = commonConfigBean.getCompleteRequestURI("topology");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET, entity,
					String.class);

			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/environment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> workloadStatus(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
