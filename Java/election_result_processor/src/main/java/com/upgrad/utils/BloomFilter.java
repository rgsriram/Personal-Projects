package com.upgrad.utils;

import java.util.BitSet;

public class BloomFilter {
	BitSet bs;// BloomFilter array of bits

	HashFunctions hashFunction;
	int collisionCount = 0;
	int hash1, hash2, hash3; // hash function values
	int size; // size of the bloom filter
	int numHashFunctions; // total number of hash functions to work with bloom filter

	public BloomFilter(int x, int y) {
		/***
		 * Constructor x indicate the size of bloom filter and y indicate the number of
		 * hash functions
		 */
		bs = new BitSet(x);
		bs.clear();
		hashFunction = new HashFunctions();
		size = bs.size();
		numHashFunctions = y;
	}

	public boolean isPresent(int x) {
		/***
		 * This function returns true is x is present and false if x is not present-
		 * according to bloom filter.
		 */
		hash1 = hashFunction.genHash(x);
		hash2 = hashFunction.genHash2(x);
		hash3 = hashFunction.genHash3(x);
		for (int i = 1; i <= numHashFunctions; i++) {
			if (bs.get(Math.abs((hash1 + i * hash2 + i * hash3) % size))) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	public void add(int x) {
		/***
		 * Build the bloom filter by adding the elements to it
		 */
		if (isPresent(x)) {
			collisionCount++;
		}
		hash1 = hashFunction.genHash(x);
		hash2 = hashFunction.genHash2(x);
		hash3 = hashFunction.genHash3(x);
		for (int i = 1; i <= numHashFunctions; i++) {
			bs.set(Math.abs((hash1 + i * hash2 + i * hash3) % size));
		}
	}
}