package BatchLayer.model;


import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private Client client;
    private Date Transaction_date;
    private String Product;
    private Integer Price;
    private String Payment_Type;
    private Integer Account_Created;
    private Date Last_Login;
    private String Description;
    private Geolocation geolocation;

    public Transaction() {
    }

    public Transaction(Client client, Date transaction_date, String product, Integer price, String payment_Type, Integer account_Created, Date last_Login, String description, Geolocation geolocation) {
        this.client = client;
        Transaction_date = transaction_date;
        Product = product;
        Price = price;
        Payment_Type = payment_Type;
        Account_Created = account_Created;
        Last_Login = last_Login;
        Description = description;
        this.geolocation = geolocation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTransaction_date() {
        return Transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        Transaction_date = transaction_date;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getPayment_Type() {
        return Payment_Type;
    }

    public void setPayment_Type(String payment_Type) {
        Payment_Type = payment_Type;
    }

    public Integer getAccount_Created() {
        return Account_Created;
    }

    public void setAccount_Created(Integer account_Created) {
        Account_Created = account_Created;
    }

    public Date getLast_Login() {
        return Last_Login;
    }

    public void setLast_Login(Date last_Login) {
        Last_Login = last_Login;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }
}
