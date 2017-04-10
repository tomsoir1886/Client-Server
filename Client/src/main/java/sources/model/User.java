package sources.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {


    private String title;


    private String country;

    private String type;


    private String depositor;


    private long id;


    private int sum;


    private double procent;

    private Date date;


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

    public User( int startIndex, String ... arrVar){
        this.title = arrVar[startIndex++];
        this.country = arrVar[startIndex++];
        this.type = arrVar[startIndex++];
        this.depositor = arrVar[startIndex++];
        this.id = Long.valueOf(arrVar[startIndex++]);
        this.sum =  Integer.valueOf(arrVar[startIndex++]);
        this.procent =  Double.valueOf(arrVar[startIndex++]);
        try {
            this.date =  new SimpleDateFormat("yyyy-MM-dd").parse(arrVar[startIndex++]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User( int startIndex, String var){
        String[] arrVar = var.split(" ");
        this.title = arrVar[startIndex++];
        this.country = arrVar[startIndex++];
        this.type = arrVar[startIndex++];
        this.depositor = arrVar[startIndex++];
        this.id = Long.valueOf(arrVar[startIndex++]);
        this.sum =  Integer.valueOf(arrVar[startIndex++]);
        this.procent =  Double.valueOf(arrVar[startIndex++]);
        try {
            this.date =  new SimpleDateFormat("yyyy-MM-dd").parse(arrVar[startIndex++]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        return
                  title  +
                        " " + country +
                        " " + type +
                        " " + depositor +
                        " " + id +
                        " " + sum +
                        " " + procent +
                        " " + date.getTime();

    }
}