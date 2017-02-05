package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ZacznijProgramowac.
 */
public class MainQuery {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";
        String databaseUrlH2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlH2);

        TableUtils.dropTable(connectionSource, Book.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);

        //Pierwsza
        Book book = DataCreator.firstBook();

        //Druga
        Book book2 = DataCreator.secondBook();

        //Wiedźmin
        Book book3 = DataCreator.thirdBook();

        Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);

        dao.create(book);
        dao.create(book2);
        dao.create(book3);


        QueryBuilder<Book, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("TITLE", "Hobbit");
        PreparedQuery<Book> prepare = queryBuilder.prepare();
        List<Book> result = dao.query(prepare);
        result.forEach(e->{
            System.out.println();
            System.out.println(e);
            System.out.println();
        });

        List<Book> result2 = dao.query(dao.queryBuilder().
                where().
                eq("TITLE", "Władca pierścieni").
                prepare());
        result2.forEach(e->{
            System.out.println();
            System.out.println(e);
            System.out.println();
        });

        List<Book> booksListWhereAnd = dao.query(dao.queryBuilder().where()
                                        .eq("TITLE", "Hobbit")
                                        .and()
                                        .eq("PRICE", 22.99).prepare());
        booksListWhereAnd.forEach(e -> {
            System.out.println();
            System.out.println(e.toString());
            System.out.println();
        });

        UpdateBuilder<Book, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.updateColumnValue("DESCRIPTION", "Przygody Hobbitów z pierścieniem");
        updateBuilder.where().isNull("DESCRIPTION");
        int booksUpdate = updateBuilder.update();
        System.out.println(booksUpdate);
        System.out.println();


        connectionSource.close();
    }
}
