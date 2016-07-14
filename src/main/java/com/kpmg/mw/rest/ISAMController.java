package com.kpmg.mw.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

@RestController
public class ISAMController {

	private Logger logger = LoggerFactory.getLogger(ISAMController.class);

	@RequestMapping(value = "/isammigration", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> authenticate(@RequestBody String json, HttpServletRequest req) {
		logger.debug("Received json as " + json);

		// save to a file

		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();

		String applicationType = req.getParameter("applicationType");

		JsonObject authObj = jsonObj.getAsJsonObject("auth_input");

		String ipContent = "";

		if (authObj != null) {
			logger.info("Auth object is " + authObj.toString());
			JsonPrimitive ip = authObj.getAsJsonPrimitive("server_lmi_ip");
			if (ip != null) {
				logger.debug("ip value is " + ip.getAsString());
				ipContent = ip.getAsString();
			} else {
				logger.warn("IP object is not found");
			}
		} else {
			logger.warn("Auth object is not found");
		}

		ServletContext context = req.getSession().getServletContext();

		String jsonFileName = "config.json";
		if (applicationType != null && !applicationType.trim().isEmpty()) {
			jsonFileName = applicationType + ".json";
		}

		File file = new File(context.getRealPath("/") + "/" + jsonFileName);
		File ipFile = new File(context.getRealPath("/") + "/ip.txt");

		try {

			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();

			FileWriter ipWriter = new FileWriter(ipFile);
			ipWriter.write(ipContent);
			ipWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
