package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZacznijProgramowac.
 */
public class MainForeignFields {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";
        String databaseUrlH2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        TableUtils.dropTable(connectionSource, Book.class, true);
        TableUtils.dropTable(connectionSource, Author.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTableIfNotExists(connectionSource, Author.class);

        Dao<Book, Integer> daoBook = DaoManager.createDao(connectionSource, Book.class);
        Dao<Author, Integer> daoAuthor = DaoManager.createDao(connectionSource, Author.class);


        Author author = new Author();
        author.setName("Tolkien");

        //Pierwsza
        Book book = new Book();
        book.setTitle("Władca pierścieni");
        book.setDescription(null);
        book.setIsbn("11111");
        book.setAddedDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = "2012/11/11";
        Date date = sdf.parse(dateInString);
        book.setDateRelease(date);
        book.setRating("1");
        book.setBorrowed(true);
        book.setPrice(67.99);

        book.setAuthor(author);
        daoBook.create(book);
        System.out.println("Po zapisie do bazy danyc: " + book);

        Book book2 = daoBook.queryForId(1);
        System.out.println("Po zapytaniu do bazy danyc: " + book2);

        book2.getAuthor().setName("Nieznany");
        daoAuthor.createOrUpdate(book2.getAuthor());
        book2 = daoBook.queryForId(1);
        System.out.println("Po zmianie autora: " + book2);


        author = daoAuthor.queryForId(1);
        author.getBooks().forEach(e->{
            e.setTitle("Blablabla");
            try {
                daoBook.createOrUpdate(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        daoAuthor.refresh(author);
        author.getBooks().forEach(e->{
            System.out.println("Zmiana tytułu: " + e.getTitle());
        });

        //Wiedźmin
        Book book3 = new Book();
        book3.setTitle("Krew elfów");
        book3.setDescription("Tutaj opis jest zbędny...");
        book3.setIsbn("33333");
        book3.setAddedDate(new Date());

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString3 = "2012/11/11";
        Date date3 = sdf3.parse(dateInString3);

        book3.setDateRelease(date3);
        book3.setRating("1");
        book3.setBorrowed(true);
        book3.setPrice(33.99);

        author.getBooks().add(book3);
        daoAuthor.createOrUpdate(author);


    }
}
