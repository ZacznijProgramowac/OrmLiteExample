package pl.ormlite.example.Dao;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example.Model.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * https://www.facebook.com/zacznijprogramowac
 */
public class BookDao extends CommonDao {

    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<String[]> queryRaw(String value) throws SQLException {
        GenericRawResults<String[]> where = getDao(Book.class).queryRaw("SELECT * FROM books WHERE title = 'Hobbit'");
        return where.getResults();
    }

    public List<Book> queryWhere(String columnName, String value) throws SQLException {
        QueryBuilder<Book, Integer> queryBuilder = getQueryBuilder(Book.class);
        queryBuilder.where().eq(columnName, value);
        PreparedQuery<Book> prepare = queryBuilder.prepare();
        return getDao(Book.class).query(prepare);
        }


}
