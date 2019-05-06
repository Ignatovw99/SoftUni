package exception;

public class CustomException extends Throwable {
    private String message;

    protected CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
