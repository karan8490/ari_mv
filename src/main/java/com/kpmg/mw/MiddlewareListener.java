package com.kpmg.mw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MiddlewareListener {

	private static final Logger logger = LoggerFactory.getLogger(MiddlewareListener.class);

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		/*

		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// Don't do anything.
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				// TODO Auto-generated method stub
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				// TODO Auto-generated method stub
			}
		};

		SSLContext sslcontext;
		try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { trustManager }, null);
			SSLContext.setDefault(sslcontext);
			
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		}
		*/

	}

}
