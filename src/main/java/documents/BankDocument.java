package documents;

public abstract class BankDocument {
    private String date;
    private String signature;
    private Double amount;
    private int documentId;
    private static int newDocumentId = 0;

    private static void incrementDocumentId(){
        newDocumentId++;
    }

    public static int getNewDocumentId(){
        return newDocumentId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = getNewDocumentId();
        incrementDocumentId();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
