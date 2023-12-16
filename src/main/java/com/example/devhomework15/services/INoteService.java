package com.example.devhomework15.services;

import com.example.devhomework15.Note;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INoteService {
    ResponseEntity<List<Note>> listAll();
    ResponseEntity<Note> add(Note note);
    ResponseEntity<Note> deleteById(long id);
    ResponseEntity<Note> update(Note note);
    ResponseEntity<Note> getById(long id);
}
