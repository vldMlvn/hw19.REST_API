package com.example.demo.controller;

import com.example.demo.base.controller.BaseNoteController;
import com.example.demo.base.entity.Note;
import com.example.demo.base.response.CustomResponse;
import com.example.demo.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/notes")
public class NoteController implements BaseNoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<Note>>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Note>> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Note>> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(note));
    }

    @Override
    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<Note>> editNoteById(@PathVariable Long id, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.updateNote(note));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Note>> deleteNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.deleteNote(id));
    }
}
