package com.itbaizhan.travel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.pojo.Admin;
import com.itbaizhan.travel.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    //查询用户详情
    Admin findDesc(Integer aid);

    //删除用户所有角色
    void deleteAdminAllRoles(Integer aid);
    //添加角色
    void addAdminRole(@Param("aid")Integer aid,@Param("rid")Integer rid);

    //根据用户名返回权限
    List<Permission> findAllPermission(String username);
}
