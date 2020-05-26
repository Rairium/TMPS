package appInterface.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransactionFormController implements Initializable {
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

    public void close(MouseEvent mouseEvent) {
        System.exit(0);
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
}
