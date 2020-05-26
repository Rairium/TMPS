package appInterface.controllers;

import appInterface.DBConnect;
import appInterface.controllers.tables.TransactionDepositTable;
import appInterface.controllers.tables.TransactionTransferTable;
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

public class TransactionTransferFormController implements Initializable {
    public TableView<TransactionTransferTable> transferTransactionDataTable;
    public TableColumn<TransactionTransferTable, String> td_date;
    public TableColumn<TransactionTransferTable, String> td_signature;
    public TableColumn<TransactionTransferTable, String> td_type;
    public TableColumn<TransactionTransferTable, String> td_client_IDNP;
    public TableColumn<TransactionTransferTable, Double> td_amount;

    ObservableList<TransactionTransferTable> observableList = FXCollections.observableArrayList();

    private int clientId;
    private FXMLLoader loader = new FXMLLoader();
    private Parent root;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public void populateTable(){
        try {
            observableList = FXCollections.observableArrayList();
            Connection connection = DBConnect.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select td_date, td_signature, td_type, td_client_IDNP, td_amount from transferdocuments WHERE td_client = '"+getClientId()+"'");
            while (rs.next()) {
                observableList.add(new TransactionTransferTable
                        (rs.getString("td_date"), rs.getString("td_signature"), rs.getString("td_type"),
                                rs.getString("td_client_IDNP"), rs.getDouble("td_amount")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        td_date.setCellValueFactory(new PropertyValueFactory<TransactionTransferTable, String>("td_date"));
        td_signature.setCellValueFactory(new PropertyValueFactory<TransactionTransferTable, String>("td_signature"));
        td_type.setCellValueFactory(new PropertyValueFactory<TransactionTransferTable, String>("td_type"));
        td_client_IDNP.setCellValueFactory(new PropertyValueFactory<TransactionTransferTable, String>("td_client_IDNP"));
        td_amount.setCellValueFactory(new PropertyValueFactory<TransactionTransferTable, Double>("td_amount"));
        transferTransactionDataTable.setItems(observableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void addTransactionTransfer(MouseEvent mouseEvent) throws IOException {
        this.loader.setLocation(getClass().getResource("/transaction_transfer_add_form.fxml"));
        this.root = this.loader.load();
        TransactionTransferAddFormController controller = loader.getController();
        controller.setClientId(getClientId());
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
