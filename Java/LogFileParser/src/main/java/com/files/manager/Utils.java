package com.files.manager;

import java.util.Calendar;
import java.util.Date;

public class Utils {
	/* 
	 * Takes the time in epoch time and convert into 15 min time slot.
	 * Input: Long
	 * Output: String
	 * */
	public String getSlot(long timeMs) {
		
		Date dt = new Date(timeMs);
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(dt);
	
	    	int unroundedMinutes = calendar.get(Calendar.MINUTE);
	    	int mod = unroundedMinutes % 15;
	    	calendar.add(Calendar.MINUTE, mod < 8 ? -mod : (15-mod));
	    	
	    	int mins = calendar.getTime().getMinutes();
	    	int hours = calendar.getTime().getHours();
	    	
	    	if (mins <= 15) {
	    		return hours + ":00" + '-' + hours + ":15";
	    	}
	    	else if (mins <= 30) {
	    		return hours + ":15" + '-' + hours + ":30";
	    	}
	    	else if (mins <= 45) {
	    		return hours + ":30" + '-' + hours + ":45";
	    	}
	    
	    	return hours + ":45" + '-' + hours + ":60";
	}
}
