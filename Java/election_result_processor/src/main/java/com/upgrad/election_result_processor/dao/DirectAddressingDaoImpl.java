package com.upgrad.election_result_processor.dao;

public class DirectAddressingDaoImpl implements IElectionActivityDao {

	public int[] data;

	public DirectAddressingDaoImpl(int size) {
		data = new int[size];
	}

	public long find(int key) {
		if (key != 0) {
			return data[key];
		}
		return 0;
	}

	public void add(int... elements) {
		data[elements[0]] = elements[1];

	}

	public long count(int key) {
		return find(key);
	}
}
