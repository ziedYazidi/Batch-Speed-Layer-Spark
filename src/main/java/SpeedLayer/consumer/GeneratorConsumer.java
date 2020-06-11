package SpeedLayer.consumer;

import BatchLayer.model.Transaction;
import SpeedLayer.config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.util.*;

public class GeneratorConsumer {
    Map<String, Object> kafkaParams;
    Collection<String> topics;


    public GeneratorConsumer() {
        kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", KafkaConsumerConfig.boostrapservers);
        kafkaParams.put("key.deserializer", KafkaConsumerConfig.keyDeserializer);
        kafkaParams.put("value.deserializer", KafkaConsumerConfig.valueDeserializer);
        kafkaParams.put("group.id", "1");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        topics = Arrays.asList(KafkaConsumerConfig.topic);

    }

    public JavaPairDStream<String, Transaction>  consumeStream(JavaStreamingContext javaStreamingContext){
        JavaInputDStream<ConsumerRecord<String, Transaction>> stream =
                KafkaUtils.createDirectStream(
                        javaStreamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, Transaction>Subscribe(topics, kafkaParams)
                );


        JavaPairDStream<String, Transaction> pairedStream= stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
        return pairedStream;
    }
}
