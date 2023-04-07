package ba.unsa.etf.pnwt.recommendationservice.exceptions;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }
}
