package com.example.devhomework14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    @Test
    void listAll() {
        Note note1 = new Note();
        note1.setId(5);
        note1.setTitle("Daily notes");
        note1.setContent("buy list: something1, something2");

        Note note2 = new Note();
        note2.setId(8);
        note2.setTitle("Daily notes2");
        note2.setContent("buy list: something1, something2");

        NoteService noteService = new NoteService();
        noteService.add(note1);
        noteService.add(note2);


        assertEquals(List.of(note1, note2), noteService.listAll());
    }

    @Test
    void add() {
        Note note = new Note();
        note.setId(5);
        note.setTitle("Daily notes");
        note.setContent("buy list: something1, something2");

        NoteService noteService = new NoteService();
        noteService.add(note);

        assertEquals(List.of(note), noteService.listAll());
    }

    @Test
    void deleteById() {
        Note note = new Note();
        note.setId(5);
        note.setTitle("Daily notes");
        note.setContent("buy list: something1, something2");

        NoteService noteService = new NoteService();
        noteService.add(note);

        try {
            noteService.deleteById(note.getId());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void update() {
        Note note = new Note();
        note.setId(5);
        note.setTitle("Daily notes");
        note.setContent("buy list: something1, something2");

        NoteService noteService = new NoteService();
        noteService.add(note);

        note.setTitle("Another note");
        note.setContent("important task");

        noteService.update(note);
        try {
            assertEquals(note, noteService.getById(note.getId()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getById() {
        Note note = new Note();
        note.setId(5);
        note.setTitle("Daily notes");
        note.setContent("buy list: something1, something2");

        NoteService noteService = new NoteService();
        noteService.add(note);

        try {
            assertEquals(note, noteService.getById(note.getId()));
        } catch (Exception e) {
            fail();
        }
    }
}