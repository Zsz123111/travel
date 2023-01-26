package com.itbaizhan.travel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.pojo.Category;
import com.itbaizhan.travel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backstage/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public ModelAndView all(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        Page<Category> categoryPage = categoryService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryPage",categoryPage);
        modelAndView.setViewName("/backstage/category_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Category category){
        categoryService.add(category);
        return "redirect:/backstage/category/all";
    }
    @RequestMapping("/edit")
    public ModelAndView edit(Integer cid){
        Category category = categoryService.findById(cid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category",category);
        modelAndView.setViewName("/backstage/category_edit");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Category category){
        categoryService.update(category);
        return "redirect:/backstage/category/all";
    }
    @RequestMapping("/delete")
    public String delete(Integer cid){
        categoryService.delete(cid);
        return "redirect:/backstage/category/all";
    }
}
