package exceptions;

public class AccountDataNotFound extends RuntimeException {
    public AccountDataNotFound(String errorMessage){
        super(errorMessage);
    }
}
