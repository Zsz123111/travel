package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.mapper.ProductMapper;
import com.itbaizhan.travel.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    public ProductMapper productMapper;

    //查询用户是否收藏线路
    public boolean findFavorite(Integer pid,Integer mid){
        int result = productMapper.findFavoritePidAndMid(pid, mid);
        if (result == 0){
            return false;
        }else {
            return true;
        }
    }

    //收藏
    public void addFavorite(Integer pid,Integer mid){
        productMapper.addFavorite(pid, mid);
    }

    //取消收藏
    public void deleteFavorite(Integer pid,Integer mid){
        productMapper.deleteFavorite(pid, mid);
    }

    //查询我的收藏
    public Page<Product> findMemberFavorite(int page,int size,Integer mid){
        Page<Product> favoriteProduct = productMapper.findMemberFavorite(new Page<>(page, size), mid);
        return favoriteProduct;
    }
}
