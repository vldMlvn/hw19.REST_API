package ua.dev13.hw19.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.dev13.hw19.base.entity.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByTitleContainingIgnoreCase(String title);

    List<Note> findByContentContainingIgnoreCase(String content);

    Page<Note> findAll(Pageable pageable);

    @Query("SELECT n FROM Note n WHERE n.title LIKE %:searchTerm% " +
            "OR n.content LIKE %:searchTerm%")
    List<Note> search(String searchTerm);
}
