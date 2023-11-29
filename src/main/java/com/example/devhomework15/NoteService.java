package com.example.devhomework15;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService implements INoteService{
    private static List<Note> noteList = new ArrayList<>();
    //long noteCounter = 0;
    @Override
    public List<Note> listAll(){
        return noteList;
    }

    public Note add(Note note){
        noteList.add(note);
        return note;
    }
    public void deleteById(long id) throws Exception {
        Note noteFound = noteList.stream()
                .filter(note -> note.getId() == id).findAny().orElseThrow(() -> new Exception("Note not found: "+ id));
        noteList.remove(noteFound);
    }
    public void update(Note note) {
        int index = noteList.indexOf(note);
        noteList.set(index, note);
    }
    public Note getById(long id) throws Exception {
        return noteList.stream()
                .filter(note -> note.getId() == id).findAny().orElseThrow(() -> new Exception("Note not found: "+ id));
    }
}
