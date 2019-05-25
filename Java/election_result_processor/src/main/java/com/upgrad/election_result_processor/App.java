package com.upgrad.election_result_processor;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.upgrad.election_result_processor.cmd.Loader;
import com.upgrad.election_result_processor.service.DirectAddressingDriverServiceImpl;
import com.upgrad.election_result_processor.service.HashTableDriverServiceImpl;
import com.upgrad.election_result_processor.service.IElectionActivityService;
import com.upgrad.utils.BloomFilter;
import com.upgrad.utils.Constants;
import com.upgrad.utils.LoadProperties;

public class App {
	private static LoadProperties loadProps = new LoadProperties(Constants.PROP_FILE);
	private static Loader command = new Loader();
	private static String evmFile = Constants.EVM_FILE;
	private static String voterFile = Constants.VOTER_ID_FILE;
	private static BloomFilter bloomFilter;

	private static IElectionActivityService directAddressingService = new DirectAddressingDriverServiceImpl();
	private static IElectionActivityService hashTableService = new HashTableDriverServiceImpl();
	private static Scanner scn;
	private static Logger logger = Logger.getLogger(App.class);
	static int missingFrom;
	static int missingTo;

	public static void main(String[] args) throws IOException {

		int caseScan = 0;
		int noOfInputs = Integer.parseInt((String) LoadProperties.getProperties("noOfInput"));
		long startTime;
		long endTime;
		float totalTime;

		bloomFilter = command.loadDataToBloomFilter(voterFile);
		logger.info("Data loaded to Bloom Filter");
		directAddressingService.withBloomFilter(evmFile, bloomFilter);
		directAddressingService.withOutBloomFilter(evmFile);
		hashTableService.withBloomFilter(evmFile, bloomFilter);
		hashTableService.withOutBloomFilter(evmFile);
		scn = new Scanner(System.in);

		System.out.println("\nEnter any of the following options:\n"
				+ "1. Find the candidate for an given voter id using Direct addressing with bloom filter\n"
				+ "2. Get the vote count for cadidate using Direct Addressing\n"
				+ "3. Find the candidate for an given voter id using Hash table with bloom filter\n"
				+ "4. Get the vote count for cadidate using Hash Table\n" + "5. Exit\n");

		while (caseScan <= noOfInputs) {

			caseScan = scn.nextInt();

			// If selected option is not available in switch case it will exit the program
			switch (caseScan) {
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_BLOOM_FILTER
			 * please enter #1
			 * 
			 */
			case Constants.FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_BLOOM_FILTER:
				System.out.println("\nPlease enter the voter id: ");
				int voterID = scn.nextInt();
				startTime = System.currentTimeMillis();
				directAddressingService.getCandidateWithBloomFilter(voterID);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute GET_CANDIDATE_COUNT_USING_DIRECT_ADDRESSING : please enter #2
			 * 
			 */
			case Constants.GET_CANDIDATE_COUNT_USING_DIRECT_ADDRESSING:
				System.out.println("\nPlease enter candidate id: ");
				int candidID = scn.nextInt();
				startTime = System.currentTimeMillis();
				directAddressingService.getCandidCount(candidID, Constants.WITH_BF);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_BLOOM_FILTER : please
			 * enter #3
			 * 
			 */
			case Constants.FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_BLOOM_FILTER:
				System.out.println("\nPlease enter the voter id: ");
				int voterIDHashTable = scn.nextInt();
				startTime = System.currentTimeMillis();
				hashTableService.getCandidateWithBloomFilter(voterIDHashTable);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute GET_CANDIDATE_COUNT_USING_HASH_TABLE : please enter #4
			 * 
			 */
			case Constants.GET_CANDIDATE_COUNT_USING_HASH_TABLE:
				System.out.println("\nPlease enter candidate id: ");
				int candidateID = scn.nextInt();
				startTime = System.currentTimeMillis();
				hashTableService.getCandidCount(candidateID, Constants.WITH_BF);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER:
			 * Please enter #5
			 */
			case Constants.EXIT:
				System.out.println("Exited");
				System.exit(0);
				break;
			/***
			 * To execute
			 * FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_OUT_BLOOM_FILTER: Please
			 * enter #6
			 */
			case Constants.FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_OUT_BLOOM_FILTER:
				System.out.println("\nPlease enter the voter id: ");
				int voterId = scn.nextInt();
				startTime = System.currentTimeMillis();
				directAddressingService.getCandidateWithOutBloomFilter(voterId);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER:
			 * Please enter #7
			 */
			case Constants.FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER:
				System.out.println("\nPlease enter the voter id: ");
				int voterIdHashTable = scn.nextInt();
				startTime = System.currentTimeMillis();
				hashTableService.getCandidateWithOutBloomFilter(voterIdHashTable);
				endTime = System.currentTimeMillis();
				totalTime = (endTime - startTime) / 1000F;
				System.out.println("Total time taken to run (in secs): " + totalTime);
				break;
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER:
			 * Please enter #8
			 */
			case Constants.CHECK_FALSE_POSITIVE_IN_DIRECT_ADDRESSING:
				missingFrom = Integer.parseInt((String) LoadProperties.getProperties("startIndex"));
				missingTo = Integer.parseInt((String) LoadProperties.getProperties("endIndex"));
				System.out.println("Total number of false postives (Bloom filter + Direct addressing): "
						+ directAddressingService.validateFalsePositive(missingFrom, missingTo));
				break;
			/***
			 * To execute FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER:
			 * Please enter #9
			 */
			case Constants.CHECK_FALSE_POSITIVE_IN_HASH_TABLE:
				missingFrom = Integer.parseInt((String) LoadProperties.getProperties("startIndex"));
				missingTo = Integer.parseInt((String) LoadProperties.getProperties("endIndex"));
				System.out.println("Total number of false postives (Bloom filter + Hash Table): "
						+ hashTableService.validateFalsePositive(missingFrom, missingTo));
				break;

			default:
				System.out.println("Exited");
				System.exit(0);
				break;
			}
		}
	}
}