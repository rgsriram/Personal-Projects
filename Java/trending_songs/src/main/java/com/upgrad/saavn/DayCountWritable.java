package com.upgrad.saavn;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

/*
 * Class will have date and count. It is used in the mapper to keep and day and count for the given song.
 */
public class DayCountWritable implements Writable {
	
	// Setting day and count variables.
	String day;
	int count;

	public DayCountWritable() {super();}
	
	// Parameterized constructor to initialize the day and count.
	public DayCountWritable(String day, int count) {
	    this.day = day;
	    this.count = count;
	}

	// Setters and Getters class.
	public String getDate() {return day;}
	public void setDate(String number) {this.day = number;}
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}

	public void readFields(DataInput dataInput) throws IOException {
	    day = WritableUtils.readString(dataInput);
	    count = WritableUtils.readVInt(dataInput);      
	}

	public void write(DataOutput dataOutput) throws IOException {
	    WritableUtils.writeString(dataOutput, day);
	    WritableUtils.writeVInt(dataOutput, count);

	}

}
