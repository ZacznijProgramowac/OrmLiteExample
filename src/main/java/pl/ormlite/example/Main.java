package pl.ormlite.example;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by ZacznijProgramowac on 20.12.2016.
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";
        //String databaseUrlH2 = "jdbc:h2:./database";

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);


        connectionSource.close();
    }
}
