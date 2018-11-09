package com.iNotes.repository;

import com.iNotes.model.Note;
import com.iNotes.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Iterable<Note> findAllByType(Type type);

    Page<Note> findAllByTitleContaining(String title, Pageable pageable);
}
