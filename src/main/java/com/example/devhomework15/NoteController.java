package com.example.devhomework15;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/note")
public class NoteController {
    private static final NoteService noteService = new NoteService();
    public NoteController() {
        noteService.add(new Note(1,"To do list", "task task task"));

        noteService.add(new Note(2,"Reminder", "do something important"));
    }
    @GetMapping("/list")
    public ModelAndView getNoteList(){
        ModelAndView result = new ModelAndView("noteTemplate");
        result.addObject("noteList", noteService.listAll());
        return result;
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") long id){
        Note note;
        try {
            note = noteService.getById(id);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        ModelAndView result = new ModelAndView("editTemplate");
        result.addObject("id", id);
        result.addObject("noteEdited", note);
        result.addObject("formCompleted", true);
        return result;
    }
    @PostMapping(value = "/edit")
    public ModelAndView edit(@ModelAttribute("note") Note note){
        //save changed fields
        noteService.update(note);
        //redirect to the list of notes
        return getNoteList();
    }
    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(name = "deleteButton") Long id){
        try {
            noteService.deleteById(id);
        } catch (Exception e) {
            //output error
            ModelAndView errorTemplate = new ModelAndView();
            errorTemplate.addObject("errorDescription", e);
            return errorTemplate;
        }
        ModelAndView result = new ModelAndView("noteTemplate");
        result.addObject("noteList", noteService.listAll());
        return result;
    }
}
