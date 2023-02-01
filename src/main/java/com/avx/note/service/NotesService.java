package com.avx.note.service;

import com.avx.note.entity.NotesEntity;

public interface NotesService {

    Iterable<NotesEntity> findAll();

    NotesEntity findById(Long id);

    NotesEntity saveNotes(NotesEntity notesEntity);

    void deleteById(Long id);
}
