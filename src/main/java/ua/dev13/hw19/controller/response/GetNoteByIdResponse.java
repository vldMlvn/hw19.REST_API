package ua.dev13.hw19.controller.response;

import lombok.Builder;
import lombok.Data;
import ua.dev13.hw19.entity.Note;

@Builder
@Data
public class GetNoteByIdResponse {
    private Error error;
    private Note note;

    public enum Error{
        ok,
        notFound
    }

    public static GetNoteByIdResponse success(Note note){
        return builder().error(Error.ok).note(note).build();
    }

    public static GetNoteByIdResponse failed (Error error){
        return builder().error(error).note(null).build();
    }
}
