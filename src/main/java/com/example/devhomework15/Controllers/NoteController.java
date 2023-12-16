package com.example.devhomework15.Controllers;

import com.example.devhomework15.Note;
import com.example.devhomework15.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//http://localhost:8080/note
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    //http://localhost:8080/note/list
    @GetMapping("/list")
    public ResponseEntity<List<Note>> getNoteList(){
        return noteService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id){
        return noteService.getById(id);
    }
    //http://localhost:8080/note/edit
    @PostMapping(value = "edit", consumes="application/json")
    public ResponseEntity<Note> edit(@RequestBody Note note){
        return noteService.update(note);
    }

    //http://localhost:8080/note/delete/id
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Note> delete(@PathVariable long id){
        return noteService.deleteById(id);
    }
    ////http://localhost:8080/note/create
    @PostMapping(value = "create", consumes="application/json")
    public ResponseEntity<Note> create(@RequestBody Note note){
        return noteService.add(note);
    }
}
