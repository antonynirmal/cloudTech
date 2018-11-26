package my.exercise.cryptocurrency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PriceListException extends RuntimeException {

    public PriceListException() {
        super();
    }

    public PriceListException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PriceListException(String message, Throwable cause) {
        super(message, cause);
    }

    public PriceListException(String message) {
        super(message);
    }

    public PriceListException(Throwable cause) {
        super(cause);
    }
}
