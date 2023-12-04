package ua.dev13.hw19.v2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dev13.hw19.base.service.BaseNoteService;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.base.repository.NoteRepository;
import ua.dev13.hw19.base.response.CustomResponse;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoteServiceWithCustomsResponses implements BaseNoteService {

    private final NoteRepository repository;

    @Override
    public CustomResponse<List<Note>> getAll() {
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return CustomResponse
                    .failed(CustomResponse.Error.NOT_FOUND);
        }
        return CustomResponse
                .success(notes);
    }

    @Override
    public CustomResponse<Note> getById(Long id) {
        Optional<Note> note = repository.findById(id);
        if (!note.isPresent()) {
            return CustomResponse
                    .failed(CustomResponse.Error.NOT_FOUND);
        } else {
            return CustomResponse
                    .success(note.get());
        }
    }

    @Override
    public CustomResponse<Note> create(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            return CustomResponse
                    .failed(CustomResponse.Error.INVALID_TITLE);
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            return CustomResponse
                    .failed(CustomResponse.Error.INVALID_CONTENT);
        }
        repository.save(note);
        return CustomResponse
                .success(note);
    }

    @Override
    public CustomResponse<Note> update(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            return CustomResponse
                    .failed(CustomResponse.Error.INVALID_TITLE);
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            return CustomResponse
                    .failed(CustomResponse.Error.INVALID_CONTENT);
        }
        if (!repository.existsById(note.getId())) {
            return CustomResponse
                    .failed(CustomResponse.Error.NOT_FOUND);
        }
        Note updatedNote = repository.save(note);
        return CustomResponse
                .success(updatedNote);
    }

    @Override
    public CustomResponse<Note> deleteById(Long id) {
        if (!repository.existsById(id)) {
            return CustomResponse
                    .failed(CustomResponse.Error.NOT_FOUND);
        }
        repository.deleteById(id);
        return CustomResponse
                .success(null);
    }
}