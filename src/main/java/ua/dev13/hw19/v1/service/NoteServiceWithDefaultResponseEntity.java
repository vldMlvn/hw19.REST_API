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
public class NoteServiceWithDefaultResponseEntity implements BaseNoteService {

    private final NoteRepository repository;

    @Override
    public ResponseEntity<List<Note>> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notes);
    }

    @Override
    public ResponseEntity<Note> getById(Long id) {
        Optional<Note> note = repository.findById(id);
        if (!note.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(note.get());
        }
    }

    @Override
    public ResponseEntity<Note> create(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        repository.save(note);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(note);
    }

    @Override
    public ResponseEntity<Note> update(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if (!repository.existsById(note.getId())) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Note updatedNote = repository.save(note);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(updatedNote);
    }

    @Override
    public ResponseEntity<Note> deleteById(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}