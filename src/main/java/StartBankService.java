import exchange.Exchanger;

public class StartBankService {
    public static void main(String[] args) {
        Exchanger exchanger = Exchanger.getInstance();
        exchanger.setCurrency("USD", 0.06d);
        exchanger.setCurrency("Leu", 1d);
        exchanger.setCurrency("Euro", 0.05d);
        System.out.println(exchanger.getCurrency());
        System.out.println(exchanger.convert("Euro","Leu", 200d));
        Exchanger anotherExchanger = Exchanger.getInstance();
        anotherExchanger.setCurrency("RUB", 3.57d);
        System.out.println(anotherExchanger.getCurrency());
        System.out.println(anotherExchanger.convert("Leu", "RUB", 200d));
    }
}
