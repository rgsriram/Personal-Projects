package com.upgrad.election_result_processor.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.upgrad.election_result_processor.dao.DirectAddressingDaoImpl;
import com.upgrad.utils.BloomFilter;
import com.upgrad.utils.CommonUtils;
import com.upgrad.utils.Constants;
import com.upgrad.utils.LoadProperties;

public class DirectAddressingDriverServiceImpl implements IElectionActivityService {
	private static BloomFilter bloomFilter;
	private static DirectAddressingDaoImpl voterListAfterBF;
	private static DirectAddressingDaoImpl candidateCountAfterBF;

	private static DirectAddressingDaoImpl voterListWithOutBF;
	private static DirectAddressingDaoImpl candidateCountWithOutBF;

	private static int falsePositive;

	private static Logger logger = Logger.getLogger(CommonUtils.class);
	int binSortArraySize = Integer.parseInt((String) LoadProperties.getProperties("binSortArraySize"));

	/***
	 * Purpose: Add data to array (direct addressing) without using bloom filter
	 * from given csv
	 */
	public void withOutBloomFilter(String evmFile) {

		voterListWithOutBF = new DirectAddressingDaoImpl(binSortArraySize);
		candidateCountWithOutBF = new DirectAddressingDaoImpl(binSortArraySize);

		try {
			String fileLocation = evmFile;
			String[] nextRecord;
			CSVReader csvReader = CommonUtils.CSVReader(fileLocation, 1);

			while ((nextRecord = csvReader.readNext()) != null) {
				String[] voterCandidateID = nextRecord[0].split(" ");
				int voterID = Integer.parseInt(voterCandidateID[0]);
				int candidateID = Integer.parseInt(voterCandidateID[1]);
				addVoterWithoutBloomFilter(voterID, candidateID);
			}
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException("Error loading data to Bloom Filter. Exiting...");
		}

	}

	/***
	 * Purpose: Add data to array (direct addressing) with bloom filter from given
	 * csv.
	 * 
	 */
	public void withBloomFilter(String evmFile, BloomFilter bloomFilter) {
		voterListAfterBF = new DirectAddressingDaoImpl(binSortArraySize);
		candidateCountAfterBF = new DirectAddressingDaoImpl(binSortArraySize);

		DirectAddressingDriverServiceImpl.bloomFilter = bloomFilter;

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
			throw new RuntimeException("Error loading data to Bloom Filter. Exiting...");
		}

	}

	/***
	 * Giving the no of votes for an given candidate.
	 */
	public void getCandidCount(int candidateID, String candidateFilter) {
		switch (candidateFilter) {

		case Constants.WITH_BF:
			logger.info("Finding the candidate ID with bloom filter.");
			int find = (int) candidateCountAfterBF.find(candidateID);
			System.out.println("Number of Candidates: " + find);
			logger.info("Number of Candidates: " + find);
			break;

		case Constants.WITHOUT_BF:
			logger.info("Finding the candidate ID with out bloom filter.");
			long candidCount = candidateCountWithOutBF.find(candidateID);
			System.out.println("Number of Candidates: " + candidCount);
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
			int vote = candidateCountAfterBF.data[candidateID];
			candidateCountAfterBF.add(candidateID, vote + 1);

		}
	}

	/***
	 * Purpose: Add voter ids to bloom filter.
	 * 
	 * @param voterID
	 * @param candidateID
	 */

	private void addVoterWithoutBloomFilter(int voterID, int candidateID) {
		voterListWithOutBF.add(voterID, candidateID);
		int vote = candidateCountWithOutBF.data[candidateID];
		candidateCountWithOutBF.add(candidateID, vote + 1);
	}

	/***
	 * Purpose: Find candidate for whom the vote is casted by voter using bloom
	 * filter.
	 * 
	 * @param voterID
	 */
	public void getCandidateWithBloomFilter(int voterID) {

		if (this.getVoterIDFromBloomFilter(voterID)) {
			long candidateID = voterListAfterBF.find(voterID);

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
	 * Purpose: Find candidate for whom the vote is casted by voter without using
	 * bloom filter
	 * 
	 * @param voterID
	 */
	public void getCandidateWithOutBloomFilter(int voterID) {
		int startOffset = Integer.parseInt((String) LoadProperties.getProperties("startOffset"));
		int endLimit = Integer.parseInt((String) LoadProperties.getProperties("endLimit"));

		if (voterID < startOffset || voterID > endLimit) {
			System.out.println("Invalid voter");
			return;
		}

		int missingFrom = Integer.parseInt((String) LoadProperties.getProperties("startIndex"));
		int missingTo = Integer.parseInt((String) LoadProperties.getProperties("endIndex"));

		if (voterID >= missingFrom) {
			if (voterID < missingTo) {
				System.out.println("Invalid voter");
				return;
			}
		}

		long candidateID = voterListWithOutBF.find(voterID);

		if (candidateID != 0) {
			System.out.println("Voter: " + voterID + " casted vote for the candidate: " + candidateID);
		} else {
			System.out.println("Voter: " + voterID + " not casted any vote");
		}
	}

}
