package com.files.manager;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class LogLine 
{
	String exception;
	long dt;
	
	public LogLine(String exception, String dt) {
		this.exception = exception;
		this.dt = Long.parseLong(dt);
	}
}

class LogLineParser 
{
	/*
	 * */
	public ArrayList<LogLine> parser(String file) {
	
        ArrayList<LogLine> logLineObjs = new ArrayList<LogLine>();

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            reader.readNext();
            
            while ((line = reader.readNext()) != null) {
            		logLineObjs.add(new LogLine(line[2], line[1]));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return logLineObjs;
	}	
	public Map<String, Map<String,Integer>> formater(ArrayList<LogLine> fileObjs) {
		
		Map<String, Map<String,Integer>> resultMap = new HashMap<String, Map<String,Integer>>();
		Utils util = new Utils();
		
		for (LogLine file : fileObjs) {
			
			String slot = util.getSlot(file.dt);
			
			if (resultMap.get(file.exception) == null) {
				resultMap.put(file.exception, new HashMap<String,Integer>());
			}
			
			if (resultMap.get(file.exception).containsKey(slot) == false) {
				Map<String,Integer> timeCountMap = new HashMap<String,Integer>();
				timeCountMap.put(slot, 1);
				resultMap.put(file.exception, timeCountMap);
			} else {
				int val = resultMap.get(file.exception).get(slot) ;
				resultMap.get(file.exception).put(slot, val+1);
			}
			
		}
		
		return resultMap;
	}
}


class Worker implements Callable<ArrayList<LogLine>> {
   String file;
   LogLineParser lp = new LogLineParser();
   
   Worker(String file) {
	   this.file = file;
   }
   
   public ArrayList<LogLine> call() {
	   return lp.parser(file);
   }
}

public class App 
{

	private static final int MAX_THREADS = 5;

	public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
    		LogLineParser lp = new LogLineParser();
    		
    		// Store of all input files.
    		String[] inputfiles = new String [] {"/tmp/test1.csv", "/tmp/test2.csv","/tmp/test3.csv","/tmp/test4.csv","/tmp/test5.csv","/tmp/test6.csv","/tmp/test7.csv","/tmp/test8.csv","/tmp/test9.csv", "/tmp/test10.csv"};
    		
    		ArrayList<LogLine> logLines = new ArrayList<LogLine>();
    		
    		ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    		
    		for (int i = 0; i < inputfiles.length; i++) {
            Worker worker = new Worker(inputfiles[i]);
            Future<ArrayList<LogLine>> future = executor.submit(worker);
            logLines.addAll(future.get());
        }

        executor.shutdown();
        
        // Finally parsing all the results
        Map<String, Map<String,Integer>> resultMap = lp.formater(logLines);
		
		for(String k : resultMap.keySet()) {
		    Map<String, Integer> m1 = resultMap.get(k);

		    for(String k1 : m1.keySet()) {
		        System.out.println(k + "," + k1 + "," + m1.get(k1));
		    }
		}
    }
}
