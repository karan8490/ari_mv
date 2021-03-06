package com.kpmg.mw.rest;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

	/**
	 * This is original Project creation JSON.
	 *	{
   	 *		"name": "TestProject12",
	 *  	"parent": {
	 *    	"id": "16"
	 *  	}
     *	}
	 * @param projectJson
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/createproject", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createProject(@RequestBody String projectJson, HttpServletRequest req) {
		
		logger.debug("Received json data to create project : " + projectJson);
		
		/*		{
				   "BusinessGroup": "Audit",
				   "name": "TestProject",
				   "ApplicationName": "TestApp",
				   "EngagementCode": "3423423",
				   "PlanviewID": "455322",
				   "SecurityZone": "Internal Controlled",
				   "ADGroup":"ActiveDirectoryGroup1",
				   "parent": {
				      "id": "1"
				   }
				}
				
		*/		

		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(projectJson).getAsJsonObject();
		JsonObject projectObj = new JsonObject();
		
		Gson gson = new Gson();
		
		if (jsonObj != null) {
			logger.info("Project create Json is " + jsonObj.toString());
			JsonObject parentObj = jsonObj.getAsJsonObject("parent");
			if (parentObj != null) {
				logger.debug("parent container value is " + parentObj.toString());
				// get Project Name from the JSON object
				String projName = jsonObj.get("name").getAsString();
				String containerId = parentObj.get("id").getAsString();
				
				// Create JSON object as expected by the agility api
				projectObj.addProperty("name", projName);
				JsonObject parentId = new JsonObject();
				parentId.addProperty("id", containerId);
				projectObj.add("parent", (JsonElement)parentId);
				
			} else {
				logger.warn("parent container id is not found");
			}
		} else {
				logger.warn("Json Object to create Project is not found");
		}
			
		final String uriString = commonConfigBean.getCompleteRequestURI("project");
		logger.debug("uri string formed as "+uriString);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(projectObj), headers);

		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriString, HttpMethod.POST, entity,
					String.class);
			String response = responseEntity.getBody();
			logger.debug("Received response as " + response);
			return responseEntity;
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return null;
		}	
	}	

	
	
}
