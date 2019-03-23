package com.example.noderedserver;

import android.util.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLAccess {
        private Connection connect = null;
        private Statement statement = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;

        public void getConnection() throws Exception{
            try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection("jdbc:mysql://192.168.1.14:3306" + "user=root&password=root");

                // Statements allow to issue SQL queries to the database
                statement = connect.createStatement();
            }
            catch (Exception e){
                System.out.println(e);
            }
            finally {
                close();
            }
        }

        public void initializeDataBase() throws Exception {
            try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection("jdbc:mysql://192.168.1.14:3306" + "user=root&password=root");

                // Statements allow to issue SQL queries to the database
                statement = connect.createStatement();
                // Result set get the result of the SQL query
                resultSet = statement.executeQuery("select * from bookstore");
                writeResultSet(resultSet);

                // PreparedStatements can use variables and are more efficient
                preparedStatement = connect.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
                // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
                // Parameters start with 1
                preparedStatement.setString(1, "Test");
                preparedStatement.setString(2, "TestEmail");
                preparedStatement.setString(3, "TestWebpage");
                //preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
                preparedStatement.setString(5, "TestSummary");
                preparedStatement.setString(6, "TestComment");
                preparedStatement.executeUpdate();

                preparedStatement = connect.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
                resultSet = preparedStatement.executeQuery();
                writeResultSet(resultSet);

                // Remove again the insert comment
                preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
                preparedStatement.setString(1, "Test");
                preparedStatement.executeUpdate();

                resultSet = statement.executeQuery("select * from feedback.comments");
                writeMetaData(resultSet);

            } catch (Exception e) {
                throw e;
            } finally {
                close();
            }

        }

        private void writeMetaData(ResultSet resultSet) throws SQLException {
            //  Now get some metadata from the database
            // Result set get the result of the SQL query

            System.out.println("The columns in the table are: ");

            System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
            for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
            }
        }

        private void writeResultSet(ResultSet resultSet) throws SQLException {
            // ResultSet is initially before the first data set
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
                String title = resultSet.getString("Title");
                String Caterogy = resultSet.getString("Caterogy");
                String comment = resultSet.getString("Comments");
                String userRating = resultSet.getString("User Rating");
                String inStock = resultSet.getString("In Stock");
                String releaseDate = resultSet.getString("Release Date");
                //Date date = resultSet.getDate("");
                System.out.println("User: " + title);
                System.out.println("Website: " + Caterogy);
                System.out.println("summary: " + comment);
                //System.out.println("Date: " + date);
                System.out.println("Comment: " + userRating);
                System.out.println("Comment: " + inStock);
                System.out.println("Comment: " + releaseDate);
            }
        }

        // You need to close the resultSet
        private void close() {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    Log.i(null, "ResultSet Connection Sucessfully Closed");
                }

                if (statement != null) {
                    statement.close();
                    Log.i(null, "Statement Connection Sucessfully Closed");
                }

                if (connect != null) {
                    connect.close();
                    Log.i(null, "MySQL Connection Sucessfully Closed");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
