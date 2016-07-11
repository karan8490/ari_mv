package com.kpmg.mw.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kpmg.mw.config.CommonConfigBean;

@RestController
public class SolutionRestController {

	private Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/solution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deploymentNames() {
		final String uriString = commonConfigBean.getCompleteRequestURI("solution");
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
	 * Get all solution deployment 
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String solutionDeployment() {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment");
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
	 * Get solution details by identifier
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String solutionDeploymentDetails(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id;
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
	 * Deploy a solution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}/deploy", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deploySolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/deploy";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.PUT, entity,
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
	 * Starts a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/start", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String startSolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/start";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.PUT, entity,
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
	 * Stops a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/stop", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String stopSolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/stop";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.PUT, entity,
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
	 * Releases a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/release", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String releaseSolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/release";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.PUT, entity,
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
	 * Approves a soution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}/approval", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String approveSolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/approval";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.POST, entity,
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
	 * Promotes a solution to other location
	 * @param id
	 * @param locationId
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/promote/{locationId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String promoteSolution(@PathVariable("id") String id, @PathVariable("locationId") String locationId) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id + "/promote/"
				+ locationId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.PUT, entity,
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
	 * Removes a solution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String removeSolution(@PathVariable("id") String id) {
		final String uriString = commonConfigBean.getCompleteRequestURI("solutiondeployment") + "/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.DELETE, entity,
					String.class);
			String response = responseEntity.getBody();
			logger.debug("Received response as " + response);
			return response;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
