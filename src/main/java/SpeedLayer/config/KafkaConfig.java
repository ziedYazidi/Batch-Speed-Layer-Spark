package SpeedLayer.config;

import SpeedLayer.serdes.TransactionSerializer;
import org.codehaus.jackson.map.ser.std.StringSerializer;

public class KafkaConfig {
    public static final String boostrapservers = "kafka:9092";
    public static final String acks = "1";
    public static final String topic = "transactions-topic";
    public static final String keySerializer= StringSerializer.class.getName();
    public static final String valueSerializer = TransactionSerializer.class.getName();
}
