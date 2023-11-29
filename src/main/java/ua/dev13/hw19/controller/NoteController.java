package ua.dev13.hw19.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dev13.hw19.entity.Note;
import ua.dev13.hw19.controller.response.CreateAndUpdateNoteResponse;
import ua.dev13.hw19.controller.response.GetAllNotesResponse;
import ua.dev13.hw19.controller.response.GetNoteByIdResponse;
import ua.dev13.hw19.service.NoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteService service;

    @GetMapping("/list")
    public GetAllNotesResponse getAllNotes(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetNoteByIdResponse getNoteById(@PathVariable("id") Long id) {
          return service.getById(id);
    }

    @PostMapping("/add")
    public CreateAndUpdateNoteResponse createNote(@RequestBody Note note){
        return service.create(note);
    }

    @PutMapping("/edit/{id}")
    public CreateAndUpdateNoteResponse editById(@PathVariable("id") Long id, @RequestBody Note note){
        note.setId(id);
        return service.update(note);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
