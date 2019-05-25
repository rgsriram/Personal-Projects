package com.projects.urlshortener;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortenURLRepository extends MongoRepository<ShortenURL, String> {
	
	/***
	 * findByShortenURL - Method will be used in case of redirection where we need to raw url for url.
	 * findByRawURL - Method will be used in case of creation of shorten url for raw url.
	 *  */
    public ShortenURL findByShortenURL(String url);
    public ShortenURL findOneById(ObjectId id);
    public ShortenURL findByRawURL(String rawURL);
}
