package pl.ormlite.example;

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

    }
}
