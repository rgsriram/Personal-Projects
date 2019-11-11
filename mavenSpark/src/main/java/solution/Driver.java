package solution;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
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

	// Harding kafka broker port.
	static String KAFKA_BROKER_PORT = "9092";

	// Based on this param cores in spark will be set.
	static boolean ENV_DEV = false;

	// Spark App Name
	static String SPARK_APP_NAME = "stockAnalysis";

	/*
	 * Convert string into json.
	 */
	private static Gson getGson() {
	    if (gson == null) {
	        gson = new Gson();
	    }
	    return gson;
	}

	public static void simpleMovingAvg(JavaInputDStream<ConsumerRecord<String, String>> dstream) {
		JavaPairDStream<String, AverageTuple> pair = dstream.flatMapToPair(
				new PairFlatMapFunction<String, String, AverageTuple>() {

					public Iterator<Tuple2<String, AverageTuple>> call(String t)
							throws Exception {
						List<Tuple2<String, AverageTuple>> list = new ArrayList<Tuple2<String, AverageTuple>>();
						JSONArray js1 = new JSONArray(t);
						for (int i = 0; i < js1.length(); i++) {
							String symbol = js1.getJSONObject(i).get("symbol")
									.toString();
							JSONObject jo = new JSONObject(js1.getJSONObject(i)
									.get("priceData").toString());
							list.add(new Tuple2<String, AverageTuple>(symbol,
									new AverageTuple(1,
											jo.getDouble("close"))));
						}
						return list.iterator();
					}
				});
		JavaPairDStream<String, AverageTuple> result=pair.reduceByKeyAndWindow(
				new Function2<AverageTuple, AverageTuple, AverageTuple>() {
					public AverageTuple call(AverageTuple result, AverageTuple value)
							throws Exception {
						result.setAverage(
								result.getAverage() + value.getAverage());
						result.setCount(result.getCount() + value.getCount());
						return result;
					}
				}, new Duration(600000), new Duration(300000));

		result.foreachRDD(new VoidFunction<JavaPairRDD<String,AverageTuple>>() {
			public void call(JavaPairRDD<String, AverageTuple> t)
					throws Exception {
				t.coalesce(1).print();
			}
		});

	}

	// Calculate the simple moving average closing price of the four stocks in a 5-minute sliding window for the last 10 minutes
	public static void maxProfit(JavaInputDStream<ConsumerRecord<String, String>> dstream) {
		JavaPairDStream<String, MaximumTuple> pair = dstream.flatMapToPair(
				new PairFlatMapFunction<String, String, MaximumTuple>() {
					public Iterator<Tuple2<String, MaximumTuple>> call(String t)
							throws Exception {

						List<Tuple2<String, MaximumTuple>> list = new ArrayList<Tuple2<String, MaximumTuple>>();
						JSONArray js1 = new JSONArray(t);

						for (int i = 0; i < js1.length(); i++) {
							String symbol = js1.getJSONObject(i).get("symbol")
									.toString();

							JSONObject jo = new JSONObject(js1.getJSONObject(i)
									.get("priceData").toString());

							list.add(new Tuple2<String, MaximumTuple>(symbol,
									new MaximumTuple(1,
											jo.getDouble("close"),jo.getDouble("open"))));

						}
						return list.iterator();
					}
				});

		JavaPairDStream<String, MaximumTuple> result=pair.reduceByKeyAndWindow(
				new Function2<MaximumTuple, MaximumTuple, MaximumTuple>() {
					public MaximumTuple call(MaximumTuple result, MaximumTuple value)
							throws Exception {
						result.setClosingPrice(
								result.getClosingPrice() + value.getClosingPrice());
						result.setOpeningPrice(result.getOpeningPrice()+value.getOpeningPrice());
						result.setCount(result.getCount() + value.getCount());
						return result;
					}
				}, new Duration(600000), new Duration(300000));

		result.foreachRDD(new VoidFunction<JavaPairRDD<String,MaximumTuple>>() {
			public void call(JavaPairRDD<String, MaximumTuple> t)
					throws Exception {
				t.coalesce(1).print();
			}
		});
	}

	public static void buyMaxStock(JavaInputDStream<ConsumerRecord<String, String>> dstream) {
		JavaPairDStream<String, StockTuple> pair = dstream.flatMapToPair(
				new PairFlatMapFunction<String, String, StockTuple>() {

					public Iterator<Tuple2<String, StockTuple>> call(String t)
							throws Exception {
						List<Tuple2<String, StockTuple>> list = new ArrayList<Tuple2<String, StockTuple>>();
						JSONArray js1 = new JSONArray(t);
						for (int i = 0; i < js1.length(); i++) {
							String symbol = js1.getJSONObject(i).get("symbol")
									.toString();
							JSONObject jo = new JSONObject(js1.getJSONObject(i)
									.get("priceData").toString());
							list.add(new Tuple2<String, StockTuple>(symbol,
									new StockTuple(1,
											jo.getDouble("volume"))));
						}
						return list.iterator();
					}
				});

		JavaPairDStream<String, StockTuple> result=pair.reduceByKeyAndWindow(
				new Function2<StockTuple, StockTuple, StockTuple>() {
					public StockTuple call(StockTuple result, StockTuple value)
							throws Exception {
						if (result.getVolume() < value.getVolume())
							result.setVolume(value.getVolume());

						result.setCount(result.getCount() + value.getCount());
						return result;
					}
				}, new Duration(600000), new Duration(300000));

		result.foreachRDD(new VoidFunction<JavaPairRDD<String,StockTuple>>() {
			public void call(JavaPairRDD<String, StockTuple> t)
					throws Exception {

				t.coalesce(1).print();

			}
		});

	}

	public static void main(String[] args) throws InterruptedException {

		if (args.length <= 2) {
			System.out.println("Not enough args passed");
			System.exit(0);
		}

		String KAFKA_BROKER = args[0];
		String KAFKA_TOPIC_NAME = args[1];
		String KAFKA_GROUP_ID = args[2];

		if (ENV_DEV)
			SparkConf conf = new SparkConf().setAppName(SPARK_APP_NAME).setMaster("local[*]");
		else
			SparkConf conf = new SparkConf().setAppName(SPARK_APP_NAME);

		
		JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext jssc = new JavaStreamingContext(sc, new Duration(1));

        // Setting up kafka parameters
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", KAFKA_BROKER + ":" + KAFKA_BROKER_PORT);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", KAFKA_GROUP_ID);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Arrays.asList(KAFKA_TOPIC_NAME);
        
        final JavaInputDStream<ConsumerRecord<String, String>> dstream =
        		  KafkaUtils.createDirectStream(
        		    jssc,
        		    LocationStrategies.PreferConsistent(),
        		    ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
        		  );
		
