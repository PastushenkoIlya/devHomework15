package com.example.devhomework15;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class NoteDto {
    private long id;
    private String title;
    private String content;

    public static NoteDto fromNote(Note note){
        return NoteDto
                .builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
    public static List<NoteDto> fromNote(Iterable<Note> notes){
        List<NoteDto> result = new ArrayList<>();

        for (Note note: notes) {
            result.add(NoteDto.fromNote(note));
        }
        return result;
    }
}
