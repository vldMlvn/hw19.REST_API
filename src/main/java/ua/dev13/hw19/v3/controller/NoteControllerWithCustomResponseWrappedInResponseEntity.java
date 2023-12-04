package ua.dev13.hw19.v3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dev13.hw19.base.controller.BaseNoteController;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.base.response.CustomResponse;
import ua.dev13.hw19.v3.service.NoteServiceWithCustomsResponseWrappedInResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/notes")
public class NoteControllerWithCustomResponseWrappedInResponseEntity implements BaseNoteController {

    private final NoteServiceWithCustomsResponseWrappedInResponseEntity service;

    @Override
    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<Note>>> getAllNotes() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Note>> getNoteById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Note>> createNote(@RequestBody Note note) {
        return service.create(note);
    }

    @Override
    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<Note>> editNoteById(@PathVariable Long id, @RequestBody Note note) {
        return service.update(note);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Note>> deleteNoteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
