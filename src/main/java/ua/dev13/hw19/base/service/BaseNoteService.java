package ua.dev13.hw19.base.service;

import ua.dev13.hw19.base.entity.Note;
import java.util.List;
import java.util.Optional;

public interface BaseNoteService<T extends Note> {

    List<T> getAll();

    Optional<T> getById(Long id);

    T create(T note);

    T update(T note);

    void deleteById(Long id);
}
