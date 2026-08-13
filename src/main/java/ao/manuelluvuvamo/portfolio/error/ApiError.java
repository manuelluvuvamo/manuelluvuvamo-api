package ao.manuelluvuvamo.portfolio.error;

import java.time.Instant;
import java.util.Map;

/**
 * Formato unico de erro devolvido pela API.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fields
) {
    public static ApiError of(int status, String error, String message, String path) {
        return new ApiError(Instant.now(), status, error, message, path, null);
    }

    public static ApiError validation(String path, Map<String, String> fields) {
        return new ApiError(Instant.now(), 400, "Bad Request", "Dados invalidos.", path, fields);
    }
}
