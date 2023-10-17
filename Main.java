// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import database.Database;
import database.Word;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        database.setConnection();
        ArrayList<Word> words = new ArrayList<Word>();
        words = database.getAllTable();
        for (Word w : words) {
            System.out.println(w.getWord_target() + "\t\t\t|\t" + w.getWord_explain() + "\t\t\t|\t"
                                    + w.getType() + "\t\t\t|\t" + w.getIpa());
        }

//        String sql = "select * from av";
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            while(resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String wordtarget = resultSet.getString("word");
//                String wordexplain = resultSet.getString("description");
//                String ipa = resultSet.getString("pronounce");
//                System.out.println(id + "\t|\t" + wordtarget + "\t|" + wordexplain + "\t|\t" + ipa);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        connection.close();
    }
        //Dictionary dictionary = new Dictionary();
        //DictionaryCommanline.dictionaryBasic(dictionary);
}