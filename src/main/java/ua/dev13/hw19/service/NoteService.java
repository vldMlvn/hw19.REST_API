package ua.dev13.hw19.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dev13.hw19.controller.response.CreateAndUpdateNoteResponse;
import ua.dev13.hw19.controller.response.DeleteNoteByIdResponse;
import ua.dev13.hw19.controller.response.GetAllNotesResponse;
import ua.dev13.hw19.controller.response.GetNoteByIdResponse;
import ua.dev13.hw19.entity.Note;
import ua.dev13.hw19.repository.NoteRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    public GetAllNotesResponse getAll(){
        List<Note> notes = repository.findAll();
        return GetAllNotesResponse.success(notes);
    }

   public GetNoteByIdResponse getById(Long id){
        Optional<Note> note = repository.findById(id);
        if(note.isEmpty()){
            return GetNoteByIdResponse.failed(GetNoteByIdResponse.Error.notFound);
        } else {
            return GetNoteByIdResponse.success(note.get());
        }
   }

    public CreateAndUpdateNoteResponse create(Note note){
        if(note.getTitle() == null){
            return CreateAndUpdateNoteResponse.failed(CreateAndUpdateNoteResponse.Error.invalidTitle);
        }
        if(note.getContent() == null){
            return CreateAndUpdateNoteResponse.failed(CreateAndUpdateNoteResponse.Error.invalidContent);
        }
        repository.save(note);
        return CreateAndUpdateNoteResponse.success(note);
    }

    public CreateAndUpdateNoteResponse update(Note note){
        if(note.getTitle() == null){
            return CreateAndUpdateNoteResponse.failed(CreateAndUpdateNoteResponse.Error.invalidTitle);
        }
        if(note.getContent() == null){
            return CreateAndUpdateNoteResponse.failed(CreateAndUpdateNoteResponse.Error.invalidContent);
        }
        if(repository.existsById(note.getId())){
            Note updatedNote = repository.save(note);
            return CreateAndUpdateNoteResponse.success(updatedNote);
        }
        return CreateAndUpdateNoteResponse.failed(CreateAndUpdateNoteResponse.Error.notFound);
    }

    public DeleteNoteByIdResponse deleteById(Long id){
       if (repository.existsById(id)){
           repository.deleteById(id);
           return DeleteNoteByIdResponse.success();
       }
        return DeleteNoteByIdResponse.failed();
    }
}
