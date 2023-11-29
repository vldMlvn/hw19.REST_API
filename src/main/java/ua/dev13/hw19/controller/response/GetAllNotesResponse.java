package ua.dev13.hw19.controller.response;

import lombok.Builder;
import lombok.Data;
import ua.dev13.hw19.entity.Note;

import java.util.List;

@Builder
@Data
public class GetAllNotesResponse {
    private Error error;
    private List<Note> notes;

    public enum Error{
        ok
    }

    public static GetAllNotesResponse success(List<Note> notes){
        return builder().error(Error.ok).notes(notes).build();
    }

    public static GetAllNotesResponse failed(Error error){
        return builder().error(error).notes(null).build();
    }

}
