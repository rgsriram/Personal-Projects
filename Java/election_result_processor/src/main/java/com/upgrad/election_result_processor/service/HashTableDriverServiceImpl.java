package com.upgrad.election_result_processor.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.upgrad.election_result_processor.dao.HashTableDaoImpl;
import com.upgrad.utils.BloomFilter;
import com.upgrad.utils.CommonUtils;
import com.upgrad.utils.Constants;
import com.upgrad.utils.LoadProperties;

public class HashTableDriverServiceImpl implements IElectionActivityService {
	private static BloomFilter bloomFilter;
	private static HashTableDaoImpl voterListAfterBF = new HashTableDaoImpl();
	private static HashTableDaoImpl candidateCountAfterBF = new HashTableDaoImpl();

	private static HashTableDaoImpl voterListWithOutBF = new HashTableDaoImpl();
	private static HashTableDaoImpl candidateCountWithOutBF = new HashTableDaoImpl();
	private static int falsePositive;
	private static Logger logger = Logger.getLogger(CommonUtils.class);

	/***
	 * Purpose: Add data to hashmap without using bloom filter from given csv.
	 */
	public void withOutBloomFilter(String evmFile) {
		try {
			String fileLocation = evmFile;
			String[] nextRecord;
			CSVReader csvReader = CommonUtils.CSVReader(fileLocation, 1);
			while ((nextRecord = csvReader.readNext()) != null) {
				String[] voterCandidateID = nextRecord[0].split(" ");
				int voterID = Integer.parseInt(voterCandidateID[0]);
				int candidateID = Integer.parseInt(voterCandidateID[1]);
				addToHashTable(voterID, candidateID);
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	/***
	 * Purpose: Add data to hashmap with bloom filter from given csv.
	 * 
	 */
	public void withBloomFilter(String evmFile, BloomFilter bloomFilter) {
		HashTableDriverServiceImpl.bloomFilter = bloomFilter;
		try {
			String fileLocation = evmFile;
			String[] nextRecord;
			CSVReader csvReader = CommonUtils.CSVReader(fileLocation, 1);
			while ((nextRecord = csvReader.readNext()) != null) {
				String[] voterCandidateID = nextRecord[0].split(" ");
				int voterID = Integer.parseInt(voterCandidateID[0]);
				int candidateID = Integer.parseInt(voterCandidateID[1]);
				addVoterUsingBloomFilter(voterID, candidateID);
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	/***
	 * Giving the no of votes for an given candidate.
	 */
	public void getCandidCount(int candidID, String type) {
		switch (type) {

		case Constants.WITH_BF:
			System.out.println("Finding the candidID");
			long find = candidateCountAfterBF.find(candidID);
			System.out.println("Number of Candidates: " + find);
			break;
		case Constants.WITHOUT_BF:
			System.out.println("Finding the candidID");
			long candidateCount = candidateCountWithOutBF.find(candidID);
			System.out.println("Number of Candidates: " + candidateCount);
			break;

		}

	}

	public boolean getVoterIDFromBloomFilter(int voterID) {
		// Check given voter ID is present in bloom filter.
		return bloomFilter.isPresent(voterID);
	}

	public boolean checkFalsePositive(int voterID) {
		if (this.getVoterIDFromBloomFilter(voterID)) {
			return voterListAfterBF.find(voterID) == 0;
		}
		return false;
	}

	/***
	 * Purpose: Validate false postives for the bloom filter. Since we intentionally
	 * missed a range we are using the same range to validate false postivies.
	 */
	public int validateFalsePositive(int start, int end) {
		for (int i = start; i <= end; i++) {
			if (this.checkFalsePositive(i))
				falsePositive++;
		}

		return falsePositive;
	}

	/***
	 * Purpose: Add voter ids to bin sort using bloom filter. Intentionally avoiding
	 * certain range to evaluate fps in bloom filter.
	 * 
	 * @param voterID
	 * @param candidateID
	 */

	private void addVoterUsingBloomFilter(int voterID, int candidateID) {
		int missingFrom = Integer.parseInt((String) LoadProperties.getProperties("startIndex"));
		int missingTo = Integer.parseInt((String) LoadProperties.getProperties("endIndex"));

		if (this.getVoterIDFromBloomFilter(voterID)) {
			if (voterID > missingFrom && voterID < missingTo) {
				return;
			}

			voterListAfterBF.add(voterID, candidateID);
			incrementCandidateVote(candidateID, voterID, Constants.WITH_BF);

		}
	}

	private void addToHashTable(int voterID, int candidateID) {
		voterListWithOutBF.add(voterID, candidateID);
		incrementCandidateVote(candidateID, voterID, Constants.WITHOUT_BF);
	}

	private void incrementCandidateVote(int candidateID, int value, String type) {
		switch (type) {

		case Constants.WITH_BF:
			if (candidateCountAfterBF.table.get(candidateID) != null) {
				int vote = candidateCountAfterBF.table.get(candidateID);
				candidateCountAfterBF.add(candidateID, vote + 1);
			} else {
				candidateCountAfterBF.add(candidateID, 1);
			}

		case Constants.WITHOUT_BF:
			if (candidateCountWithOutBF.table.get(candidateID) != null) {
				int vote = candidateCountWithOutBF.table.get(candidateID);
				candidateCountWithOutBF.add(candidateID, vote + 1);
			} else {
				candidateCountWithOutBF.add(candidateID, 1);
			}
		}
	}

	/***
	 * Purpose: Find candidate for whom the vote is casted by voter using bloom
	 * filter.
	 * 
	 * @param voterID
	 */
	public void getCandidateWithBloomFilter(int voterID) {

		if (this.getVoterIDFromBloomFilter(voterID)) {

			if (!voterListAfterBF.table.containsKey(voterID)) {
				System.out.println("Invalid voter");
				return;
			}

			long candidateID = voterListAfterBF.table.get(voterID);

			if (candidateID != 0) {
				System.out.println("Voter: " + voterID + " casted vote for the candidate: " + candidateID);
			} else {
				System.out.println("Voter: " + voterID + " not casted any vote");
			}

		} else {
			System.out.println("Invalid voter");
		}
	}

	/***
	 * Purpose: Find candidate for whom the vote is casted by voter using bloom
	 * filter.
	 * 
	 * @param voterID
	 */
	public void getCandidateWithOutBloomFilter(int voterID) {

		if (!voterListWithOutBF.table.containsKey(voterID)) {
			System.out.println("Invalid voter");
			return;
		}

		long candidateID = voterListWithOutBF.find(voterID);

		if (candidateID != 0) {
			System.out.println("Voter: " + voterID + " casted vote for the candidate: " + candidateID);
		} else {
			System.out.println("Invalid voter");
		}

	}

}
