package com.upgrad.saavn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TrendingSongsMapper extends Mapper<Object, Text, Text, DateAndCount> {
	public static List<String> days = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31");

	public void map(Object key, Text record, Context con) throws IOException, InterruptedException {

		String[] info = record.toString().split(",");
		String songid = info[0];
		String[] date = info[4].split("-");
		String day;
		if (date.length > 1) {
			day = date[2];

			if (days.contains(day)) {
				con.write(new Text(songid), new DateAndCount(day, 1));
			}
		}
	}
}
