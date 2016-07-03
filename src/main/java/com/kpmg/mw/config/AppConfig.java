package com.kpmg.mw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.kpmg.mw.MWConstants;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

	@Autowired
	Environment env;

	@Bean
	public CommonConfigBean getCommonConfiguration() {
		CommonConfigBean bean = new CommonConfigBean();

		if (env.getProperty("agility.apiVersion") != null)
			bean.setApiVersion(env.getProperty("agility.apiVersion"));
		else
			bean.setApiVersion(MWConstants.DEFAULT_API_VERSION);

		if (env.getProperty("agility.baseURL") != null)
			bean.setBaseURL(env.getProperty("agility.baseURL"));
		else
			bean.setBaseURL(MWConstants.DEFAULT_BASE_URL);

		return bean;
	}

}
