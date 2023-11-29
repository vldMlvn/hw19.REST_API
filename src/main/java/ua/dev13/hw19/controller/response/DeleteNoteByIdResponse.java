package ua.dev13.hw19.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteNoteByIdResponse {
    private Error error;

    public enum Error{
        ok,
        invalidId
    }

    public static DeleteNoteByIdResponse success(){
        return builder().error(Error.ok).build();
    }

    public static DeleteNoteByIdResponse failed(){
        return builder().error(Error.invalidId).build();
    }
}
