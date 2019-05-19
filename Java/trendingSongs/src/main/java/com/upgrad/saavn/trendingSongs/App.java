package com.upgrad.saavn.trendingSongs;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {

	public static class MapperClass extends Mapper<Object, Text, Text, DateAndCount> {
		public static List<String> days = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31");

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

	public static class DayPartitioner extends Partitioner<Text, DateAndCount> implements Configurable {

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

	public static class ReducerClass extends Reducer<Text, DateAndCount, Text, Text> {

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

	public static void main(String[] args) throws Exception {

		Configuration conf;
		Job job;

		conf = new Configuration();

		job = Job.getInstance(conf, "trendingSongs");
		job.setJarByClass(App.class);

		job.setMapperClass(MapperClass.class);
		job.setReducerClass(ReducerClass.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DateAndCount.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));

		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setNumReduceTasks(31);

		/*
		 * Specify the partitioner class.
		 */
		job.setPartitionerClass(DayPartitioner.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
