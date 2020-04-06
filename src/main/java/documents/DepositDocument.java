package documents;

public class DepositDocument extends BankDocument implements CloneableDocument {
    private Double rate;

    public DepositDocument(){
        this.setDocumentId(BankDocument.getNewDocumentId());
    }

    public CloneableDocument makeCopy() {
        DepositDocument depositDocument = null;
        try {
            depositDocument = (DepositDocument) super.clone();
            depositDocument.setDocumentId(BankDocument.getNewDocumentId());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return depositDocument;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
