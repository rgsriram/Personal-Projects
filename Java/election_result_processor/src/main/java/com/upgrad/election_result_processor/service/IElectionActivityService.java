package com.upgrad.election_result_processor.service;

import com.upgrad.utils.BloomFilter;

public interface IElectionActivityService {

	public void withOutBloomFilter(String file);

	public void withBloomFilter(String file, BloomFilter bloomFilter);

	public void getCandidCount(int candidID, String candidateFilter);

	public void getCandidateWithBloomFilter(int voterID);

	public void getCandidateWithOutBloomFilter(int voterID);

	public int validateFalsePositive(int start, int end);
}
