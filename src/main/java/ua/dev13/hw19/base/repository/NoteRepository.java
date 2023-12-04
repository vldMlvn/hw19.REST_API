package ua.dev13.hw19.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dev13.hw19.base.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}