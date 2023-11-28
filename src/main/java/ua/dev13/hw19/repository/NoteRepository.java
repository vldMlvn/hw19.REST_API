package ua.dev13.hw19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dev13.hw19.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
}
