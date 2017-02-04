package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

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


        Author author = DataCreator.author();
        //Pierwsza
        Book book = DataCreator.firstBook();

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
        Book book3 = DataCreator.thirdBook();

        author.getBooks().add(book3);
        daoAuthor.createOrUpdate(author);


    }
}
