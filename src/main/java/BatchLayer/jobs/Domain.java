package BatchLayer.jobs;

import BatchLayer.model.Client;
import BatchLayer.model.Geolocation;
import BatchLayer.model.Transaction;
import BatchLayer.utils.Utils;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.util.LongAccumulator;
import java.io.Serializable;

import static org.apache.spark.sql.functions.desc;


public class Domain implements Serializable {

    public Dataset<Transaction> transformToDataSet(SparkSession sparkSession, Dataset<Row> rowDataset){
        LongAccumulator longAccumulator = JavaSparkContext.fromSparkContext(sparkSession.sparkContext()).sc().longAccumulator();
        Encoder<Transaction> transactionEncoder = Encoders.bean(Transaction.class);

        Dataset<Transaction> transactionDataset = rowDataset.map((MapFunction<Row, Transaction>) row -> {
            longAccumulator.add(1);
            return new Transaction(
                    new Client(longAccumulator.value(), row.getAs("Name").toString().trim()),
                    Utils.convertDate(row.getAs("Transaction_date").toString()),
                    row.getAs("Product").toString().trim(),
                    Integer.parseInt(row.getAs("Price")),
                    row.getAs("Payment_Type").toString().trim(),
                    Integer.parseInt(row.getAs("Account_Created")),
                    Utils.convertDate(row.getAs("Last_Login").toString()),
                    row.getAs("Description").toString().trim(),
                    new Geolocation(Double.parseDouble(row.getAs("Latitude")),
                            Double.parseDouble(row.getAs("Longitude").toString()),
                            row.getAs("City").toString().trim())
            );
        }, transactionEncoder);

        return transactionDataset;
    }


    public Dataset<Row> getTransactionsPerCity(SparkSession sparkSession, Dataset<Transaction> transactionDataset) {
//        Methode 1
        Dataset<Row> res = transactionDataset.groupBy("geolocation.city").count().sort(desc("count"));
        return res.withColumnRenamed("count", "transactionsCount");

//        Methode 2
//        transactionDataset.createOrReplaceTempView("Transactions");
//        return sparkSession.sql("select geolocation.city, count(*) as nbrTransactions from Transactions group by geolocation.city order by nbrTransactions DESC");
    }

    public Dataset<Row> getClientsWithAmountSuperior500(Dataset<Transaction> transactionDataset){
        return transactionDataset.select("client.id", "client.name", "price").where("price > 500");
    }

    public Dataset<Row> getClientsFromLondon(Dataset<Transaction> transactionDataset){
        return transactionDataset
                .where("geolocation.city = \"London\"")
                .groupBy("client.name")
                .count()
                .withColumnRenamed("count", "nbrTransactions");
    }


//    Get location from longitude and latitiude
    public Dataset<Row> getLocationFromLongLat(SparkSession sparkSession, Dataset<Transaction> transactionDataset) {
        sparkSession.sqlContext()
                .udf()
                .register( "Location UDF",
                        (Double longitude, Double latitude) -> Utils.getLocation(longitude.toString(), latitude.toString())
                        , DataTypes.StringType);
        return transactionDataset
                .withColumn("location", functions.callUDF("Location UDF", transactionDataset.col("geolocation.Longitude"), transactionDataset.col("geolocation.Latitude")));
    }

}
