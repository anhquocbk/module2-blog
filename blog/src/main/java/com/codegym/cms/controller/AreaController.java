package com.codegym.cms.controller;

import com.codegym.cms.model.Area;
import com.codegym.cms.model.Blog;
import com.codegym.cms.service.AreaService;
import com.codegym.cms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AreaController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/areas")
    public ModelAndView listArea(){
        Iterable<Area> areas = areaService.findAll();
        ModelAndView modelAndView = new ModelAndView("/area/list");
        modelAndView.addObject("areas",areas);
        return modelAndView;
    }
    @GetMapping("/create-area")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/area/create");
        modelAndView.addObject("area", new Area());
        return modelAndView;
    }
    @PostMapping("/create-area")
    public ModelAndView saveArea(@ModelAttribute("area")Area area){
        areaService.save(area);

        ModelAndView modelAndView = new ModelAndView("/area/create");
        modelAndView.addObject("area", new Area());
        modelAndView.addObject("message", "New area created");
        return modelAndView;
    }
    @GetMapping("/edit-area/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Area area = areaService.findById(id);
        if(area != null) {
            ModelAndView modelAndView = new ModelAndView("/area/edit");
            modelAndView.addObject("area", area);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-area")
    public ModelAndView updateArea(@ModelAttribute("area") Area area){
        areaService.save(area);
        ModelAndView modelAndView = new ModelAndView("/area/edit");
        modelAndView.addObject("area", area);
        modelAndView.addObject("message", "area updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-area/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Area area = areaService.findById(id);
        if(area != null) {
            ModelAndView modelAndView = new ModelAndView("/area/delete");
            modelAndView.addObject("area", area);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-area")
    public String deleteArea(@ModelAttribute("area") Area area){
        areaService.remove(area.getId());
        return "redirect:areas";
    }
    @GetMapping("/view-area/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Area area = areaService.findById(id);
        if(area == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Blog> blogs = blogService.findAllByArea(area);

        ModelAndView modelAndView = new ModelAndView("/area/view");
        modelAndView.addObject("area", area);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
