package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        //Druga
        Book book2 = new Book();
        book2.setTitle("Hobbit");
        book2.setDescription("Niesamowite przygody małego hobbita");
        book2.setIsbn("22222");
        book2.setAddedDate(new Date());

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString2 = "2012/11/11";
        Date date2 = sdf2.parse(dateInString2);

        book2.setDateRelease(date2);
        book2.setRating("1");
        book2.setBorrowed(true);
        book2.setPrice(22.99);

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
