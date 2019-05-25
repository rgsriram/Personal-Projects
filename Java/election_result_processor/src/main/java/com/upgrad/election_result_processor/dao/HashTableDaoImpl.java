package com.upgrad.election_result_processor.dao;

import java.util.HashMap;
import java.util.Map;

public class HashTableDaoImpl implements IElectionActivityDao {
	public Map<Integer, Integer> table;

	public HashTableDaoImpl() {
		table = new HashMap<Integer, Integer>();
	}

	public long find(int key) {
		/***
		 * This function returns true is x is present and false if x is not present-
		 * according to bloom filter.
		 */
		if (table.containsKey(key)) {
			return table.get(key);
		}
		return 0;
	}

	public void add(int... elements) {
		if (elements[0] > 0 && elements[1] > 0) {
			table.put(elements[0], elements[1]);
		}
	}

	public long count(int key) {
		return find(key);
	}

}
