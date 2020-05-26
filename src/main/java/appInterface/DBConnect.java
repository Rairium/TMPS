package appInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private DBConnect(){

    }
    public static DBConnect getInstance(){
        return new DBConnect();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/yearproject", "root", "root");
    }
}
