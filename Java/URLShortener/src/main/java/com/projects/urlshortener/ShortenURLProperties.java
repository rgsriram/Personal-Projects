package com.projects.urlshortener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
public class ShortenURLProperties {
	private String domain;

	@Override
	public String toString() {
		return "ShortenURLProperties [domain=" + domain + "]";
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

}
