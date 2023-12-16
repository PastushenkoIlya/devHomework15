package com.example.devhomework15;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="note")
public class Note{
    public Note(){}
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;
}
