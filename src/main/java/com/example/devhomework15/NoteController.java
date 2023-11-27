package com.example.devhomework15;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    @GetMapping("/test")
    public ModelAndView getText(){
        return new ModelAndView("test");
    }
}
