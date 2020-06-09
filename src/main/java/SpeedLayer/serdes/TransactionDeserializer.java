package SpeedLayer.serdes;

import BatchLayer.model.Transaction;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.*;
import java.util.Map;

public class TransactionDeserializer implements Deserializer<Transaction> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Transaction deserialize(String s, byte[] bytes) {
        Object object = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            object = ois.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return (Transaction)object;

    }

    @Override
    public void close() {

    }
}
