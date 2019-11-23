package com.mvc.dao;
import com.mvc.bean.RegisterBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDao 
{
    public String authorizeRegister(RegisterBean registerBean) //create authorizeRegister()function
    {
        String firstname=registerBean.getFirstname();
        String lastname=registerBean.getLastname();
        String username=registerBean.getUsername();  //get all value through registerBean object and store in temporary variable
        String password=registerBean.getPassword();
        
        String url="jdbc:mysql://83.212.108.47:3306/appdb"; //database connection url string
        String uname="hrtsislianlo"; //database username
        String pass="sepuku"; //database password
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(url,uname,pass); //create connection
            
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("insert into user(firstname,lastname,username,password) values(?,?,?,?)"); //sql insert query
            pstmt.setString(1,firstname);
            pstmt.setString(2,lastname);
            pstmt.setString(3,username);
            pstmt.setString(4,password); 
            pstmt.executeUpdate(); //execute query
             
            pstmt.close(); //close statement
            
            con.close(); //close connection
           
            return "SUCCESS REGISTER"; //if valid return string "SUCCESS REGISTER" 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return "FAIL REGISTER"; //if invalid return string "FAIL REGISTER"
    }
}

 