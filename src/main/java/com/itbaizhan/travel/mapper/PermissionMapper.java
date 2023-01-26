package com.itbaizhan.travel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.travel.pojo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    //查询角色拥有的所有权限id
    List<Integer> findPermissionIdByRole(Integer rid);
}
