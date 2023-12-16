package com.example.devhomework15.services;

import com.example.devhomework15.Note;
import com.example.devhomework15.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService implements INoteService {

    private final NoteRepository repository;

    @Override
    public ResponseEntity<List<Note>> listAll(){
        List<Note> notes = repository.findAll();
        if(notes.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notes);
    }
    @Override
    public ResponseEntity<Note> add(@RequestBody Note noteToAdd){
        if (repository.findById(noteToAdd.getId()).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        repository.save(noteToAdd);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteToAdd);
    }
    @Override
    public ResponseEntity<Note> deleteById(long id){
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }
    @Override
    public ResponseEntity<Note> update(@RequestBody Note noteFromUser){
        if(noteFromUser.getTitle() == null || noteFromUser.getTitle().trim().isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .build();
        if(noteFromUser.getContent() == null || noteFromUser.getContent().trim().isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .build();

        Optional<Note> note = repository.findById(noteFromUser.getId());
        if(note.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        repository.save(noteFromUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteFromUser);
    }
    @Override
    public ResponseEntity<Note> getById(long id){
        Optional<Note> result = repository.findById(id);
        if(result.isEmpty())
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result.get());
    }
}
