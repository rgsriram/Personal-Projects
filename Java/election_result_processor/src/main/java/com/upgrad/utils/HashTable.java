package com.upgrad.utils;

import java.util.*;


public class HashTable {
	Map<Integer, Integer> table = new Hashtable<Integer, Integer>(); 
	
	public boolean isPresent(int key) {
		/***
		 * This function returns true is x is present and false if x is not present-
		 * according to bloom filter.
		 */
		return table.containsKey(key);
	}
	
	public void add(int key, int val) {
		/***
		 * Build the bloom filter by adding the elements to it
		 */
		table.put(key, val);
	}
}