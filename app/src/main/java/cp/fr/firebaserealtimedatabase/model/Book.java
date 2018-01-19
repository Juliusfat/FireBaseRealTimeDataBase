package cp.fr.firebaserealtimedatabase.model;

/**
 * Created by Formation on 19/01/2018.
 */

public class Book {

    private String title;
    private double price;
    private Auteur author;

    public Book() {
    }

    public Book(String title, double price, Auteur author) {

        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Auteur getAuthor() {
        return author;
    }

    public void setAuthor(Auteur author) {
        this.author = author;
    }
}
