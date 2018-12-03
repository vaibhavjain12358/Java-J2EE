package com.asset.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AssetDALConnection 
{
    public Connection connection;
    public PreparedStatement pst;
    public ResultSet rst;
    public Statement statement;

    
  public void connect()
    {
      try
      { 
          Class.forName("oracle.jdbc.driver.OracleDriver");
          
          connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
          statement = connection.createStatement();
          System.out.println("Connected");
      }
      catch(Exception e)
      {
          System.out.println("Error in conection"+e);
      }
    }
}