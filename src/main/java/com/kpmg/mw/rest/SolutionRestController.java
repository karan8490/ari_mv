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
	public ResponseEntity<?> deploymentNames() {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Get all solution deployment 
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> solutionDeployment() {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Get solution details by identifier
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> solutionDeploymentDetails(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deploy a solution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}/deploy", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deploySolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Starts a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/start", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> startSolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Stops a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/stop", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> stopSolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Releases a solution
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/release", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> releaseSolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Approves a soution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}/approval", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> approveSolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	/**
	 * Promotes a solution to other location
	 * @param id
	 * @param locationId
	 * @return
	 */

	@RequestMapping(value = "/solutiondeployment/{id}/promote/{locationId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> promoteSolution(@PathVariable("id") String id, @PathVariable("locationId") String locationId) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Removes a solution
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/solutiondeployment/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> removeSolution(@PathVariable("id") String id) {
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
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Get solution types. This call returns the supported solution types in Agility.
	 * @param 
	 * @return Solution Types
	 */
	@RequestMapping(value = "/solutiontypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SolutionTypes> solutionTypes() {

		try {
			// Just return the Solution Types
			return new ResponseEntity<SolutionTypes>(new SolutionTypes(), HttpStatus.OK);

		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param projectJson
	 * @param req
	 * @return
Create Solution has following steps. 
1	Create Runtime in each env – To create run time each env we need to get the env first. Get Project call returns environments Array (["name" : "Development", "name" : "Production", "name" : "UAT/Stagin/Demo"]) . And from there we can take out the ENV (I think we will need the env ID) and create runtime. For now put a check in the Env if the Env is 
2	Create Runtime Command 
POST https:<agility_ip>:8443/agility/api/<api_version>/environment/44/runtime

3	Create the artifact 
POST https:<agility_ip>:8443/agility/api/<api_version>/solution/23/artifact

Once we create above items then we can create solution. Using below call.
POST project/{id}/solution

This is the json format for creating solution.
{
  
    "name": "TestSolutionSach0724",
    "description": "lq8",
    "parent": { "id": "1244" }
  
}
	 */
	@RequestMapping(value = "/createsolution", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createSolution(@RequestBody String solJson, HttpServletRequest req) {
		
		logger.debug("Received json data to create Solution : " + solJson);
		
		/*	----- JSON INPUT -----
		{
		 * 		   "ProjectID": "1244",
		 		   "SolutionType": "",
				   "SolutionName": "TestSolution",
				   "ArtifactName": "TestArtifact",
				   "T-ShirtSize": "Small"
				}
				
		*/		


		return null;
	}		
	
}
