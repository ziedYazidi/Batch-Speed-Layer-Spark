package SpeedLayer;

import BatchLayer.model.Transaction;
import SpeedLayer.consumer.GeneratorConsumer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

public class SparkRunner {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setAppName("Streaming App").setMaster("local[*]");
        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();

        JavaSparkContext javaSparkContext = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(javaSparkContext, Durations.seconds(1));

//        Consumer
        GeneratorConsumer generatorConsumer= new GeneratorConsumer();
        JavaPairDStream<String, Transaction> stream = generatorConsumer.consumeStream(javaStreamingContext);
        stream.map(tuple -> {
            return new Tuple2(tuple._1, tuple._2().getClient().getName());
        }).print();

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();

    }
}
