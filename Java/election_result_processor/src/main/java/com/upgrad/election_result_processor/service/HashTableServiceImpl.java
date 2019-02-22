package com.upgrad.election_result_processor.service;

import java.util.Hashtable;
import java.util.Map;

public class HashTableServiceImpl extends ElectionActivityService {
	Map<Integer, Integer> table;

	public HashTableServiceImpl() {
		table = new Hashtable<Integer, Integer>();
	}

	@Override
	public long find(int key) {
		/***
		 * This function returns true is x is present and false if x is not present-
		 * according to bloom filter.
		 */
		if (table.containsKey(key)) {
			return 1;
		}
		return 0;

	}

	@Override
	void add(int... elements) {
		if (elements[0] != 0 && elements[1] != 0) {
			table.put(elements[0], elements[1]);
		}
	}

	@Override
	void count() {

	}

}
