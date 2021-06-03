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
public class Game {

    private String name;
    private int rating;
    private String publisher;

    public Game(String name, int rating, String publisher) {
        this.name = name;
        this.rating = rating;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Game{" + "name=" + name + ", rating=" + rating + ", publisher=" + publisher + '}';
    }
    
}
