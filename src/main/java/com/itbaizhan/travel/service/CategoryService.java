package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.mapper.CategoryMapper;
import com.itbaizhan.travel.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public Page<Category> findPage(int page,int size){
        return categoryMapper.selectPage(new Page<>(page,size),null);
    }

    public void add(Category category){
        categoryMapper.insert(category);
    }

    public Category findById(Integer cid){
        return categoryMapper.selectById(cid);
    }

    public void update(Category category){
        categoryMapper.updateById(category);
    }

    public void delete(Integer cid){
        categoryMapper.deleteById(cid);
    }

    public List<Category> findAll(){
        return categoryMapper.selectList(null);
    }
}
