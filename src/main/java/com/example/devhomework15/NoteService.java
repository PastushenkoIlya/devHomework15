package com.example.devhomework15;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService implements INoteService{
    List<Note> notesMap= new ArrayList<>();
    //long noteCounter = 0;
    @Override
    public List<Note> listAll(){
        return notesMap;
    }

    public Note add(Note note){
        //note.setId(++noteCounter);
        notesMap.add(note);
        return note;
    }
    public void deleteById(long id) throws Exception {
        Note noteFound = notesMap.stream()
                .filter(note -> note.getId() == id).findAny().orElseThrow(() -> new Exception("Note not found: "+ id));
        notesMap.remove(noteFound);
    }
    public void update(Note note) {
        int index = notesMap.indexOf(note);
        notesMap.set(index, note);
    }
    public Note getById(long id) throws Exception {
        return notesMap.stream()
                .filter(note -> note.getId() == id).findAny().orElseThrow(() -> new Exception("Note not found: "+ id));
    }
}
