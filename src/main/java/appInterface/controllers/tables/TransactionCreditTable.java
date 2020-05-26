package appInterface.controllers.tables;

public class TransactionCreditTable {
    String cd_date, cd_signature, cd_garant_name;
    Double cd_amount;

    public String getCd_date() {
        return cd_date;
    }

    public void setCd_date(String cd_date) {
        this.cd_date = cd_date;
    }

    public String getCd_signature() {
        return cd_signature;
    }

    public void setCd_signature(String cd_signature) {
        this.cd_signature = cd_signature;
    }

    public String getCd_garant_name() {
        return cd_garant_name;
    }

    public void setCd_garant_name(String cd_garant_name) {
        this.cd_garant_name = cd_garant_name;
    }

    public Double getCd_amount() {
        return cd_amount;
    }

    public void setCd_amount(Double cd_amount) {
        this.cd_amount = cd_amount;
    }

    public TransactionCreditTable(String cd_date, String cd_signature, String cd_garant_name, Double cd_amount) {
        this.cd_date = cd_date;
        this.cd_signature = cd_signature;
        this.cd_garant_name = cd_garant_name;
        this.cd_amount = cd_amount;
    }

}
