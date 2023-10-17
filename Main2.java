package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        database.setConnection();
        ArrayList<Word> words = new ArrayList<Word>();
        int i = 0;
        for (Word w : words = database.getAllTable()) {
            i++;
            System.out.println(i + " " + w.getWord_target() + "\t\t\t|\t" + w.getWord_explain() + "\t\t\t|\t"
                    + w.getType() + "\t\t\t|\t" + w.getIpa());
        }
    }
}
