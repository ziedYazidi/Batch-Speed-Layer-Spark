package SpeedLayer.generator;

import BatchLayer.model.Client;
import BatchLayer.model.Geolocation;
import BatchLayer.model.Transaction;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import scala.util.Random;


public class TransactionGeneratorRandom {
    public Transaction generateRandomTransactionRecord() {
        DateTime initialDate = new DateTime(2009, 1, 1, 0, 0);
        DateTime generatedDate = initialDate.plusMinutes(new Random().nextInt(1000000));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("M/d/yy h:mm");
        Transaction transactionRecord = new Transaction(
                new Client((long)new Random().nextInt(), getRandomName()),
                generatedDate.toDate(),
                Integer.toString(new Random().nextInt(5)),
                new Random().nextInt(6000),
                getRandomPaymentType(),
                new Random().nextInt(30),
                generatedDate.toDate(),
                getRandomDescription(),
                new Geolocation(getRandomLatitude(), getRandomLongitude(), getRandomCity()));

        return transactionRecord;
    }

    private String getRandomDescription() {
        Integer randomInt = new Random().nextInt(9);

        switch (randomInt) {
            case 0 :
                return "Shopping Mall";
            case 1 :
                return "Restaurant";
            case 2 :
                return "Cinema";
            case 3 :
                return "Sports";
            case 4 :
                return "car insurace";
            case 5 :
                return "home insurance";
            case 6 :
                return "life insurance";
            case 7 :
                return "rent";
            case 8 :
                return "leasing";
            default:
                return "other";
        }
    }

    private Double getRandomLongitude() {
        return (new Random().nextDouble()*361 - 180);
    }

    private Double getRandomLatitude() {
        return (new Random().nextDouble()*171 - 85);
    }

    private String getRandomCity() {
        Integer randomInt = new Random().nextInt(9);

        switch (randomInt) {
            case 0 :
                return "London";
            case 1 :
                return "Astoria";
            case 2 :
                return "Ottawa";
            case 3 :
                return "Eagle";
            case 4 :
                return "New York";
            case 5 :
                return "Eindhoven";
            case 6 :
                return "Manchester";
            case 7 :
                return "Madrid";
            case 8 :
                return "Barcelona";
            default:
                return new Random().alphanumeric().take(randomInt).mkString().replace(",", "");
        }
    }

    private String getRandomName() {
        Integer randomInt = new Random().nextInt(10);

        switch (randomInt) {
            case 0 :
                return "Carolina";
            case 1 :
                return "Betina";
            case 2 :
                return "Federica";
            case 3 :
                return "Gouya";
            case 4 :
                return "Fleur";
            case 5 :
                return "Adam";
            case 6 :
                return "Leanne";
            case 7 :
                return "Georgia";
            case 8 :
                return "Richard";
            default:
                return new Random().alphanumeric().take(randomInt).mkString().replace(",", "");
        }
    }

    private String getRandomPaymentType() {
        Integer randomInt = new Random().nextInt(3);

        switch(randomInt){
            case 0 :
                return "Mastercard";
            case 1 :
                return "Visa";
            case 2 :
                return "Amex";
            case 3 :
                return "Dinners";
            default:
                return "Euro6000";
        }
    }


}
