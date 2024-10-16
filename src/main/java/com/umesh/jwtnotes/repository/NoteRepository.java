package com.umesh.jwtnotes.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.umesh.jwtnotes.entity.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthor(String author);
}