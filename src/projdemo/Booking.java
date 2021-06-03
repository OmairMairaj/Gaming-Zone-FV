/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

/**
 *
 * @author Owner
 */
public class Booking {

private int bookingNo;
private int userID;
private String userName;
private String booking_date;
private int room_no;
private int noOfPC;
private String session;
private String Time;

    public Booking(int bookingNo, int userID, String userName, String booking_date, int room_no, int noOfPC, String session, String time) {
        this.bookingNo = bookingNo;
        this.userID = userID;
        this.userName = userName;
        this.booking_date = booking_date;
        this.room_no = room_no;
        this.noOfPC = noOfPC;
        this.session = session;
        this.Time = time;
    }

//        return "Booking{" + "bookingNo=" + bookingNo + ", userID=" + userID + ", userName=" + userName + ", booking_date=" + booking_date + ", room_no=" + room_no + ", noOfPC=" + noOfPC + ", session=" + session + ", game=" + game + '}';
//    }
//    
    

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingNo=" + bookingNo + ", userID=" + userID + ", userName=" + userName + ", booking_date=" + booking_date + ", room_no=" + room_no + ", noOfPC=" + noOfPC + ", session=" + session + ", Time=" + Time + '}';
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public int getUserID() {
        return userID;
    }

    public String getBooking_date() {
        return booking_date;
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

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public void setNoOfPC(int noOfPC) {
        this.noOfPC = noOfPC;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setTime(String game) {
        this.Time = game;
    }


}
