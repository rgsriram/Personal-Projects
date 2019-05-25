package com.upgrad.saavn;

import java.util.HashMap;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class TrendingSongsPartitioner extends Partitioner<Text, DateAndCount> implements Configurable {
	private Configuration configuration;
	HashMap<String, Integer> daymap = new HashMap<String, Integer>();

	// Set up the months hash map in the setConf method.

	public void setConf(Configuration configuration) {
		this.configuration = configuration;
		daymap.put("01", 0);
		daymap.put("02", 1);
		daymap.put("03", 2);
		daymap.put("04", 3);
		daymap.put("05", 4);
		daymap.put("06", 5);
		daymap.put("07", 6);
		daymap.put("08", 7);
		daymap.put("09", 8);
		daymap.put("10", 9);
		daymap.put("11", 10);
		daymap.put("12", 11);
		daymap.put("13", 12);
		daymap.put("14", 13);
		daymap.put("15", 14);
		daymap.put("16", 15);
		daymap.put("17", 16);
		daymap.put("18", 17);
		daymap.put("19", 18);
		daymap.put("20", 19);
		daymap.put("21", 20);
		daymap.put("22", 21);
		daymap.put("23", 22);
		daymap.put("24", 23);
		daymap.put("25", 24);
		daymap.put("26", 25);
		daymap.put("27", 26);
		daymap.put("28", 27);
		daymap.put("29", 28);
		daymap.put("30", 29);
		daymap.put("31", 30);

	}

	// Implement the getConf method for the Configurable interface.

	public Configuration getConf() {
		return configuration;
	}

	public int getPartition(Text key, DateAndCount value, int numReduceTasks) {
		return (int) (daymap.get(value.getDate()));
	}

}
