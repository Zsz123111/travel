package com.itbaizhan.travel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.RoleWithStatus;
import com.itbaizhan.travel.mapper.AdminMapper;
import com.itbaizhan.travel.mapper.RoleMapper;
import com.itbaizhan.travel.pojo.Admin;
import com.itbaizhan.travel.pojo.Permission;
import com.itbaizhan.travel.pojo.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;
    public Page<Admin> findPage(int page,int size){
        Page selectPage = adminMapper.selectPage(new Page(page, size), null);
        return selectPage;
    }

    public void add(Admin admin){
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
    }

    //根据id查询管理员
    public Admin findById(Integer aid){
        return adminMapper.selectById(aid);
    }
    //修改管理员
    public void update(Admin admin){
        String oldPassword = adminMapper.selectById(admin.getAid()).getPassword();
        String newPassword = admin.getPassword();
        if (!oldPassword.equals(newPassword)){
            admin.setPassword(encoder.encode(admin.getPassword()));
        }
        adminMapper.updateById(admin);
    }
    //查询用户详情
    public Admin findDesc(Integer aid){
        return adminMapper.findDesc(aid);
    }
    public List<RoleWithStatus> findRole(Integer aid){
        //查询所有角色
        List<Role> roles = roleMapper.selectList(null);
        //查询用户拥有的所有角色
        List<Integer> rids = roleMapper.findRoleIdByAdmin(aid);
        //构建带有状态的角色集合，拥有拥有状态为true
        List<RoleWithStatus> roleList = new ArrayList<>();
        for (Role role:roles){
            RoleWithStatus roleWithStatus = new RoleWithStatus();
            //复制对象属性的
            BeanUtils.copyProperties(role,roleWithStatus);
            if (rids.contains(role.getRid())){
                roleWithStatus.setAdminHas(true);
            }else {
                roleWithStatus.setAdminHas(false);
            }
            roleList.add(roleWithStatus);
        }
        return roleList;
    }
    //修改用户角色
    public void updateRole(Integer aid,Integer[] ids){
        //删除用户所有角色
        adminMapper.deleteAdminAllRoles(aid);
        //重新添加角色
        for (Integer rid:ids)
        adminMapper.addAdminRole(aid,rid);
    }

    //修改管理员状态
    public void updateStatus(Integer aid){
        Admin admin = adminMapper.selectById(aid);
        admin.setStatus(!admin.isStatus());//状态取反
        adminMapper.updateById(admin);
    }

    //根据名字查询管理员
    public Admin findByAdminName(String username){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(wrapper);
        return admin;
    }

    //根据名字查询权限
    public List<Permission> findAllPermission(String username){
        return adminMapper.findAllPermission(username);
    }
}
