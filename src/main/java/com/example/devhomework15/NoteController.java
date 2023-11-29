package com.example.devhomework15;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {
    private static final NoteService noteService = new NoteService();
    public NoteController() {
        noteService.add(new Note(1,"To do list", "task task task"));
        noteService.add(new Note(2,"Reminder", "do smth important"));
    }
    @GetMapping("/list")
    public ModelAndView getNoteList(){
        ModelAndView result = new ModelAndView("noteTemplate");
        result.addObject("noteList", noteService.listAll());
        return result;
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") Long id){
        ModelAndView result = new ModelAndView("editTemplate");
        result.addObject("id", id);

        return result;
    }
    @PostMapping(value = "/edit")
    public void edit(){

    }
    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(name = "deleteButton") Long id){
        try {
            noteService.deleteById(id);
        } catch (Exception e) {
            ModelAndView errorTemplate = new ModelAndView();
            errorTemplate.addObject("errorDescription", e);
            return errorTemplate;
        }
        ModelAndView result = new ModelAndView("noteTemplate");
        result.addObject("noteList", noteService.listAll());
        return result;
    }
}
