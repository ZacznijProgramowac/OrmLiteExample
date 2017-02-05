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

/**
 * Created by ZacznijProgramowac.
 */
public class MainPostgresql {

    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:postgresql://localhost:5432/ormlite";
        ConnectionSource connectionSource = new JdbcConnectionSource(url, "postgres", "root");

        TableUtils.dropTable(connectionSource, Book.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);

        TableUtils.dropTable(connectionSource, Author.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Author.class);

        Dao<Book, Integer> daoBook = DaoManager.createDao(connectionSource, Book.class);

        Author author = DataCreator.author();
        Book book = DataCreator.firstBook();
        book.setAuthor(author);

        daoBook.createOrUpdate(book);

        connectionSource.close();


    }
}
