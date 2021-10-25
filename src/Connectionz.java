import java.sql.*;
public class Connectionz
{
    public static Connection con;
    public static String dbFile="res/Stockdata.accdb";
    public static String dbUrl="jdbc:ucanaccess://"+dbFile.trim()+"; memory=true";

    public static Connection getConnection(){
        try{
            con=DriverManager.getConnection(dbUrl, "", "");
        }catch (Exception e){
            System.out.println(""+e);
        }
        return con;
    }
}