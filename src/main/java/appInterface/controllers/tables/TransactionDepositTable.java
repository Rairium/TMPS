package appInterface.controllers.tables;

public class TransactionDepositTable {
    String dep_date, dep_signature;
    Double dep_amount, dep_rate;

    public TransactionDepositTable(String dep_date, String dep_signature, Double dep_amount, Double dep_rate) {
        this.dep_date = dep_date;
        this.dep_signature = dep_signature;
        this.dep_amount = dep_amount;
        this.dep_rate = dep_rate;
    }

    public String getDep_date() {
        return dep_date;
    }

    public void setDep_date(String dep_date) {
        this.dep_date = dep_date;
    }

    public String getDep_signature() {
        return dep_signature;
    }

    public void setDep_signature(String dep_signature) {
        this.dep_signature = dep_signature;
    }

    public Double getDep_amount() {
        return dep_amount;
    }

    public void setDep_amount(Double dep_amount) {
        this.dep_amount = dep_amount;
    }

    public Double getDep_rate() {
        return dep_rate;
    }

    public void setDep_rate(Double dep_rate) {
        this.dep_rate = dep_rate;
    }
}