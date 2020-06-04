package BatchLayer;

import BatchLayer.jobs.Domain;
import BatchLayer.model.Transaction;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;



public class SparkRunner {
    public static void main(String[] args){
        SparkConf sparkConf = new SparkConf().setAppName("batch layer").setMaster("local[*]");
        Domain domain = new Domain();
        SparkSession sparkSession = SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate();

        Dataset<Row> rowDataset = sparkSession.read().option("header", "true").csv("./src/main/resources/transactions.csv");

//        Create DataSet of Transactions
        Dataset<Transaction> transactionDataset = domain.transformToDataSet(sparkSession, rowDataset);
//        get transactions per city
//        domain.getTransactionsPerCity(sparkSession, transactionDataset).show();
//        get clients with price > 500
//        domain.getClientsWithAmountSuperior500(transactionDataset).show();
//        get Clients from London
//        domain.getClientsFromLondon(transactionDataset).show();

//        Get Real location from longitude and latitude
        domain.getLocationFromLongLat(sparkSession, transactionDataset).show();
    }

}
