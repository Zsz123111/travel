package com.itbaizhan.travel.bean;

import lombok.Data;

//带有状态的权限，状态表示角色是否拥有该权限
@Data
public class PermissionWithStatus {
    private Integer pid;
    private String permissionName; // 权限名
    private String permissionDesc;//权限详情
    private boolean roleHas;//角色是否拥有该权限
}
