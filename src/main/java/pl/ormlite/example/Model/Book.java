package pl.ormlite.example.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * https://www.facebook.com/zacznijprogramowac
 */
@DatabaseTable(tableName = "books")
public class Book {

    public Book() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "AUTHOR_ID", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Author author;

    @DatabaseField(columnName = "TITLE", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "DESCRIPTION", dataType = DataType.LONG_STRING)
    private String description;

    @DatabaseField(columnName = "ISBN", unique = true)
    private String isbn;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;

    @DatabaseField(columnName = "DATE_RELEASE", dataType = DataType.DATE_STRING, format = "yyyy-MM-DD")
    private Date dateRelease;

    @DatabaseField(columnName = "RATING", width = 1)
    private String rating;

    @DatabaseField(columnName = "BORROWED", defaultValue = "false")
    private boolean borrowed;

    @DatabaseField(columnName = "PRICE")
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "\nid=" + id +
                "\n author=" + author +
                "\n title='" + title + '\'' +
                "\n description='" + description + '\'' +
                "\n isbn='" + isbn + '\'' +
                "\n addedDate=" + addedDate +
                "\n dateRelease=" + dateRelease +
                "\n rating='" + rating + '\'' +
                "\n borrowed=" + borrowed +
                "\n price=" + price +
                '}';
    }
}
