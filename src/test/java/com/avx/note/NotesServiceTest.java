package com.avx.note;

import com.avx.note.entity.NotesEntity;
import com.avx.note.repository.NotesRepository;
import com.avx.note.service.NotesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    private static final long ID = 1L;
    @Mock
    private NotesRepository notesRepository;

    @InjectMocks
    private NotesServiceImpl notesService;

    @Test
    public void findByIdNotesTest() {
        final NotesEntity notes = mock(NotesEntity.class);

        when(notesRepository.findById(ID)).thenReturn(Optional.ofNullable(notes));

        final NotesEntity actual = notesService.findById(ID);

        assertNotNull(actual);
        assertEquals(notes, actual);
        verify(notesRepository).findById(ID);
    }

    @Test
    public void saveNotes() {
        final NotesEntity notes = mock(NotesEntity.class);

        notesService.saveNotes(notes);

        verify(notesRepository).save(notes);
    }
}

