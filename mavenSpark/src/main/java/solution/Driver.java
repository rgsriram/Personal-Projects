package solution;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.google.gson.Gson;

import scala.Tuple2;


public class Driver {
	
	transient static Gson gson;
	static String KAFKA_BROKER = "52.55.237.11";
	static String KAFKA_BROKER_PORT = "9092";
	
	/*
	 * Convert string into json.
	 */
	private static Gson getGson() {
	    if (gson == null) {
	        gson = new Gson();
	    }
	    return gson;
	}

	public static void main(String[] args) throws InterruptedException {
		SparkConf conf = new SparkConf().setAppName("stockAnalysis").setMaster("local[*]");
		System.out.println("Test");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext jssc = new JavaStreamingContext(sc, new Duration(1));

        // Setting up kafka parameters
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", KAFKA_BROKER + ':' + KAFKA_BROKER_PORT);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "stockgroup1");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Arrays.asList("stockData");
        
        final JavaInputDStream<ConsumerRecord<String, String>> stream =
        		  KafkaUtils.createDirectStream(
        		    jssc,
        		    LocationStrategies.PreferConsistent(),
        		    ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
        		  );
		
		JavaPairDStream<Object, Object> outStream = 
			    stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
		
		// Convert into string into stockinformation object
		JavaDStream<StockInformation> stockInformation = outStream.
				map(tuple2 -> getGson().fromJson((String) tuple2._2(), StockInformation.class
		));
		
		// Calculate the simple moving average closing price of the four stocks in a 5-minute sliding window for the last 10 minutes
		JavaDStream<StockInformation> rdd = stockInformation.window(new Duration(600), new Duration(300));
		int size = 0;
		rdd.foreachRDD(st -> size++);
		double sum = rdd.reduce((a,b) -> a.priceData.open + b.priceData.open);
		double avgOpeningPrice = sum/size;
		
		JavaDStream<StockInformation> rdd = stockInformation.window(new Duration(600), new Duration(300));
		int size = 0;
		rdd.foreachRDD(st -> size++);
		double sum = rdd.reduce((a,b) -> a.priceData.close + b.priceData.close);
		double avgClosingPrice = sum/size;
		System.out.println(avgClosingPrice);
	
		// Find the stock out of the four stocks giving maximum profit (average closing price - average opening price)
		System.out.println(avgClosingPrice - avgOpeningPrice);
		
        jssc.start();
        jssc.awaitTermination();
        
	}

}
