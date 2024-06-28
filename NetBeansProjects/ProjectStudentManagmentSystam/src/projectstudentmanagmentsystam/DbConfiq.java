/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectstudentmanagmentsystam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wazeem
 */
public class DbConfiq {
    private static DbConfiq instance;
    
    /**
     *
     */
    
    private DbConfiq(){
        
    }
    public static synchronized DbConfiq getInstance(){
        
        if(instance == null){
            instance = new DbConfiq();
            
        }
        return instance;
    }
    
    
    public Connection dbConnction()throws Expception, ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
       String url = "jdbc:mysql://localhost:3306/project1";
       String user = "root";
       String password = "Str0ngPessword!";
       
       Connection con = DriverManager.getConnection(url, user, password);
       return con;
        
    }
    
}
