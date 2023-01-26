package com.itbaizhan.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

// 权限
@Data
public class Permission {
    @TableId
    private Integer pid;
    private String permissionName; // 权限名
    private String permissionDesc;//权限详情
}
