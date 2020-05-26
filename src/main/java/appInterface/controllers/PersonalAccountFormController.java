package appInterface.controllers;

import appInterface.DBConnect;
import appInterface.controllers.tables.TransactionTransferTable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PersonalAccountFormController implements Initializable {

    public TextField nameField;
    public TextField surnameField;
    public TextField emailField;
    public TextField phoneField;
    public TextField cityField;
    public TextField districtField;
    public TextField st_nameField;
    public TextField st_addField;

    private int clientId;
    private FXMLLoader loader = new FXMLLoader();
    private Parent root;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populate() throws SQLException {
        Connection connection = DBConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from clients WHERE id = '"+getClientId()+"'");
        while (rs.next()) {
            nameField.setText(rs.getString("name"));
            surnameField.setText(rs.getString("surname"));
            emailField.setText(rs.getString("email"));
            phoneField.setText(rs.getString("phone"));
            cityField.setText(rs.getString("city"));
            districtField.setText(rs.getString("district"));
            st_addField.setText(rs.getString("street_address"));
            st_nameField.setText(rs.getString("street_name"));
        }
    }

    public void goToPersonalWallet(MouseEvent mouseEvent) throws IOException, SQLException {
        this.loader.setLocation(getClass().getResource("/personal_wallet_form.fxml"));
        this.root = this.loader.load();
        PersonalWalletFormController controller = loader.getController();
        controller.setClientId(getClientId());
        controller.populate();
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void goToTransactions(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_form.fxml"));
        this.root = this.loader.load();
        TransactionFormController controller = loader.getController();
        controller.setClientId(getClientId());
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void close(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void save(MouseEvent mouseEvent) throws SQLException {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("SUCCESS");
        successAlert.setContentText("Account data successfully saved");
        successAlert.setHeaderText(null);
        String name, surname, email, phone, city, district, street_name, street_address;
        if(nameField.getText() == null)
            name = "";
        else name = nameField.getText();
        if(surnameField.getText() == null)
            surname = "";
        else surname = surnameField.getText();
        if(emailField.getText() == null)
            email = "";
        else email = emailField.getText();
        if(phoneField.getText() == null)
            phone = "";
        else phone = phoneField.getText();
        if(cityField.getText() == null)
            city = "";
        else city = cityField.getText();
        if(districtField.getText() == null)
            district = "";
        else district = districtField.getText();
        if(st_nameField.getText() == null)
            street_name = "";
        else street_name = st_nameField.getText();
        if(st_addField.getText() == null)
            street_address = "";
        else street_address = st_addField.getText();
        Connection connection = DBConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE clients SET name = '" + name + "'" + ", surname = '" + surname + "'"
                + ", email = '" + email + "'" + ", phone = '" + phone + "'"
                + ", city = '" + city + "'" + ", district = '" + district + "'"
                + ", street_address = '" + street_address + "'" + ", street_name = '" + street_name + "' WHERE id = '"+getClientId()+"'");
        successAlert.showAndWait();
        populate();
    }
}
