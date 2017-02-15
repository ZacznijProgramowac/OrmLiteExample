package pl.ormlite.example;

import pl.ormlite.example.Dao.AuthorDao;
import pl.ormlite.example.Dao.BookDao;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * https://www.facebook.com/zacznijprogramowac
 */
public class MainDbManager {

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author author = DataCreator.author();
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        authorDao.creatOrUpdate(author);
        List<Author> lista = authorDao.queryForAll(Author.class);
        lista.forEach(e->{
            System.out.println(e);
        });

        Book book = DataCreator.firstBook();
        BookDao bookDao = new BookDao(DbManager.getConnectionSource());
        bookDao.creatOrUpdate(book);
        bookDao.queryWhere("TITLE", "Władca pierścieni");

        DbManager.closeConnectionSource();

    }
}
