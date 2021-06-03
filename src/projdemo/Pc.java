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
public class Pc {
    private int pcNo;
    private String model;
    private String isOccupied;
    private Room room;

    public Pc(int pcNo, String model, String isOccupied, Room room) {
        this.pcNo = pcNo;
        this.model = model;
        this.isOccupied = isOccupied;
        this.room = room;
    }

    public int getPcNo() {
        return pcNo;
    }

    public String getModel() {
        return model;
    }

    public String isIsOccupied() {
        return isOccupied;
    }

    public Room getRoom() {
        return room;
    }    

    @Override
    public String toString() {
        return "Pc{" + "pcNo=" + pcNo + ", model=" + model + ", isOccupied=" + isOccupied + ", room=" + room + '}';
    }
    
    
}
