package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.mapper.ProductMapper;
import com.itbaizhan.travel.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Page<Product> findPage(int page,int size){
        Page<Product> productPage = productMapper.findProductPage(new Page<>(page, size));
        return productPage;
    }
    public void add(Product product){
        productMapper.insert(product);
    }
    public Product findOne(int pid){
        return productMapper.findOne(pid);
    }
    public void update(Product product){
        productMapper.updateById(product);
    }
    public void updateStatus(Integer pid){
        Product product = productMapper.selectById(pid);
        product.setStatus(!product.getStatus());
        productMapper.updateById(product);
    }

    public Page<Product> findProduct(Integer cid,String productName,int page,int size){
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (cid!=null){
            queryWrapper.eq("cid",cid);
        }if (StringUtils.hasText(productName)){
            queryWrapper.like("productName",productName);
        }
        queryWrapper.eq("status",1);
        queryWrapper.orderByDesc("pid");
        Page<Product> selectPage = productMapper.selectPage(new Page<>(page, size), queryWrapper);
        return selectPage;
    }
}
