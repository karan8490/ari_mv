package com.kpmg.mw.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * This is a spring java configuration class. This class configured RestTemplate
 * to use SSL Socket factory.
 * 
 * @author
 *
 */

@Configuration
public class MiddlewareBeanConfig {
	private static final Logger logger = LoggerFactory.getLogger(MiddlewareBeanConfig.class);

	@Bean
	public RestTemplate restTemplate() {

		/**
		 * 
		 * Currently trust all strategy is being used. This should be replaced
		 * by more secure code like (trust only a particular host etc)
		 */

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);

			RestTemplate restTemplate = new RestTemplate(requestFactory);
			FormHttpMessageConverter converter = new FormHttpMessageConverter();
			restTemplate.getMessageConverters().add(converter);
			return restTemplate;

		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			logger.error(e.getMessage(), e);
			return new RestTemplate();
		}

	}

}
