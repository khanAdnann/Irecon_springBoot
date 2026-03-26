package com.irecon.innovation.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Value;

public class ConnectionUtil  {

	
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String login;

    @Value("${spring.datasource.password}")
    private String password;
    private Connection myConnection;




  
    public void createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        System.out.println("New Connection");

        myConnection = DriverManager.getConnection(url, login, password);

        myConnection.setAutoCommit(true);
        myConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

    public Connection getconn() {
        return this.myConnection;
    }

    public void RollBack() throws SQLException {
        myConnection.rollback();
        System.out.println("RollBack Process Called");
    }

    public void CloseConnection() throws SQLException {
        myConnection.close();
        System.out.println("Connection to conv Closed");
    }



    public ConnectionUtil() throws ClassNotFoundException, SQLException {

    	
    	
  


 //  url="jdbc:mysql://10.161.79.12:3511/debitcard_recon_pnb"; 
 url="jdbc:mysql://103.108.12.208:3511/irecon";
  //url="jdbc:mysql://103.108.12.208:3511/irecon"; 
             
     login = "reconuser";	
 password = "r1e2c3o4n5";
     //password = "Un1ted@PNB#!";
 // password = "Recon@123";

          
        createConnection();

    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmnt;
      
        stmnt = myConnection.createStatement();
        ResultSet rsltSt;
        rsltSt = stmnt.executeQuery(query);

      
        return rsltSt;
    }

    public Vector executeQueryVector(String query) throws SQLException {
        Vector vectMain = new Vector(20, 10);
        Statement stmnt;
      
        stmnt = myConnection.createStatement();
        ResultSet rs;
        rs = stmnt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData();

        int numberOfColumns = rsMetaData.getColumnCount();
        while (rs.next()) {
            Vector vectRec = new Vector(20, 10);
            for (int i = 1; i <= numberOfColumns; i++) {
                String str = rs.getString(i);
                vectRec.add(str);
            }
            vectMain.add(vectRec);
        }

        rs.close();
        stmnt.close();
        rs = null;
        stmnt = null;

        return vectMain;
    }

    public int executeUpdate(String query) throws SQLException {
        Statement stmnt;
        stmnt = myConnection.createStatement();
        return stmnt.executeUpdate(query);
    }

    public void resetAutoCommit() throws SQLException {
        myConnection.commit();
        myConnection.setAutoCommit(true);
    }

    public static void main(String args[]) {
        try {
            ConnectionUtil conn = new ConnectionUtil();
            conn.createConnection();
          
            System.out.println(" Connection is made on conv");

        } catch (Exception ex) {
            System.out.println("Exception in Main--> " + ex.getMessage());
        }

    }

   
}
