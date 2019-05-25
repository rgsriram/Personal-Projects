package com.upgrad.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CommonUtils {
	static BufferedReader bufferedReader = null;
	static FileReader fileReader = null;
	static CSVReader csvReader = null;
	private static Logger logger = Logger.getLogger(CommonUtils.class);

	public static CSVReader CSVReader(String fileName, int startFrom) {
		logger.info("Reading data from csv file...");

		String fileLocation = fileName;

		try {
			fileReader = new FileReader(fileLocation);
			logger.info("Completed.");
		} catch (FileNotFoundException e) {
			logger.error(e);
		}

		csvReader = new CSVReaderBuilder(fileReader).withSkipLines(startFrom).build();

		return csvReader;
	}

}
