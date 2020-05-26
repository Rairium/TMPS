package appInterface.controllers;

import appInterface.DBConnect;
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

public class PersonalWalletFormController implements Initializable {
    public TextField currentBalanceField;
    public TextField addMoneyField;
    public TextField withdrawMoneyField;
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
        Double balance;
        ResultSet rs = statement.executeQuery("select amount from clients WHERE id = '" + getClientId() + "'");
        while (rs.next()) {
            balance = rs.getDouble("amount");
            currentBalanceField.setText(balance.toString());
        }
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

    public void goToPersonalProfile(MouseEvent mouseEvent) throws IOException, SQLException {
        this.loader.setLocation(getClass().getResource("/personal_account_form.fxml"));
        this.root = this.loader.load();
        PersonalAccountFormController controller = loader.getController();
        controller.setClientId(getClientId());
        controller.populate();
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void addMoney(MouseEvent mouseEvent) throws SQLException {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("SUCCESS");
        successAlert.setContentText("You add money successfully");
        successAlert.setHeaderText(null);
        double amount, initialAmount, addAmount;
        initialAmount = Double.parseDouble(currentBalanceField.getText());
        if (addMoneyField.getText() == null)
            amount = 0.0;
        else amount = Double.parseDouble(addMoneyField.getText());
        addAmount = initialAmount + amount;
        Connection connection = DBConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE clients SET amount = '" + addAmount + "' WHERE id = '" + getClientId() + "'");
        successAlert.showAndWait();
        addMoneyField.clear();
        populate();
    }

    public void withdrawMoney(MouseEvent mouseEvent) throws SQLException {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("SUCCESS");
        successAlert.setContentText("You withdrew money successfully");
        successAlert.setHeaderText(null);

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setContentText("Insuficient money in balance");
        errorAlert.setHeaderText(null);

        double amount, initialAmount, withdrawAmount;
        initialAmount = Double.parseDouble(currentBalanceField.getText());
        if (withdrawMoneyField.getText() == null)
            amount = 0.0;
        else amount = Double.parseDouble(withdrawMoneyField.getText());
        withdrawAmount = initialAmount - amount;
        Connection connection = DBConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        if (withdrawAmount >= 0) {
            statement.execute("UPDATE clients SET amount = '" + withdrawAmount + "' WHERE id = '" + getClientId() + "'");
            successAlert.showAndWait();
            withdrawMoneyField.clear();
            populate();
        } else {
            errorAlert.showAndWait();
            withdrawMoneyField.clear();
            populate();
        }
    }
}
