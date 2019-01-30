package http;

import java.io.IOException;

/**
 * Manages connections' error handling.
 */
class HttpConnectionException extends IOException {
    HttpConnectionException(String message) {
        super(message);
    }
}
