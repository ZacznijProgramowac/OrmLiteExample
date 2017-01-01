package pl.ormlite.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by ZacznijProgramowac on 01.01.2017.
 */
@DatabaseTable(tableName = "books")
public class Book {

    public Book() {
    }

    @DatabaseField(columnName = "TITTLE")
    private String tittle;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "ISBN")
    private String isbn;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;

    @DatabaseField(columnName = "DATE_RELEASE")
    private Date dateRelease;

    @DatabaseField(columnName = "RATING")
    private int rating;

    @DatabaseField(columnName = "BORROWED")
    private boolean borrowed;

    @DatabaseField(columnName = "PRICE")
    private double price;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
