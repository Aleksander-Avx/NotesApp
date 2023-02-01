package com.avx.note.service;

import com.avx.note.entity.NotesEntity;
import com.avx.note.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;

    @Override
    public Iterable<NotesEntity> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public NotesEntity findById(Long id) {
        return notesRepository.findById(id).orElseThrow();
    }

    @Override
    public NotesEntity saveNotes(NotesEntity notesEntity) {
        return notesRepository.save(notesEntity);
    }

    @Override
    public void deleteById(Long id) {
        notesRepository.deleteById(id);
    }
}
