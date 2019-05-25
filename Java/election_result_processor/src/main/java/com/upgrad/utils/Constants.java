package com.upgrad.utils;

public interface Constants {
	public static String WITH_BF = "WITH_BF";
	public static String WITHOUT_BF = "WITHOUT_BF";

	public static String PROP_FILE = "./app.properties";

	public static String EVM_FILE = "./data/votersCandList.txt";
	public static String VOTER_ID_FILE = "./data/validVotersList.txt";

	public static int FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_BLOOM_FILTER = 1;
	public static int GET_CANDIDATE_COUNT_USING_DIRECT_ADDRESSING = 2;
	public static int FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_BLOOM_FILTER = 3;
	public static int GET_CANDIDATE_COUNT_USING_HASH_TABLE = 4;
	public static int EXIT = 5;
	
	public static int FIND_CANDIATE_FOR_VOTER_IN_DIRECT_ADDRESSING_WITH_OUT_BLOOM_FILTER = 6;
	public static int FIND_CANDIATE_FOR_VOTER_IN_HASH_TABLE_WITH_OUT_BLOOM_FILTER = 7;
	
	public static int CHECK_FALSE_POSITIVE_IN_DIRECT_ADDRESSING = 8;
	public static int CHECK_FALSE_POSITIVE_IN_HASH_TABLE = 9;

	

}
