package com.itbaizhan.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Member {
    @TableId
    private Integer mid; // id
    private String username; // 用户名（昵称）
    private String password; // 密码
    private String email; // 邮箱
    private String phoneNum; // 手机号
    private String sex; //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday; //生日
    private boolean active; //是否激活
    private String activeCode;//激活码
}
