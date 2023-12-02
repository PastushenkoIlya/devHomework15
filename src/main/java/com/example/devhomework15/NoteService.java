package com.example.devhomework15;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService implements INoteService{

    private final NoteRepository repository;

    public List<NoteDto> listAll(){
        return NoteDto.fromNote(repository.findAll());
    }
    public NoteDto add(NoteDto noteDto){
        //fix is needed   constructor has to be with no id field
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        repository.save(note);
        return noteDto;
    }
    public void deleteById(long id){
        repository.deleteById(id);
    }
    public void update(@Param("noteDto") NoteDto noteDto) throws NoSuchElementException{
        Optional<Note> note = repository.findById(noteDto.getId());
        if(note.isEmpty()) throw new NoSuchElementException();
        Note result = note.get();
        result.setTitle(noteDto.getTitle());
        result.setContent(noteDto.getContent());
        repository.save(result);
    }
    public NoteDto getById(long id) throws NoSuchElementException{
        Optional<Note>note =  repository.findById(id);
        if(note.isEmpty()) throw new NoSuchElementException();
        return NoteDto.fromNote(note.get());
    }
}
