package com.projects.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projects.urlshortener.ShortenURLRepository;
import com.projects.urlshortener.ShortenURLProperties;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/") 
public class ShortenURLRedirectController {
	@Autowired
	public ShortenURLProperties prop;

	@Autowired 
	private ShortenURLRepository urlRepository;
	
	@RequestMapping(value = "{shortURL}", method = RequestMethod.GET)
	public String redirect (@PathVariable("shortURL") String shortURL) {
		ShortenURL url = urlRepository.findByShortenURL(prop.getDomain() + shortURL);
		url.noOfClicks += 1;
		urlRepository.save(url);
		return "redirect:" + url.rawURL;
	}
	
}
