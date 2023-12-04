package ua.dev13.hw19.v1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dev13.hw19.base.controller.BaseNoteController;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.v1.service.NoteServiceWithDefaultResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notes")
public class NoteControllerWithDefaultResponseEntity implements BaseNoteController {

    private final NoteServiceWithDefaultResponseEntity service;

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<Note>> getAllNotes() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return service.create(note);
    }

    @Override
    @PutMapping("/edit/{id}")
    public ResponseEntity<Note> editNoteById(@PathVariable Long id, @RequestBody Note note) {
        return service.update(note);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}