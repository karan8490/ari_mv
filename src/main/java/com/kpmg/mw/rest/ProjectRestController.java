package com.kpmg.mw.rest;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kpmg.mw.config.CommonConfigBean;
import com.kpmg.mw.vo.ProjectVO;

/**
 * This controller would contain all the REST APIs for Project resource
 * 
 * @author
 *
 */

@RestController
public class ProjectRestController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfigBean commonConfigBean;

	private Logger logger = LoggerFactory.getLogger(ProjectRestController.class);

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ProjectVO getAllProjects(HttpServletResponse response) {
		logger.info("Inside get project method");
		final String uri = commonConfigBean.getCompleteRequestURI("project");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ProjectVO projectVO;
		ResponseEntity<ProjectVO> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ProjectVO.class);
			projectVO = responseEntity.getBody();
			projectVO.setSuccess(true);
			return projectVO;
		} catch (RestClientException e) {
			projectVO = new ProjectVO();
			projectVO.setSuccess(false);
			projectVO.setMessage("");
			if (responseEntity != null)
				response.setStatus(responseEntity.getStatusCodeValue());
			else
				logger.info("response entity is null");
			return projectVO;
		}

	}

}
