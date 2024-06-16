package org.example.info_injun.global.error;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String code;
    private final String message;

    @Override
    public String toString() {
        return "{\n" + "  \"status\": " + status + ",\n" + "  \"code\": \"" + code + "\",\n" + "  \"message\": \"" + message + "\"\n" + "}";
    }
}
