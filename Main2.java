package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        //database.setConnection();
        ArrayList<Word> words = new ArrayList<Word>();
        words = database.getAllTable();
        MatchGameUi matchGameUi = new MatchGameUi(words);
        matchGameUi.play();
    }
}
