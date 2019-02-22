package com.upgrad.election_result_processor.service;

public abstract class ElectionActivityService {
	abstract long find(int x);
	abstract  void add(int ... key);
	abstract void count();
}
