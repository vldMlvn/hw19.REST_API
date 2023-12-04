package ua.dev13.hw19.base.service;

import ua.dev13.hw19.base.entity.Note;

public interface BaseNoteService {

    Object getAll();

    Object getById(Long id);

    Object create(Note note);

    Object update(Note note);

    Object deleteById(Long id);
}