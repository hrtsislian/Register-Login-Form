
package com.mvc.dao;

import com.mvc.bean.LoginBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao 
{
    public String authorizeLogin(LoginBean loginBean) //create authorizeLogin()function
    {
        String username=loginBean.getUsername(); //get username value through loginBean object and store in temporary variable "username"
        String password=loginBean.getPassword(); //get password value through loginBean object and store in temporary variable "password"
        
        String dbusername="";  //create two variable for use next process
        String dbpassword="";
        
        String url="jdbc:mysql://83.212.108.47:3306/appdb"; //database connection url string
        String uname="hrtsislianlo"; //database username
        String pass="sepuku"; //database password
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(url,uname,pass); //create connection
            
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("select * from user where username=? and password=?"); //sql select query 
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery(); //execute query and set in Resultset object rs.
             
            while(rs.next())
            {    
                dbusername=rs.getString("username");   //fetchable database record username and password store in this two variable dbusername,dbpassword above created 
                dbpassword=rs.getString("password"); 
                
                if(username.equals(dbusername) && password.equals(dbpassword))  //apply if condition check for fetchable database username and password are match for user input side type in textbox
                {
                    return "SUCCESS LOGIN"; //if valid condition return string "SUCCESS LOGIN" 
                }
            } 
           
            pstmt.close(); //close statement
            
            con.close(); //close connection
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "WRONG USERNAME AND PASSWORD"; //if invalid condition return string "WRONG USERNAME AND PASSWORD"
    }
}