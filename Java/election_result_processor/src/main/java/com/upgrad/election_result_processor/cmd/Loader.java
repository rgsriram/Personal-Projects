package com.upgrad.election_result_processor.cmd;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.upgrad.utils.BloomFilter;
import com.upgrad.utils.CommonUtils;
import com.upgrad.utils.LoadProperties;

public class Loader {

	BloomFilter bloomFilter;
	Logger logger = Logger.getLogger(Loader.class);

	public BloomFilter loadDataToBloomFilter(String file) throws IOException {
		CSVReader csvReader = CommonUtils.CSVReader(file, 0);

		String[] nextRecord;

		int sizeOfBloomFilter = Integer.parseInt((String) LoadProperties.getProperties("sizOfBloomFilter"));
		int noOfHashFunctions = Integer.parseInt((String) LoadProperties.getProperties("noOfHashFunctions"));

		bloomFilter = new BloomFilter(sizeOfBloomFilter, noOfHashFunctions);

		while ((nextRecord = csvReader.readNext()) != null)
			bloomFilter.add(Integer.parseInt(nextRecord[0]));

		logger.info("Bloom filter loaded.");

		return bloomFilter;
	}
}