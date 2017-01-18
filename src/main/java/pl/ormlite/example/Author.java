package pl.ormlite.example;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ZacznijProgramowac.
 */
@DatabaseTable
public class Author {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Book> books;
}
