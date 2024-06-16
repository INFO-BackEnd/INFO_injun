package org.example.info_injun.global.error;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.info_injun.global.error.exception.ErrorCode;
import org.example.info_injun.global.error.exception.TodoException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class GlobalExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            handleException(response, e.getCause());
        } catch (Exception e) {
            handleException(response, e);
        }
    }

    private void handleException(HttpServletResponse response, Throwable throwable) throws IOException {
        ErrorCode errorCode;

        if (throwable instanceof TodoException) {
            errorCode = ((TodoException) throwable).getErrorCode();
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }

        writeErrorResponse(response, errorCode);
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder().status(errorCode.getStatus()).code(errorCode.getCode()).message(errorCode.getMessage()).build();

        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponse.toString());
    }
}
