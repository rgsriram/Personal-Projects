package com.upgrad.election_result_processor.dao;

public interface IElectionActivityDao {
	long find(int x);

	void add(int... key);

	long count(int key);
}
