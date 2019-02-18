package com.upgrad.utils;

public class HashFunctions {
	
	//typical hash function
	public int genHash(int x) {
		return x * 31;
	}
	
	public int genHash2(int x) {
		return x * 61;
	}
	
	public int genHash3(int x) {
		return x * 89;
	}
	
}