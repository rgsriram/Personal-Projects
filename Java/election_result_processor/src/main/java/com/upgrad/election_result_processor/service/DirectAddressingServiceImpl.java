package com.upgrad.election_result_processor.service;

public class DirectAddressingServiceImpl extends ElectionActivityService {

	public long[] data;
	

	public DirectAddressingServiceImpl(int size) {
		data = new long[size];
	}

	@Override
	public long find(int key) {
		return data[key];
	}

	@Override
	void add(int... elements) {
		data[elements[0]] = elements[1];
	}

	@Override
	void count() {

	}

}
