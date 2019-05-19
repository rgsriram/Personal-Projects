package com.upgrad.saavn;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TrendingSongsReducer extends Reducer<Text, DateAndCount, Text, Text> {
	public void reduce(Text key, Iterable<DateAndCount> valueList, Context con)
			throws IOException, InterruptedException {
		int song_count = 0;
		for (DateAndCount var : valueList) {
			song_count += var.getCount();
		}
		String out = "::" + song_count;
		con.write(key, new Text(out));
	}
}
