package com.projects.urlshortener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private ShortenURLRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
//		repository.deleteAll();
//
//		// save a couple of customers
//		repository.save(new ShortenURL("https://www.facebook.com/sriram.ganesh2", "http://localhost/bat4444"));
//		System.out.println("ShortenURL: " + repository.findByShortenURL("http://localhost/bat4444"));
		
	}
	
}
