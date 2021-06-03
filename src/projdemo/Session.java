/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

import java.util.Date;

/**
 *
 * @author Owner
 */
public class Session {
    private int Room_No;
    private int PC_No;
    private String date;
    private String Time;

    public Session(int Room_No, int PC_No, String date, String Time) {
        this.Room_No = Room_No;
        this.PC_No = PC_No;
        this.date = date;
        this.Time = Time;
    }

    public int getRoom_No() {
        return Room_No;
    }

    public int getPC_No() {
        return PC_No;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return Time;
    }
    @Override
    public String toString() {
        return "Session{" + "Room_No=" + Room_No + ", PC_No=" + PC_No + ", date=" + date + ", Time=" + Time + '}';
    }
    
    
    
    
    
}
