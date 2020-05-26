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
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignupFormController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void insert(MouseEvent mouseEvent) throws IOException {
        try {
            Connection connection = DBConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            Statement verifyStatement = connection.createStatement();
            ResultSet resultSet = verifyStatement.executeQuery("SELECT email from clients");
            boolean exists = false;
            boolean isEmpty = false;

            Alert existsAlert = new Alert(Alert.AlertType.ERROR);
            existsAlert.setTitle("ERROR");
            existsAlert.setContentText("An account with such email exists");
            existsAlert.setHeaderText(null);

            Alert isEmptyAlert = new Alert(Alert.AlertType.ERROR);
            isEmptyAlert.setTitle("ERROR");
            isEmptyAlert.setContentText("Check input fields, they should not be empty");
            isEmptyAlert.setHeaderText(null);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("SUCCESS");
            successAlert.setContentText("Successful sign up");
            successAlert.setHeaderText(null);
            while (resultSet.next()) {
                if (resultSet.getString("email").equals(emailField.getText())) {
                    exists = true;
                    break;
                }
            }
            if (nameField.getText().equals("") || surnameField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("")) {
                isEmpty = true;
            }
            if (!exists && !isEmpty) {
                statement.execute("INSERT into clients (name, surname, email, password, amount) values ('" + nameField.getText() + "','" + surnameField.getText() + "','" + emailField.getText() + "','" + passwordField.getText() + "', + '0')");
                successAlert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/loginform.fxml"));
                Node node = (Node) mouseEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                if (isEmpty){
                    isEmptyAlert.showAndWait();
                } else
                existsAlert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void goToLogin(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginform.fxml"));
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
