package documents;

public class TransferDocument extends BankDocument implements CloneableDocument {
    private String transferType;
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public TransferDocument(){
        this.setDocumentId(BankDocument.getNewDocumentId());
    }

    public CloneableDocument makeCopy() {
        TransferDocument transferDocument = null;
        try {
            transferDocument = (TransferDocument) super.clone();
            transferDocument.setDocumentId(BankDocument.getNewDocumentId());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return transferDocument;
    }

    public String  getTransferType() {
        return transferType;
    }
}
