package com.iNotes.service.impl;

import com.iNotes.model.Note;
import com.iNotes.model.Type;
import com.iNotes.repository.NoteRepository;
import com.iNotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAll(Pageable pageable){
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findbyId(Long id){
        return noteRepository.findOne(id);
    }

    @Override
    public void save(Note note){
        noteRepository.save(note);
    }

    @Override
    public void remove(Long id){
        noteRepository.delete(id);
    }
    @Override
    public Iterable<Note> findAllByType(Type type){
        return noteRepository.findAllByType(type);
    }
    @Override
    public Page<Note> findAllByTitleContaining(String title, Pageable pageable){
        return noteRepository.findAllByTitleContaining(title,pageable);
    }
}
