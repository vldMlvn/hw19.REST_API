package ua.dev13.hw19.controller.response;

import lombok.Builder;
import lombok.Data;
import ua.dev13.hw19.entity.Note;

@Builder
@Data
public class CreateAndUpdateNoteResponse {
    private Error error;
    private Note note;

    public enum Error{
        ok,
        invalidTitle,
        invalidContent,
        notFound
    }

    public static CreateAndUpdateNoteResponse success(Note note){
        return builder().error(Error.ok).note(note).build();
    }

    public static CreateAndUpdateNoteResponse failed(Error error){
        return builder().error(error).note(null).build();
    }
}
