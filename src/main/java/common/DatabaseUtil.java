/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Acer
 */
public class DatabaseUtil {
    public static Connection getConnection(){
        Connection conn = null;
        try{
            //1.nạp driver
            //1.Nap driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.Thiet lap ket noi CSDL
                conn = DriverManager.getConnection("jdbc:sqlserver://KAZEKI;databaseName=demodb", "sa", "suzaki705");
        }catch(Exception e){
            System.out.println("loi" + e.toString());
        }
        return conn;
    }
}
