package com.itbaizhan.travel.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 旅游产品
@Data
public class Product {
    @TableId
    private Integer pid;// id
    private String productName;// 产品名称
    private BigDecimal price;// 价格
    private String hotline;// 热线电话
    private Boolean status; // 产品状态  false:关闭  true:开启
    private String productDesc; //产品详情
    private String pImage; // 产品图片
    private Integer cid; // 产品类型id
    @TableField(exist = false)
    private Category category; // 产品类型

}
