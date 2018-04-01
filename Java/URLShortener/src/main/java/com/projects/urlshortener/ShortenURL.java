package com.projects.urlshortener;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShortenURL {
	
	@JsonIgnore
	@Id
	public ObjectId id;
	
	public String rawURL;
	public String shortenURL;
	
	@JsonIgnore
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date createdDate = new Date();
	
	@JsonIgnore
	public double expireAt;
	public int noOfClicks;
	
	@JsonIgnore
	public String domain;
	
	@JsonIgnore
	public boolean isActive;
	
	public ShortenURL() {
		
	}
	
	public ShortenURL(String raw_url, String url) {
		this.rawURL = raw_url;
		this.shortenURL = url;
		System.out.println(this.createdDate.toString());
	}

	// Getters and Setters.
	public int getNoOfClicks() {
		return noOfClicks;
	}

	public void setNoOfClicks(int noOfClicks) {
		this.noOfClicks = noOfClicks;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String toString() {
		return "Shorten URL: " + this.shortenURL + ", Raw URL: " + this.rawURL;
	}
	
	
}
