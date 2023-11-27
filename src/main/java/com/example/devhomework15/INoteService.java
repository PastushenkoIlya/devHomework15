package com.example.devhomework15;

import java.util.List;

public interface INoteService {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(long id) throws Exception;
    void update(Note note);
    Note getById(long id) throws Exception;
}
