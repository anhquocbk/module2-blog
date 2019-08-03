package com.codegym.cms.controller;

import com.codegym.cms.model.Area;
import com.codegym.cms.model.Blog;
import com.codegym.cms.service.AreaService;
import com.codegym.cms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AreaService areaService;

    @ModelAttribute("areas")
    public Iterable<Area> areas(){
        return areaService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView listBlog(@RequestParam("s") Optional<String>s,@PageableDefault(size = 5)  Pageable pageable){
        Page<Blog> blogs;
        if (s.isPresent()){
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
    @GetMapping("/create-blog")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "Blog created ");
        return modelAndView;
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:blogs";
    }
}
