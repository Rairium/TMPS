package appInterface.controllers.tables;

public class TransactionTransferTable {
    String td_date, td_signature, td_type, td_client_IDNP;
    Double td_amount;

    public TransactionTransferTable(String td_date, String td_signature, String td_type, String td_client_IDNP, Double td_amount) {
        this.td_date = td_date;
        this.td_signature = td_signature;
        this.td_type = td_type;
        this.td_client_IDNP = td_client_IDNP;
        this.td_amount = td_amount;
    }

    public String getTd_date() {
        return td_date;
    }

    public void setTd_date(String td_date) {
        this.td_date = td_date;
    }

    public String getTd_signature() {
        return td_signature;
    }

    public void setTd_signature(String td_signature) {
        this.td_signature = td_signature;
    }

    public String getTd_type() {
        return td_type;
    }

    public void setTd_type(String td_type) {
        this.td_type = td_type;
    }

    public String getTd_client_IDNP() {
        return td_client_IDNP;
    }

    public void setTd_client_IDNP(String td_client_IDNP) {
        this.td_client_IDNP = td_client_IDNP;
    }

    public Double getTd_amount() {
        return td_amount;
    }

    public void setTd_amount(Double td_amount) {
        this.td_amount = td_amount;
    }
}
