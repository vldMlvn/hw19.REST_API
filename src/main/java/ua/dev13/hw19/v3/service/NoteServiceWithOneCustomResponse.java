package ua.dev13.hw19.v3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.dev13.hw19.base.service.BaseNoteService;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.base.repository.NoteRepository;
import ua.dev13.hw19.base.response.CustomResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceWithOneCustomResponse implements BaseNoteService {

    private final NoteRepository repository;

    @Override
    public ResponseEntity<CustomResponse<List<Note>>> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.NOT_FOUND));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomResponse
                        .success(notes));
    }

    @Override
    public ResponseEntity<CustomResponse<Note>> getById(Long id) {
        Optional<Note> note = repository.findById(id);
        if (!note.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.NOT_FOUND));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomResponse
                        .success(note.get()));
    }

    @Override
    public ResponseEntity<CustomResponse<Note>> create(Note note) {
        if (note.getTitle() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.INVALID_TITLE));
        }
        if (note.getContent() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.INVALID_CONTENT));
        }
        repository.save(note);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse
                        .success(note));
    }

    @Override
    public ResponseEntity<CustomResponse<Note>> update(Note note) {
        if (note.getTitle() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.INVALID_TITLE));
        }
        if (note.getContent() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.INVALID_CONTENT));
        }
        if (!repository.existsById(note.getId())) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.NOT_FOUND));
        }
        Note updatedNote = repository.save(note);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(CustomResponse
                        .success(updatedNote));
    }

    @Override
    public ResponseEntity<CustomResponse<Note>> deleteById(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomResponse
                            .failed(CustomResponse.Error.NOT_FOUND));

        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(CustomResponse
                        .failed(CustomResponse.Error.OK));
    }
}