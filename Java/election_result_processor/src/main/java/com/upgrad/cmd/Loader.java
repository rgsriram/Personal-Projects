package com.upgrad.cmd;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.upgrad.utils.BloomFilter;

public class Loader {
	static BufferedReader br = null;
	static FileReader fr = null;
	static CSVReader cr = null;
	BloomFilter bf;
	
	public BloomFilter loadDataToBloomFilter(String file) throws IOException {
		String fileLocation = file;
		try {
			fr = new FileReader(fileLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cr = new CSVReaderBuilder(fr).withSkipLines(1).build();
		String[] nextRecord;
		bf = new BloomFilter(267000000, 5);
		while ((nextRecord = cr.readNext()) != null) {
			System.out.println(nextRecord[0]);
			bf.add(Integer.parseInt(nextRecord[0]));
		}
		
		return bf;
	}
}