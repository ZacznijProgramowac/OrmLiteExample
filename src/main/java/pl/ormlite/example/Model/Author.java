package pl.ormlite.example.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * https://www.facebook.com/zacznijprogramowac
 */
@DatabaseTable
public class Author implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "\nid=" + id +
                "\n name='" + name + '\'' +
                "\n books=" + books +
                '}';
    }
}
