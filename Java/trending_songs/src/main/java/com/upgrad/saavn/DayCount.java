package com.upgrad.saavn;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

/*
 * Class will have date and count. It is used in the mapper to keep and day and count for the given song.
 */
public class DayCount implements Writable {
	String date;
	int count;

	public DayCount() {super();}

	public DayCount(String date, int count) {
	    this.date = date;
	    this.count = count;
	}

	public String getDate() {return date;}
	public void setDate(String number) {this.date = number;}
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}

	public void readFields(DataInput dataInput) throws IOException {
	    date = WritableUtils.readString(dataInput);
	    count = WritableUtils.readVInt(dataInput);      
	}

	public void write(DataOutput dataOutput) throws IOException {
	    WritableUtils.writeString(dataOutput, date);
	    WritableUtils.writeVInt(dataOutput, count);

	}

}
