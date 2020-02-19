package documents;

public class CreditDocument extends BankDocument implements CloneableDocument {
    public CloneableDocument makeCopy() {
        CreditDocument creditDocument = null;
        try {
            creditDocument = (CreditDocument) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return creditDocument;
    }
}
