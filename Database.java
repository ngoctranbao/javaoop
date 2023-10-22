package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String URL = "jdbc:sqlite:src\\main\\resources\\dict_hh.db";
    private Connection connection;
    protected ArrayList<Word> dictionary = new ArrayList<>();

    public Database() {
        try {
            connection = DriverManager.getConnection(URL);
            getAllTable();
            if (dictionary.isEmpty()) {
                System.out.println("can't get database");
            }
        } catch (SQLException e) {
            System.out.println("error constructor");
        }
    }
    /**
     * Disconnect with MySQL.
     *
     * @param connection
     */
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot execute closing connection.");
            e.printStackTrace();
        }
    }

    /**
     * close preparedstatement.
     *
     * @param preparedStatement
     */
    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close preparedStatement.");
            e.printStackTrace();
        }
    }

    /**
     * close resultSet.
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close resultSet.");
            e.printStackTrace();
        }
    }

    /**
     * close database connection.
     */
    public void close() {
        close(connection);
    }


    /**
     * Connect to database.
     *
     */
    public void setConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        System.out.println("Connected to database.");
    }

    /**
     * get just column targetWord from database.
     */
    public ArrayList<String> getTargetWords() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM av");
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    ArrayList<String> targetWords = new ArrayList<>();
                    while (resultSet.next()) {
                        targetWords.add(resultSet.getString("targetWord"));
                    }
                    return targetWords;
                } finally {
                    close(resultSet);
                }
            } finally {
                close(preparedStatement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }


    public void getAllTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM av");
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        String word_target = resultSet.getString("wordTarget");
                        String word_explain = resultSet.getString("wordExplain");
                        String type = resultSet.getString("type");
                        String ipa = resultSet.getString("ipa");
                        Word word = new Word(word_target, word_explain, ipa, type);
                        dictionary.add(word);
                    }
                } finally {
                    close(resultSet);
                }
            } finally {
                close(preparedStatement);
            }
        } catch (SQLException e) {
            System.out.println("return null error");
            System.out.println(e.getMessage());
        }
    }
}
