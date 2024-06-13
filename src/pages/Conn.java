package pages;
import java.sql.*;
public class Conn {

    Connection c;
    Statement s;

    public Conn(){

        try{
            c = DriverManager.getConnection("jdbc:mysql:///Bank_App","root","Your_password");
            s = c.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
