package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.mapper.PermissionMapper;
import com.itbaizhan.travel.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServive {
    @Autowired
    private PermissionMapper permissionMapper;

    public Page<Permission> findPage(int page,int size){
        Page<Permission> permissionPage = permissionMapper.selectPage(new Page(page, size), null);
        return permissionPage;
    }

    public void add(Permission permission){
        permissionMapper.insert(permission);
    }

    public Permission findById(Integer pid){
        return permissionMapper.selectById(pid);
    }

    public void update(Permission permission){
        permissionMapper.updateById(permission);
    }

    public void delete(Integer pid){
        permissionMapper.deleteById(pid);
    }
}
