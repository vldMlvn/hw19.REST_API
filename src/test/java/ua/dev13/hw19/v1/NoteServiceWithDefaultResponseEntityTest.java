package ua.dev13.hw19.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ua.dev13.hw19.base.repository.NoteRepository;
import ua.dev13.hw19.v1.service.NoteServiceWithDefaultResponseEntity;
import ua.dev13.hw19.base.entity.Note;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class NoteServiceWithDefaultResponseEntityTest {

    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteServiceWithDefaultResponseEntity service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllShouldReturnAllNotes() {
        // Given
        List<Note> expectedNotes = Arrays.asList(
                new Note(1L, "Title 1", "Content 1"),
                new Note(2L, "Title 2", "Content 2")
        );
        when(repository.findAll()).thenReturn(expectedNotes);

        // When
        ResponseEntity<List<Note>> response = service.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNotes, response.getBody());
    }

    @Test
    void getAllShouldReturnNotFoundWhenNoNotesExist() {
        // Given
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<Note>> response = service.getAll();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getByIdShouldReturnNoteWhenExists() {
        // Given
        Long noteId = 1L;
        Note expectedNote = new Note(noteId, "Title", "Content");
        when(repository.findById(noteId)).thenReturn(Optional.of(expectedNote));

        // When
        ResponseEntity<Note> response = service.getById(noteId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNote, response.getBody());
    }

    @Test
    void getByIdShouldReturnNotFoundWhenNoteDoesNotExist() {
        // Given
        Long noteId = 1L;
        when(repository.findById(noteId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Note> response = service.getById(noteId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createShouldReturnCreatedNoteWhenValidInput() {
        // Given
        Note noteToCreate = new Note(null, "New Title", "New Content");
        Note expectedNoteAfterSave = new Note(1L, "New Title", "New Content");
        when(repository.save(noteToCreate)).thenReturn(expectedNoteAfterSave);

        // When
        ResponseEntity<Note> response = service.create(noteToCreate);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createShouldReturnBadRequestWhenTitleIsInvalid() {
        // Given
        Note noteToCreate = new Note(null, null, "New Content");

        // When
        ResponseEntity<Note> response = service.create(noteToCreate);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void createShouldReturnBadRequestWhenContentIsInvalid() {
        // Given
        Note noteToCreate = new Note(null, "New Title", null);

        // When
        ResponseEntity<Note> response = service.create(noteToCreate);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateShouldReturnAcceptedWhenValidInput() {
        // Given
        Note existingNote = new Note(1L, "Existing Title", "Existing Content");
        when(repository.existsById(existingNote.getId())).thenReturn(true);

        // When
        ResponseEntity<Note> response = service.update(existingNote);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void updateShouldReturnNotFoundWhenTitleNotValid() {
        // Given
        Note nonExistingNote = new Note(1L, " ", "New Content");
        when(repository.existsById(nonExistingNote.getId())).thenReturn(true);

        // When
        ResponseEntity<Note> response = service.update(nonExistingNote);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateShouldReturnNotFoundWhenContentNotValid() {
        // Given
        Note nonExistingNote = new Note(1L, "New Title", " ");
        when(repository.existsById(nonExistingNote.getId())).thenReturn(true);

        // When
        ResponseEntity<Note> response = service.update(nonExistingNote);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateShouldReturnNotFoundWhenNoteDoesNotExist() {
        // Given
        Note nonExistingNote = new Note(1L, "Non-Existing Title", "Non-Existing Content");
        when(repository.existsById(nonExistingNote.getId())).thenReturn(false);

        // When
        ResponseEntity<Note> response = service.update(nonExistingNote);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteByIdShouldReturnNotFoundWhenNoteDoesNotExist() {
        // Given
        Long nonExistingNoteId = 1L;
        when(repository.existsById(nonExistingNoteId)).thenReturn(false);

        // When
        ResponseEntity<Note> response = service.deleteById(nonExistingNoteId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void deleteByIdShouldReturnNotFoundWhenNote() {
        // Given
        Long nonExistingNoteId = 1L;
        when(repository.existsById(nonExistingNoteId)).thenReturn(false);

        // When
        ResponseEntity<Note> response = service.deleteById(nonExistingNoteId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}
