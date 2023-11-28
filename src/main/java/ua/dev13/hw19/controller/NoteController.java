package ua.dev13.hw19.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dev13.hw19.entity.Note;
import ua.dev13.hw19.service.NoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/note")
public class NoteController {
    private final NoteService service;

    @GetMapping("/list")
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = service.getAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id){
        Note note = service.getById(id);
        return new ResponseEntity<>(note,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        service.add(note);
        return new ResponseEntity<>(note,HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Note> editById(@PathVariable("id") Long id, @RequestBody Note note){
        note.setId(id);
        service.update(note);
        return new ResponseEntity<>(note,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
