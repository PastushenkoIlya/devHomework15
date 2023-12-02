package com.example.devhomework15;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    @GetMapping("/list")
    public ModelAndView getNoteList(){
        ModelAndView result = new ModelAndView("noteTemplate");
        result.addObject("noteList", noteService.listAll());
        return result;
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") long id){
        NoteDto noteDto;
        try {
            noteDto = noteService.getById(id);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        ModelAndView result = new ModelAndView("editTemplate");
        result.addObject("id", id);
        result.addObject("noteEdited", noteDto);
        return result;
    }
    @PostMapping(value = "/edit")
    public ModelAndView edit(@ModelAttribute("note") NoteDto noteDto){
        //save changed fields
        noteService.update(noteDto);
        //redirect to the list of notes
        return getNoteList();
    }
    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(name = "id") long id){
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
