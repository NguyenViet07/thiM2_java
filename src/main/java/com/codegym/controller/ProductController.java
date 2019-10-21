package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Type;
import com.codegym.service.ProductService;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    TypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> types(){
        return typeService.findAll();
    }

    @GetMapping("/products")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        Page<Product> products;
        if(s.isPresent()){
            products = productService.findAllByTitleContaining(s.get(), pageable);
            modelAndView.addObject("titleSearch", s.get());
        } else {
            products = productService.findAll(pageable);
        }
        List<Integer> productPages = productService.getNumberPage(products);
        modelAndView.addObject("products", products);
        modelAndView.addObject("productPages", productPages);

        if (request.getParameter("message")!= null){
            modelAndView.addObject("message",request.getParameter("message"));
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:products";
    }

    @GetMapping("view-product/{id}")
    public ModelAndView viewType(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        if (product!=null){
            ModelAndView modelAndView=new ModelAndView("/product/view");
            modelAndView.addObject("product", product);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("/error.404");
            return modelAndView;
        }
    }
}
