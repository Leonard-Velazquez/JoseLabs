package com.josetechblog;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MaxMinTemp {

	public static class MaxTempMapper extends MapReduceBase implements
			Mapper<LongWritable, Text, Text, Text> {

		@Override
		public void map(LongWritable arg0, Text value,
				OutputCollector<Text, Text> output, Reporter arg3)
				throws IOException {
			// TODO Auto-generated method stub

			String line = value.toString();

			String date = line.substring(6, 14);

			float max_temp = Float.parseFloat(line.substring(39, 45).trim());
			float min_temp = Float.parseFloat(line.substring(47, 53).trim());

			if (max_temp > 40.0) {
				output.collect(new Text("Hot Day" + date),
						new Text(String.valueOf(max_temp)));
			}

			if (min_temp < 10) {
				output.collect(new Text("Cold Day" + date),
						new Text(String.valueOf(min_temp)));
			}

		}

	}

	public static class MaxTempReducer extends MapReduceBase implements
			Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterator<Text> value,
				OutputCollector<Text, Text> output, Reporter arg3)
				throws IOException {
			String temp = value.next().toString();
			output.collect(key, new Text(temp));

		}
	}

	public static void main(String args[]) throws IOException {

		JobConf job = new JobConf(MaxMinTemp.class);
		job.setJobName("temprature");

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setMapperClass(MaxTempMapper.class);
		job.setReducerClass(MaxTempReducer.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		JobClient.runJob(job);
	}

}
