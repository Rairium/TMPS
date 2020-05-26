package appInterface.controllers;

import appInterface.DBConnect;
import appInterface.controllers.tables.TransactionDepositTable;
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
import java.util.ResourceBundle;

public class TransactionDepositFormController implements Initializable {
    public TableView<TransactionDepositTable> depositTransactionDataTable;
    public TableColumn<TransactionDepositTable, String> dep_date;
    public TableColumn<TransactionDepositTable, String> dep_signature;
    public TableColumn<TransactionDepositTable, Double> dep_rate;
    public TableColumn<TransactionDepositTable, Double> dep_amount;

    ObservableList<TransactionDepositTable> observableList = FXCollections.observableArrayList();

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

    public void populateTable(){
        try {
            observableList = FXCollections.observableArrayList();
            Connection connection = DBConnect.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select dep_date, dep_signature, dep_rate, dep_amount from depositdocuments WHERE dep_client = '"+getClientId()+"'");
            while (rs.next()) {
                observableList.add(new TransactionDepositTable
                        (rs.getString("dep_date"), rs.getString("dep_signature"),
                                rs.getDouble("dep_amount"), rs.getDouble("dep_rate")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        dep_date.setCellValueFactory(new PropertyValueFactory<TransactionDepositTable, String>("dep_date"));
        dep_signature.setCellValueFactory(new PropertyValueFactory<TransactionDepositTable, String>("dep_signature"));
        dep_rate.setCellValueFactory(new PropertyValueFactory<TransactionDepositTable, Double>("dep_rate"));
        dep_amount.setCellValueFactory(new PropertyValueFactory<TransactionDepositTable, Double>("dep_amount"));
        depositTransactionDataTable.setItems(observableList);
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

    public void addTransactionDeposit(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_deposit_add_form.fxml"));
        this.root = this.loader.load();
        TransactionDepositAddFormController controller = loader.getController();
        controller.setClientId(getClientId());
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
