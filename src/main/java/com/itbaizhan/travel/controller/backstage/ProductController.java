package com.itbaizhan.travel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.WangEditorResult;
import com.itbaizhan.travel.pojo.Category;
import com.itbaizhan.travel.pojo.Product;
import com.itbaizhan.travel.service.CategoryService;
import com.itbaizhan.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backstage/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public ModelAndView all(@RequestParam(defaultValue = "1")int page,
                            @RequestParam(defaultValue = "5")int size){
        Page<Product> productPage = productService.findPage(page, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productPage",productPage);
        modelAndView.setViewName("/backstage/product_all");
        return modelAndView;
    }
    @RequestMapping("/addPage")
    public ModelAndView addPage(){
        List<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryList",categories);
        modelAndView.setViewName("/backstage/product_add");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String add(Product product){
        productService.add(product);
        return "redirect:/backstage/product/all";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public WangEditorResult upload(HttpServletRequest request, MultipartFile file) throws IOException {
        //?????????????????????????????????
        //1.????????????????????????????????????
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/upload";
        //2.???????????????????????????????????????????????????????????????
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        //????????????????????????
        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID()+filename;
        //???????????????
        File newFile = new File(dir, filename);
        //????????????????????????????????????
        file.transferTo(newFile);
        //??????????????????
        WangEditorResult wangEditorResult = new WangEditorResult();
        wangEditorResult.setErrno(0);
        String[] data = {"/upload/"+filename};
        wangEditorResult.setData(data);
        return wangEditorResult;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer pid){
        Product product = productService.findOne(pid);
        List<Category> categoryList = categoryService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",product);
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("/backstage/product_edit");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product){
        if (product.getPImage().equals("")){
            product.setPImage(productService.findOne(product.getPid()).getPImage());
        }
        productService.update(product);
        return "redirect:/backstage/product/all";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer pid,@RequestHeader("Referer") String referer){
        productService.updateStatus(pid);
        return "redirect:"+referer;
    }
}
