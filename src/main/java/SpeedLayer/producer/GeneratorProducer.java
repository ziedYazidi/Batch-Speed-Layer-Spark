package SpeedLayer.producer;

import BatchLayer.model.Transaction;
import SpeedLayer.config.KafkaProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class GeneratorProducer {

    private Properties props;
    private ProducerCallback producerCallback;
    public GeneratorProducer() {
        props = new Properties();
        producerCallback = new ProducerCallback();
    }

    public KafkaProducer<String, Transaction> getProducer(){
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProducerConfig.boostrapservers);
        props.put(ProducerConfig.ACKS_CONFIG, KafkaProducerConfig.acks);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaProducerConfig.keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProducerConfig.valueSerializer);

        return new KafkaProducer<>(props);
    }

    public void run(String key, Transaction value){
        ProducerRecord transactionProducerRecord= new ProducerRecord<String, Transaction>(KafkaProducerConfig.topic, key, value);
        Producer<String, Transaction> producer = this.getProducer();
        producer.send(transactionProducerRecord);

    }

}
