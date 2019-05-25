package com.projects.urlshortener;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.urlshortener.ShortenURLRepository;
import com.projects.urlshortener.ShortenURLService;
import com.projects.urlshortener.ShortenURLProperties;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/api/v1/") 
public class ShortenURLController {
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	
	@Autowired 
	private ShortenURLRepository urlRepository;
	
	@Autowired
	public ShortenURLProperties prop;
	
	public ShortenURLService urlService = new ShortenURLService();
	
	@PostMapping("/short-url/")
	public ResponseEntity<ShortenURL> create(@RequestBody ShortenURL url) throws NoSuchAlgorithmException {

		if (urlService.isValidURL(url.rawURL) == false)
			throw new IllegalArgumentException("URL is invalid");
		
		url.shortenURL = prop.getDomain() + urlService.hash(url.rawURL);
		urlRepository.save(url);
		
		return new ResponseEntity<ShortenURL>(url, HttpStatus.OK);
	}
	
	@GetMapping(path="/count/")
	public @ResponseBody ResponseEntity<ShortenURL> count (@RequestParam String shortURL) {
		ShortenURL url = urlRepository.findByShortenURL(shortURL);
		return new ResponseEntity<ShortenURL>(url, HttpStatus.OK);
	}
	
}

