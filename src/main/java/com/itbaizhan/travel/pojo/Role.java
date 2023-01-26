package com.itbaizhan.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

// 角色
@Data
public class Role {
    @TableId
    private Integer rid;
    private String roleName; // 角色名
    private String roleDesc; // 角色介绍
    @TableField(exist = false) // 不是数据库的字段
    private List<Permission> permissions;// 权限集合
}
