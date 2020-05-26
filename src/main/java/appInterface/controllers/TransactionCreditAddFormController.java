package appInterface.controllers;

import appInterface.DBConnect;
import appInterface.controllers.tables.TransactionCreditTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TransactionCreditAddFormController implements Initializable {

    public TextField dateField;
    public TextField signatureField;
    public TextField amountField;
    public TextField garantNameField;
    private int clientId;
    private FXMLLoader loader = new FXMLLoader();
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void close(MouseEvent mouseEvent) {
        System.exit(0);
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

    public void goToPersonalAccount(MouseEvent mouseEvent) throws IOException, SQLException {
        this.loader.setLocation(getClass().getResource("/personal_account_form.fxml"));
        this.root = this.loader.load();
        PersonalAccountFormController controller = loader.getController();
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

    public void goToCredit(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_credit_form.fxml"));
        this.root = this.loader.load();
        TransactionCreditFormController controller = loader.getController();
        controller.setClientId(getClientId());
        controller.populateTable();
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void goToDeposit(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_deposit_form.fxml"));
        this.root = this.loader.load();
        TransactionDepositFormController controller = loader.getController();
        controller.setClientId(getClientId());
        controller.populateTable();
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void goToTransfer(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_transfer_form.fxml"));
        this.root = this.loader.load();
        TransactionTransferFormController controller = loader.getController();
        controller.setClientId(getClientId());
        controller.populateTable();
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void insert(MouseEvent mouseEvent) throws SQLException {

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("SUCCESS");
        successAlert.setContentText("The Credit Transaction was successfully created");
        successAlert.setHeaderText(null);

        Connection connection = DBConnect.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT into creditdocuments (cd_date, cd_signature, cd_amount, cd_garant_name, cd_client) values " +
                "('" + dateField.getText() + "', '" + signatureField.getText() + "', '" + amountField.getText() + "', '" + garantNameField.getText() + "', '" + getClientId() + "' )");
        successAlert.showAndWait();
        dateField.clear();
        signatureField.clear();
        amountField.clear();
        garantNameField.clear();
    }
}
