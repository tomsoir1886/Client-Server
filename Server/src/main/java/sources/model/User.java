package sources.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.value.WritableLongValue;


public class User {

    @JsonProperty("Name")
    private String title;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Depositor")
    private String depositor;

    @JsonProperty("Account id")
    private long id;

    @JsonProperty("Amount on deposit")
    private int sum;

    @JsonProperty("Profitability")
    private double procent;

    @JsonProperty("Time constraints")
    private Date date;

    public User() {
    }


    public User(String title, String country, String type, String depositor, long id, int sum, double procent, Date date) {

        this.title = title;
        this.country = country;
        this.type = type;
        this.depositor = depositor;
        this.id = id;
        this.sum = sum;
        this.procent = procent;
        this.date = date;


    }

    public User(int startIndex, String... arrVar) {
        this.title = arrVar[startIndex++];
        this.country = arrVar[startIndex++];
        this.type = arrVar[startIndex++];
        this.depositor = arrVar[startIndex++];
        this.id = Long.valueOf(arrVar[startIndex++]);
        this.sum = Integer.valueOf(arrVar[startIndex++]);
        this.procent = Double.valueOf(arrVar[startIndex++]);
        this.date = new Date(Long.valueOf(arrVar[startIndex++]));
    }

    public User(int startIndex, String var) {
        System.out.println(var);
        String[] arrVar = var.split(" ");
        this.title = arrVar[startIndex++];
        this.country = arrVar[startIndex++];
        this.type = arrVar[startIndex++];
        this.depositor = arrVar[startIndex++];
        this.id = Long.valueOf(arrVar[startIndex++]);
        this.sum = Integer.valueOf(arrVar[startIndex++]);
        this.procent = Double.valueOf(arrVar[startIndex++]);
        this.date = new Date(Long.valueOf(arrVar[startIndex++]));
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\n User{" +
                "\n\t title='" + title + '\'' +
                ",\n\t country='" + country + '\'' +
                ",\n\t type='" + type + '\'' +
                ",\n\t depositor='" + depositor + '\'' +
                ",\n\t id=" + id +
                ",\n\t sum=" + sum +
                ",\n\t procent=" + procent +
                ",\n\t date=" + date +
                '}';
    }
}