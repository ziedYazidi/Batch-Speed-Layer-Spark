package SpeedLayer.serdes;

import BatchLayer.model.Transaction;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class TransactionSerializer implements Serializer<Transaction> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Transaction transaction) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(transaction);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e){
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public void close() {

    }
}
