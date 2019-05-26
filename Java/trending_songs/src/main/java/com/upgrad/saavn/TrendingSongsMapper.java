package com.upgrad.saavn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TrendingSongsMapper extends Mapper<LongWritable, Text, Text, DayCountWritable> {
	public static List<String> days = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31");
	
	/*
	 * Stores the song and its count. Count is stored uniquely based on the song id per day.
	 */
	public void map(LongWritable key, Text record, Context con) throws IOException, InterruptedException {
		
		// Getting text record contains song ID, user ID, timestamp, hour, date.
		String[] info = record.toString().split(",");
		
		String songid = info[0];
		
		// Getting the day from the particular date.
		String[] date = info[4].split("-");
		
		String day;
		
		// If the date is not valid it will not get processed.
		if (date.length > 1) {
			day = date[2];
			
			// If the day is not valid it will not get processed.
			if (days.contains(day)) {
				con.write(new Text(songid), new DayCountWritable(day, 1));
			}
		}
	}
}
