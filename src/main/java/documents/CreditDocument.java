package documents;

public class CreditDocument extends BankDocument implements CloneableDocument {
    private String garantPersonName;

    public CreditDocument(){
        this.setDocumentId(BankDocument.getNewDocumentId());
    }

    public CloneableDocument makeCopy() {
        CreditDocument creditDocument = null;
        try {
            creditDocument = (CreditDocument) super.clone();
            creditDocument.setDocumentId(BankDocument.getNewDocumentId());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return creditDocument;
    }

    public String getGarantPersonName() {
        return garantPersonName;
    }

    public void setGarantPersonName(String garantPersonName) {
        this.garantPersonName = garantPersonName;
    }
}
