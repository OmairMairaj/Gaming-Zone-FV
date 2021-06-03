/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

/**
 *
 * @author OMA
 */
public class Customer {
    
    private int customer_id;
    private String customername;
    private String date;
    private int user_id;
    private int room_no;
    private int noOfPC;
    private String session;
    private String Time;
    private int card_no;
    private String expDate;
    private int amount;

    public Customer(int customer_id, String customername, String date, int user_id,
            int room_no, int noOfPC, String session, String Time,int cardNo,String expDate, int amount) {
        this.customer_id = customer_id;
        this.customername = customername;
        this.date = date;
        this.user_id = user_id;
        this.room_no = room_no;
        this.noOfPC = noOfPC;
        this.session = session;
        this.Time = Time;
        this.card_no = cardNo;
        this.expDate = expDate;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    
    public int getCard_no() {
        return card_no;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomername() {
        return customername;
    }

    public String getDate() {
        return date;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRoom_no() {
        return room_no;
    }

    public int getNoOfPC() {
        return noOfPC;
    }

    public String getSession() {
        return session;
    }

    public String getTime() {
        return Time;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", customername=" + customername + ", date=" + date + ", user_id=" + user_id + ", room_no=" + room_no + ", noOfPC=" + noOfPC + ", session=" + session + ", time=" + Time + ", card_no=" + card_no + ", expDate=" + expDate + ", amount=" + amount + '}';
    }

   
    
    
}
