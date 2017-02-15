package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * https://www.facebook.com/zacznijprogramowac
 */
public class MainForeignFields {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        //Możemy użyc dowonej bazy danych
        String databaseUrl = "jdbc:sqlite:database.db";
        String databaseUrlH2 = "jdbc:h2:./database";

        //Potrzebne połączenie do bazy
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        //Usuwamy i tworzymy tabele
        TableUtils.dropTable(connectionSource, Book.class, true);
        TableUtils.dropTable(connectionSource, Author.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTableIfNotExists(connectionSource, Author.class);

        //Dao menadżery do operacji na bazie
        Dao<Book, Integer> daoBook = DaoManager.createDao(connectionSource, Book.class);
        Dao<Author, Integer> daoAuthor = DaoManager.createDao(connectionSource, Author.class);


        Author author = DataCreator.author();
        Book book = DataCreator.firstBook();

        // Nie musimy zapisywać autora bo mamy ustawiony foreignAutoCreate = true
        book.setAuthor(author);
        daoBook.create(book);
        System.out.println("Po zapisie do bazy danyc: " + book);

        Book book2 = daoBook.queryForId(1);
        System.out.println("Po zapytaniu do bazy danyc: " + book2);

        book2.getAuthor().setName("Nieznany");
        //Jeżeli zmieniamy coś w autorze to trzeba go zapisać, sam updte book nie starczy
        daoAuthor.createOrUpdate(book2.getAuthor());
        book2 = daoBook.queryForId(1);
        System.out.println("Po zmianie autora: " + book2);


        author = daoAuthor.queryForId(1);
        //Jeżeli zmieniamy coś w kolekcji, dany obiekt musi zostać uaktualniony w bazie
        author.getBooks().forEach(e->{
            e.setTitle("Blablabla");
            try {
                daoBook.createOrUpdate(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        author.getBooks().forEach(e-> System.out.println("Zmiana tytułu: " + e.getTitle()));

        //Takie dodanie książki przez autora nie zadziała
        Book book3 = DataCreator.thirdBook();
        author.getBooks().add(book3);
        daoAuthor.createOrUpdate(author);
        author.getBooks().forEach(e-> System.out.println(e));

    }
}
