package ua.dev13.hw19.base.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomResponse<T> {

    private Error error;
    private T entity;

    public static <T> CustomResponse<T> success(T entity) {
        return CustomResponse
                .<T>builder()
                .error(Error.OK)
                .entity(entity)
                .build();
    }

    public static <T> CustomResponse<T> failed(Error error) {
        return CustomResponse
                .<T>builder()
                .error(error)
                .entity(null)
                .build();
    }

    public enum Error {
        OK,
        NOT_FOUND,
        INVALID_TITLE,
        INVALID_CONTENT
    }
}