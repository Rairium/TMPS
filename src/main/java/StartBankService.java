import documents.CreditDocument;
import services.DocumentMaker;
import services.CurrencyExchanger;

public class StartBankService {
    public static void main(String[] args) {
        CurrencyExchanger currencyExchanger = CurrencyExchanger.getInstance();
        currencyExchanger.setCurrency("USD", 0.06d);
        currencyExchanger.setCurrency("Leu", 1d);
        currencyExchanger.setCurrency("Euro", 0.05d);
        System.out.println(currencyExchanger.getCurrency());
        System.out.println(currencyExchanger.convert("Euro","Leu", 200d));
        CurrencyExchanger anotherCurrencyExchanger = CurrencyExchanger.getInstance();
        anotherCurrencyExchanger.setCurrency("RUB", 3.57d);
        System.out.println(anotherCurrencyExchanger.getCurrency());
        System.out.println(anotherCurrencyExchanger.convert("Leu", "RUB", 200d));

//        ------------------------------------------

        DocumentMaker documentMaker = new DocumentMaker();
        CreditDocument creditDocument = new CreditDocument();
        creditDocument.setDate("Today");
        creditDocument.setSignature("Gutu");
        CreditDocument clonedCreditDocument = (CreditDocument) documentMaker.getClonedDocument(creditDocument);
        System.out.println(clonedCreditDocument.getDate());
        System.out.println(clonedCreditDocument.getSignature());
    }
}
