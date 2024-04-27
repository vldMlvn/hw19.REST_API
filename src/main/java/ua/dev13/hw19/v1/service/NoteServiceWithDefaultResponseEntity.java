package ua.dev13.hw19.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.dev13.hw19.base.service.BaseNoteService;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.base.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService implements BaseNoteService {

    private final NoteRepository repository;

    @Override
    public ResponseEntity<List<Note>> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Note> getById(Long id) {
        Optional<Note> note = repository.findById(id);
        if (!note.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Note> create(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty() ||
                note.getContent() == null || note.getContent().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.save(note);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Note> update(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty() ||
                note.getContent() == null || note.getContent().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!repository.existsById(note.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Note updatedNote = repository.save(note);
        return new ResponseEntity<>(updatedNote, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Note> deleteById(Long id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
