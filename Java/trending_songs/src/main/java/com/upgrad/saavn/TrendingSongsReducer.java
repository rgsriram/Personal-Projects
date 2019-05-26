package com.upgrad.saavn;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TrendingSongsReducer extends Reducer<Text, DayCount, Text, Text> {
	
	/*
	 * Sums count for each song id and finally store the result.
	 */
	public void reduce(Text key, Iterable<DayCount> valueList, Context con)
			throws IOException, InterruptedException {
		
		int song_count = 0;
		
		// Sum count for each song based.
		for (DayCount var : valueList) {
			song_count += var.getCount();
		}
		
		// Output will be written as "songid :: count"
		String out = ":: " + song_count;
		con.write(key, new Text(out));
	}
}
