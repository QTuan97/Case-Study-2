package com.iNotes.controller;

import com.iNotes.model.Type;
import com.iNotes.service.NoteService;
import com.iNotes.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class TypeController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public ModelAndView listCategory(){
        Iterable<Type> types = typeService.findAll();
        ModelAndView list = new ModelAndView("/type/list");
        list.addObject("types", types);
        return list;
    }

    @GetMapping("/create-type")
    public ModelAndView showCreateCategory(){
        ModelAndView create = new ModelAndView("/type/create");
        create.addObject("types", new Type());
        return create;
    }
    @PostMapping("/create-type")
    public ModelAndView saveNewCategory(@ModelAttribute("type") Type type){
        typeService.save(type);

        ModelAndView save = new ModelAndView("/type/create");
        save.addObject("types", new Type());
        save.addObject("message", "New type created successfully!");
        return save;
    }
    @GetMapping("/edit-type/{id}")
    public ModelAndView showEditCategory(@PathVariable Long id){
        Type type = typeService.findById(id);
        if(type != null){
            ModelAndView edit = new ModelAndView("/type/edit");
            edit.addObject("type", type);
            return edit;
        }
        else{
            ModelAndView showError = new ModelAndView();
            return showError;
        }
    }
    @PostMapping("/edit-type")
    public ModelAndView updateCategory(@ModelAttribute("type") Type type){
        typeService.save(type);
        ModelAndView update = new ModelAndView("/type/edit");
        update.addObject("type", type);
        update.addObject("message", "Updated successfully!");
        return update;
    }
    @GetMapping("/delete-type/{id}")
    public ModelAndView showDeleteCategory(@PathVariable Long id){
        Type type = typeService.findById(id);
        if(type != null){
            ModelAndView delete = new ModelAndView("/type/delete");
            delete.addObject("type", type);
            return delete;
        }else{
            ModelAndView showError = new ModelAndView("/type/error-404");
            return showError;
        }
    }
    @PostMapping("/delete-type")
    public String deleteCategory(@ModelAttribute("type") Type type){
        Iterable types = noteService.findAllByType(type);
        ArrayList<Object> noteList = new ArrayList<>();
        for(Object b : types){
            noteList.add(b);
        }
        if(noteList.size() > 0){
            return "/type/error-404";
        }
        typeService.remove(type.getId());
        return "redirect:types";
    }
}
