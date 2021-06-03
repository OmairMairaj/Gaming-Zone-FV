/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projdemo;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Room {
private int roomNo;
private int NoofPC;

    public Room(int roomNo, int NoofPC) {
        this.roomNo = roomNo;
        this.NoofPC = NoofPC;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getNoofPC() {
        return NoofPC;
    }


    @Override
    public String toString() {
        return "Room{" + "roomNo=" + roomNo + ", NoofPC=" + NoofPC + '}';
    }
    
    


}
