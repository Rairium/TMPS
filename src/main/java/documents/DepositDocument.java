package documents;

public class DepositDocument extends BankDocument implements CloneableDocument {
    private Double rata;

    public CloneableDocument makeCopy() {
        DepositDocument depositDocument = null;
        try {
            depositDocument = (DepositDocument) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return depositDocument;
    }

    public Double getRata() {
        return rata;
    }

    public void setRata(Double rata) {
        this.rata = rata;
    }

}
