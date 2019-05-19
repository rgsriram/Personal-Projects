package com.upgrad.saavn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {

	public static void main(String[] args) throws Exception {

		Configuration conf;
		Job job;

		conf = new Configuration();

		job = Job.getInstance(conf, "saavn");
		job.setJarByClass(App.class);

		job.setMapperClass(TrendingSongsMapper.class);
		job.setReducerClass(TrendingSongsReducer.class);

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
		job.setPartitionerClass(TrendingSongsPartitioner.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
