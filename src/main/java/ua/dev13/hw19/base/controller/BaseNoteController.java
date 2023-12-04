package ua.dev13.hw19.base.controller;

import ua.dev13.hw19.base.entity.Note;

public interface BaseNoteController {

    Object getAllNotes();

    Object getNoteById(Long id);

    Object createNote(Note note);

    Object editNoteById(Long id, Note note);

    Object deleteNoteById(Long id);
}