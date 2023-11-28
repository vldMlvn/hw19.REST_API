package ua.dev13.hw19.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dev13.hw19.entity.Note;
import ua.dev13.hw19.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    public List<Note> getAll(){
        return repository.findAll();
    }

    public Note getById(Long id){
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Note add(Note note){
        return repository.save(note);
    }

    public void update(Note note){
        if (repository.existsById(note.getId())){
            repository.save(note);
        } else {
            throw new IllegalArgumentException("Note not Found");
        }
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
