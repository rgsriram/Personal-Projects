Graded assignment: Find trending songs using map-reduce framework with saavn data
---------------------------------------------------------------------------------

Implementation Logic
--------------------

Mapper:
-------
1. Mapper takes the line contains song ID, user ID, timestamp, hour, date.
2. It stores the count per song and the date using the custom writable class called DayCount. DayCount class keeps the count per day for each song. 
3. Finally mapper returns the song id and DayCount writable (Count per day) .

Partitioner:
------------
1. Usage of partitioner here is, it makes sure that all the values for each key are grouped together after the map phase and also 
further ensures that all values or records for a particular key are sent to the same reducer. 
2. Since we want trending songs per day wise it make sense to use partitioner here, so all the trending songs for one day will go to one reducer because of the partitioner.


Reducer:
--------

1. Since we have 31 partitioner, there are 31 reducers. Each reducer computes trending songs per day. 
2. Because of the partitioner each reducer receives the data for one day. It sums up the count for each song and it will be returned.


Here since we are calculating per day wise count for each song sudden spikes can be ignored when we are combining for a month.
