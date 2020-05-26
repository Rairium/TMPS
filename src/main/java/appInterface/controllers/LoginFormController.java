package appInterface.controllers;

import appInterface.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private int clientId;


    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void login(MouseEvent mouseEvent) {


        String email, password;
        email = emailField.getText();
        password = passwordField.getText();

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setContentText("Check your credentials");
        errorAlert.setHeaderText(null);

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("SUCCESS");
        successAlert.setContentText("Successful login");
        successAlert.setHeaderText(null);

        boolean isLogined = false;

        try {
            Connection connection = DBConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from clients");
            while (resultSet.next()){
                if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)){
                    isLogined = true;
                    setClientId(resultSet.getInt("id"));
                    break;
                }
            }
            if (!isLogined){
                errorAlert.showAndWait();
            } else {
                successAlert.showAndWait();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/mainmenuform.fxml"));
                Parent root = loader.load();
                MainMenuFormController controller = loader.getController();
                controller.setClientId(getClientId());
                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToSignup(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/signupform.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
