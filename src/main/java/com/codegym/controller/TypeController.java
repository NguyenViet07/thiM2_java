package com.codegym.controller;

import com.codegym.model.Type;
import com.codegym.service.ProductService;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TypeController {
    @Autowired
    ProductService productService;
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public ModelAndView listCountry(Pageable pageable){
        Iterable<Type> types = typeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/create-type")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }
    @PostMapping("/create-type")
    public ModelAndView saveType(@ModelAttribute("type") Type type){
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        modelAndView.addObject("message", "New country created successfully!");
        return modelAndView;
    }
    @GetMapping("/edit-type/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Type type = typeService.findById(id);
        if (type != null) {
            ModelAndView modelAndView = new ModelAndView("/type/edit");
            modelAndView.addObject("type", type);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-type")
    public ModelAndView updateType(@ModelAttribute("type") Type type){
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("/type/edit");
        modelAndView.addObject("type", type);
        modelAndView.addObject("message", "country updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-type/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Type type = typeService.findById(id);
        if(type != null) {
            ModelAndView modelAndView = new ModelAndView("/type/delete");
            modelAndView.addObject("type", type);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-type/{id}")
    public String deleteType(@PathVariable("id") long id){
        typeService.remove(id);
        return "redirect:/types";
    }

}
