import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class STARTAPP {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yearproject", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from clients");
            while (resultSet.next()){
                System.out.println(resultSet.getString("name") + " " + resultSet.getString("surname"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
