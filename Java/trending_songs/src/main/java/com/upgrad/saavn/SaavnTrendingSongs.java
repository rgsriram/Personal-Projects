package com.upgrad.saavn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
 * Driver class which sets configurations. Invoke Mapper, Partitioner, and Reducer.
 */
public class SaavnTrendingSongs {

	public static void main(String[] args) throws Exception {

		Configuration conf;
		Job job;

		conf = new Configuration();

		job = Job.getInstance(conf, "saavn");
		job.setJarByClass(SaavnTrendingSongs.class);
		
		// Setting Mapper, and Reducer class.
		job.setMapperClass(TrendingSongsMapper.class);
		job.setReducerClass(TrendingSongsReducer.class);
		
		// Setting Mapper output key, and value type
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DayCount.class);
		
		// Setting Reducer output key, and value type
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// Getting input and output path from the command line.
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// Setting number of reducer.Since there are 31 partitioner, we have one to one mapping between partitioner and reducer. Load will be balanced properly,
		job.setNumReduceTasks(31);

		// Specify the partitioner class.
		job.setPartitionerClass(TrendingSongsPartitioner.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
