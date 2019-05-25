Graded assignment: Data Structures
----------------------------------

Worst case complexity of in direct addressing with bloom filter: 
----------------------------------------------------------------

1. Loading bloom filter: Will take (Preprocessing cost of hash functions) + O(n) time
2. Adding elements: Here each element will be added to direct addressing after evaluated in the bloom filter. Insertion takes time with bloom filter the reason is, Example:
	a. Total insertion cost = (Validating the data in the bloom filter based on number of hash functions) + (adding an element in the direct addressing). 
	b. Cost will be around 2 O(n)  with is equivalent to O(n) for adding n data.  For this dataset it took around ~1.7 seconds execution time. 
3. Finding an element: Two cases are there
	a. If bloom filter says no then it will not even hit direct addressing. So time complexity is based on processing cost of number of hash functions for a given element.
	b. If bloom filter says yes then it may/may not exists in direct addressing. So time complexity for a given element is, Total time = (Processing cost of number of hash functions for bloom filter + O(1) for direct addressing). 
4. Count: Same as above case, Two cases are there
	a. If bloom filter says no then it will not even hit direct addressing. So time complexity  is based on processing cost of number of hash functions for a given element.
	b. If bloom filter says yes then it may/may not exists in direct addressing. So time complexity for a given element is, Total time = (Processing cost of number of hash functions for bloom filter + O(1) for direct addressing). 


Worst case complexity of in direct addressing without bloom filter: 
-------------------------------------------------------------------

1. Adding elements: Since we are adding, all the elements in direct addressing the time complexity will be O(n). This faster than with bloom filter the reason is, for each element it will check the bloom filter and then addition will happen. For this dataset it took around ~0.5 seconds for execution. 
2. Finding an element: It will be constant time. O(1).
3. Count: It will take constant time. O(1).


Worst case complexity of in hash tables with bloom filter: 
-----------------------------------------------------------

1. Loading bloom filter: Will take (Preprocessing cost of hash functions) + O(n) time
2. Adding elements: Here each element will be added to hash table after evaluated in the bloom filter. Insertion takes time with bloom filter the reason is, Example:
	a. Total insertion cost = (Validating the data in the bloom filter based on number of hash functions) + (Preprocessing cost of hash key) + (Writing data to hash table). 
	b. For this dataset it took around ~1.8 seconds execution time. 
3. Finding an element: Two cases are there
	a. If bloom filter says no then it will not even hit hash table. So time complexity is based on processing cost of number of hash functions for a given element.
	b. If bloom filter says yes then it may/may not exists in hash table. So time complexity for a given element is, Total time = (Processing cost of number of hash functions for bloom filter + ~ O(1) for finding an element in hash table, because also hashing which also has preprocessing cost). 
4. Count: Same as above case, Two cases are there
	a. If bloom filter says no then it will not even hit hash table. So time complexity  is based on processing cost of number of hash functions for a given element.
	b. If bloom filter says yes then it may/may not exists in hash table. So time complexity for a given element is, Total time = (Processing cost of number of hash functions for bloom filter + ~ O(1) for finding an element in hash table, because also hashing which also has preprocessing cost). 

Worst case complexity of in hash table without bloom filter: 
------------------------------------------------------------
1. Adding elements: Since we are adding, all the elements in hash table the time complexity will be (Preprocessing cost of internal hash functions) + O(n). This faster than with bloom filter the reason is, for each element it will check the bloom filter and then addition will happen. For this dataset it took around ~1 second for execution. 
2. Finding an element: It will take constant time. (Preprocessing cost of internal hash functions) + O(1) to read data.
3. Count: It will take constant time. (Preprocessing cost of internal hash functions) + O(1) to read data.


