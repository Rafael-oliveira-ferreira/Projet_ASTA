package altn72.projet_asta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resource, String field, Object value) {
        super(String.format("%s with %s '%s' already exists", resource, field, value));
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
