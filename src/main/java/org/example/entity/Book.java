package org.example.entity;

import jakarta.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int pubYear;
    private double price;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
    }
    public Book(int id, String title, int pubYear, double price, Author author) {
        this.id = id;
        this.title = title;
        this.pubYear = pubYear;
        this.price = price;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubYear=" + pubYear +
                ", price=" + price +
                ", author=" + author +
                '}';
    }
}
