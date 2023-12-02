package com.example.devhomework15;

import java.util.List;
import java.util.NoSuchElementException;

public interface INoteService {
    List<NoteDto> listAll();
    NoteDto add(NoteDto noteDto);
    void deleteById(long id);
    void update(NoteDto noteDto);
    NoteDto getById(long id) throws NoSuchElementException;
}
