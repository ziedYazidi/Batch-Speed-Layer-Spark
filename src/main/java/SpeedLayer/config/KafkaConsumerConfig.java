package SpeedLayer.config;

import SpeedLayer.serdes.TransactionDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerConfig {
    public static final String boostrapservers = "kafka:9092";
    public static final String acks = "1";
    public static final String topic = "transactions-topic";
    public static final String keyDeserializer= StringDeserializer.class.getName();
    public static final String valueDeserializer = TransactionDeserializer.class.getName();
}
