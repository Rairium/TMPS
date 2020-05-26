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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TransactionCreditFormController implements Initializable {
    public TableView<TransactionCreditTable> creditTransactionDataTable;
    public TableColumn<TransactionCreditTable, String> cd_date;
    public TableColumn<TransactionCreditTable, String> cd_signature;
    public TableColumn<TransactionCreditTable, String> cd_garant_name;
    public TableColumn<TransactionCreditTable, Double> cd_amount;

    ObservableList<TransactionCreditTable> observableList = FXCollections.observableArrayList();

    private int clientId;
    private FXMLLoader loader = new FXMLLoader();
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateTable(){
        try {
            observableList = FXCollections.observableArrayList();
            Connection connection = DBConnect.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select cd_date, cd_signature, cd_amount, cd_garant_name from creditdocuments WHERE cd_client = '"+getClientId()+"'");
            while (rs.next()) {
                observableList.add(new TransactionCreditTable
                        (rs.getString("cd_date"), rs.getString("cd_signature"),
                                rs.getString("cd_garant_name"), rs.getDouble("cd_amount")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        cd_date.setCellValueFactory(new PropertyValueFactory<TransactionCreditTable, String>("cd_date"));
        cd_signature.setCellValueFactory(new PropertyValueFactory<TransactionCreditTable, String>("cd_signature"));
        cd_garant_name.setCellValueFactory(new PropertyValueFactory<TransactionCreditTable, String>("cd_garant_name"));
        cd_amount.setCellValueFactory(new PropertyValueFactory<TransactionCreditTable, Double>("cd_amount"));
        creditTransactionDataTable.setItems(observableList);
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

    public void addTransactionCredit(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_credit_add_form.fxml"));
        this.root = this.loader.load();
        TransactionCreditAddFormController controller = loader.getController();
        controller.setClientId(getClientId());
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
