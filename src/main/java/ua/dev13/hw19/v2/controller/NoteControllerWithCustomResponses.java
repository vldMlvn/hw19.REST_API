package ua.dev13.hw19.v2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.dev13.hw19.base.controller.BaseNoteController;
import ua.dev13.hw19.base.entity.Note;
import ua.dev13.hw19.base.response.CustomResponse;
import ua.dev13.hw19.v2.service.NoteServiceWithCustomsResponses;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/notes")
public class NoteControllerWithCustomResponses implements BaseNoteController {

    private final NoteServiceWithCustomsResponses service;

    @Override
    @GetMapping("/list")
    public CustomResponse<List<Note>> getAllNotes() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public CustomResponse<Note> getNoteById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @Override
    @PostMapping("/add")
    public CustomResponse<Note> createNote(@RequestBody Note note) {
        return service.create(note);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CustomResponse<Note> editNoteById(@PathVariable("id") Long id, @RequestBody Note note) {
        note.setId(id);
        return service.update(note);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public CustomResponse<Note> deleteNoteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}