//		JavaPairDStream<Object, Object> outStream =
//			    stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
//
//		// Convert into string into stockinformation object
//		JavaDStream<StockInformation> stockInformation = outStream.
//				map(tuple2 -> getGson().fromJson((String) tuple2._2(), StockInformation.class
//		));
//
//		// Calculate the simple moving average closing price of the four stocks in a 5-minute sliding window for the last 10 minutes
//		JavaDStream<StockInformation> rdd = stockInformation.window(new Duration(600000), new Duration(300000));
//		double rddCount = (double) rdd.count();
//		double rddSum = rdd.reduce((a,b) -> a.priceData.open + b.priceData.open);
//		double avgOpeningPrice = rddSum/rddCount;
//
//		JavaDStream<StockInformation> rdd2 = stockInformation.window(new Duration(600000), new Duration(300000));
//		double rdd2Count = (double) rdd.count();
//		double rdd2sum = rdd.reduce((a,b) -> a.priceData.close + b.priceData.close);
//		double avgClosingPrice = rdd2sum/rdd2Count;
//		System.out.println(avgClosingPrice);
//
//		// Find the stock out of the four stocks giving maximum profit (average closing price - average opening price)
//		System.out.println(avgClosingPrice - avgOpeningPrice);
//
//		// Pick stock from 4 stocks
//		JavaDStream<StockInformation> rdd = stockInformation.window()


		// Calculate the simple moving average closing price of the four stocks in a 5-minute sliding window for the last 10 minutes
		simpleMovingAvg(dstream);

		// Calculate the simple moving average closing price of the four stocks in a 5-minute sliding window for the last 10 minutes
		maxProfit(dstream);

		// Calculate the trading volume(total traded volume) of the four stocks every 10 minutes and decide which stock to purchase out of the four stocks.
		buyMaxStock(dstream);

		jssc.start();
		jssc.awaitTermination();
	}
}

