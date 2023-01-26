package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.PermissionWithStatus;
import com.itbaizhan.travel.mapper.PermissionMapper;
import com.itbaizhan.travel.mapper.RoleMapper;
import com.itbaizhan.travel.pojo.Permission;
import com.itbaizhan.travel.pojo.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public Page<Role> findPage(int page,int size){
        return roleMapper.selectPage(new Page(page,size),null);
    }

    public void add(Role role){
        roleMapper.insert(role);
    }

    public Role findById(Integer rid){
        Role role = roleMapper.selectById(rid);
        return role;
    }
    public void update(Role role){
        roleMapper.updateById(role);
    }
    public void delete(Integer rid){
        roleMapper.deleteById(rid);
    }

    //查询角色权限情况
    public List<PermissionWithStatus> findPermission(Integer rid){
        //查询所有权限
        List<Permission> permissions = permissionMapper.selectList(null);
        //查询角色拥有的权限id
        List<Integer> pids = permissionMapper.findPermissionIdByRole(rid);
        //构建带状态的权限集合
        List<PermissionWithStatus> permissionList = new ArrayList<>();
        for (Permission permission:permissions){
            PermissionWithStatus permissionWithStatus = new PermissionWithStatus();
            BeanUtils.copyProperties(permission,permissionWithStatus);
            if (pids.contains(permission.getPid())){
                permissionWithStatus.setRoleHas(true);
            }else {
                permissionWithStatus.setRoleHas(false);
            }
            permissionList.add(permissionWithStatus);
        }
        return permissionList;
    }

    //给角色重新分配权限
    public void updatePermission(Integer rid,Integer[] ids){
        roleMapper.deleteRoleAllPermission(rid);
//        int a=1/0;
        for (Integer pid:ids){
            roleMapper.addRolePermission(rid,pid);
        }
    }
}
