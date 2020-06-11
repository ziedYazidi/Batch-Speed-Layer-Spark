package SpeedLayer.generator;

import BatchLayer.model.Transaction;
import SpeedLayer.producer.GeneratorProducer;
import org.joda.time.DateTime;

public class GeneratorApp {
    public static void main(String[] args) throws InterruptedException {
        GeneratorProducer producer = new GeneratorProducer();

        for (int i = 0; i < 50; i++) {
            String timestamp = Long.toString(DateTime.now().getMillis());
            Thread.sleep(1000);
            Transaction transaction = new TransactionGeneratorRandom().generateRandomTransactionRecord();
            producer.run(timestamp, transaction);
        }
    }
}
