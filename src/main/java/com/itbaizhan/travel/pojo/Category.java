package com.itbaizhan.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

// 旅游产品类型
@Data
public class Category{
    @TableId
    private Integer cid; //分类id
    private String cname; //分类名称
}
