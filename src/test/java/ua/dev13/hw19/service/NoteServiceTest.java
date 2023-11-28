package ua.dev13.hw19.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.dev13.hw19.entity.Note;
import ua.dev13.hw19.repository.NoteRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoteServiceTest {

    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteService service;

    @Test
    void testReturnAllNotesWhenGetAllNotes() {
        // Given
        List<Note> expectedNotes = Arrays.asList(
                Note
                        .builder()
                        .id(1L)
                        .title("Title 1")
                        .content("Content 1")
                        .build(),
                Note
                        .builder()
                        .id(2L)
                        .title("Title 2")
                        .content("Content 2")
                        .build()
        );
        when(repository.findAll()).thenReturn(expectedNotes);

        // When
        List<Note> actualNotes = service.getAll();

        // Then
        assertEquals(expectedNotes, actualNotes);
    }

    @Test
    void testReturnNoteWhenGetNoteById() {
        // Given
        Long noteId = 1L;
        Note expectedNote = Note
                .builder()
                .id(noteId)
                .title("Title")
                .content("Contetn")
                .build();
        when(repository.findById(noteId)).thenReturn(Optional.of(expectedNote));

        // When
        Note actualNote = service.getById(noteId);

        // Then
        assertEquals(expectedNote, actualNote);
    }

    @Test
    void testReturnAddedNoteWhenAddNewNote() {
        // Given
        Note noteToAdd = Note
                .builder()
                .id(null)
                .title("New Title")
                .content("New Content")
                .build();
        Note expectedNoteAfterSave = Note
                .builder()
                .id(1L)
                .title("New Title")
                .content("New Content")
                .build();
        when(repository.save(noteToAdd)).thenReturn(expectedNoteAfterSave);

        // When
        Note addedNote = service.add(noteToAdd);

        // Then
        assertEquals(expectedNoteAfterSave, addedNote);
    }

    @Test
    void testSaveNoteWhenUpdateExistingNote() {
        // Given
        Note existingNote = Note
                .builder()
                .id(1L)
                .title("Existing Title")
                .content("Existing Content")
                .build();
        when(repository.existsById(existingNote.getId())).thenReturn(true);

        // When
        service.update(existingNote);

        // Then
        verify(repository, times(1)).save(existingNote);
    }

    @Test
    void testThrowExceptionWhenUpdateNonExistingNote() {
        // Given
        Note nonExistingNote = Note
                .builder()
                .id(1L)
                .title("Non-Existing Title")
                .content("Non-Existing Content")
                .build();
        when(repository.existsById(nonExistingNote.getId())).thenReturn(false);

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> service.update(nonExistingNote));
    }

    @Test
    void testDeleteNoteWhenDeleteNoteById() {
        // Given
        Long noteIdToDelete = 1L;

        // When
        service.deleteById(noteIdToDelete);

        // Then
        verify(repository, times(1)).deleteById(noteIdToDelete);
    }
}
