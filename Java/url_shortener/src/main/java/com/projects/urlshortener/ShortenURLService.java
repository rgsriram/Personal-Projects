package com.projects.urlshortener;

import java.security.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;


@Service
public class ShortenURLService {
	
    public int md5(String s) throws NoSuchAlgorithmException{
    	
    	    long salt = System.currentTimeMillis() / 1000L;
    	    s += salt;
    	    
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(s.getBytes(),0,s.length());
		
		return m.digest().hashCode();
		
    	}
 
	
	public String toBase62(int num) {
			
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] alphabets = alphabet.toCharArray();
		
		if (num == 0)
			return Character.toString(alphabets[0]);
		
		int base = alphabets.length;
		List<Character> encodedStr = new ArrayList<Character>();
	
		while (num > 0) {
			int rem = num % base;
			num /= base;
			encodedStr.add(alphabets[rem]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (Character character : encodedStr) {
			sb.append(character);
		}
		
		return sb.toString();
	}
	
	public String hash(String str) throws NoSuchAlgorithmException {
		return toBase62(md5(str));
	}
	
	public boolean isValidURL(String URL) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(URL);
	}

}
