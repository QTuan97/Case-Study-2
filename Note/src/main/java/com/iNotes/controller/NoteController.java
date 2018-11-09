package com.iNotes.controller;

import com.iNotes.model.Note;
import com.iNotes.model.Type;
import com.iNotes.service.NoteService;
import com.iNotes.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private NoteService noteService;

    @ModelAttribute("types")
    public Iterable<Type> types(){
        return typeService.findAll();
    }
    @GetMapping("/notes")
    public ModelAndView listBlogs(@RequestParam("notes") Optional<String> b, Pageable pageable){
        Page<Note> notes;
        if(b.isPresent()){
            notes = noteService.findAllByTitleContaining(b.get(), pageable);
        }else{
            notes = noteService.findAll(pageable);
        }
        ModelAndView list = new ModelAndView("/note/list");
        list.addObject("notes", notes);
        return list;
    }
    @GetMapping("/create-note")
    public ModelAndView showCreateNote(){
        ModelAndView showCreate = new ModelAndView("/note/create");
        showCreate.addObject("notes", new Note());
        return showCreate;
    }
    @PostMapping("/create-note")
    public ModelAndView createBlog(@ModelAttribute("notes") Note note){
        noteService.save(note);
        ModelAndView createBlog = new ModelAndView("/note/create");
        createBlog.addObject("notes", new Note());
        createBlog.addObject("message", "Create successfully!");
        return createBlog;
    }
    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditBlog(@PathVariable Long id){
        Note note = noteService.findbyId(id);
        if(note != null){
            ModelAndView editForm = new ModelAndView("/note/edit");
            editForm.addObject("notes", note);
            return editForm;
        }else{
            ModelAndView error = new ModelAndView();
            return error;
        }
    }
    @PostMapping("/edit-note")
    public ModelAndView updateBlog(@ModelAttribute("blog") Note note){
        noteService.save(note);
        ModelAndView update = new ModelAndView("/note/edit");
        update.addObject("notes", note);
        update.addObject("message", "Update successfully!");
        return update;
    }
    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteBlog(@PathVariable Long id){
        Note note = noteService.findbyId(id);
        if(note != null){
            ModelAndView delete = new ModelAndView("/note/delete");
            delete.addObject("notes", note);
            return delete;
        }else {
            ModelAndView error = new ModelAndView();
            return error;
        }
    }
    @PostMapping("/delete-note")
    public String deleteBlog(@ModelAttribute("note") Note note){
        noteService.remove(note.getId());
        return "redirect:notes";
    }
}
