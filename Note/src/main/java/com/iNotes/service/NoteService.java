package com.iNotes.service;

import com.iNotes.model.Note;
import com.iNotes.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Page<Note> findAll(Pageable pageable);
    Note findbyId(Long id);
    void save(Note note);
    void remove(Long id);
    Iterable<Note> findAllByType(Type type);
    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
