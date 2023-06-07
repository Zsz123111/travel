package com.itbaizhan.travel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.travel.bean.RoleWithStatus;
import com.itbaizhan.travel.mapper.AdminMapper;
import com.itbaizhan.travel.mapper.ProductMapper;
import com.itbaizhan.travel.pojo.Admin;
import com.itbaizhan.travel.pojo.Product;
import com.itbaizhan.travel.service.AdminService;
import com.itbaizhan.travel.util.MailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TravelApplicationTests {

//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private AdminMapper adminMapper;
//    @Autowired
//    private ProductMapper productMapper;
    @Autowired
    private MailUtils mailUtils;
    @Test
    void contextLoads() {
//        Page<Admin> page = adminService.findPage(1, 5);
//        System.out.println(page);
//        Admin desc = adminMapper.findDesc(1);
//        System.out.println(desc);
//        List<RoleWithStatus> roles = adminService.findRole(1);
//        Page<Product> productPage = productMapper.findProductPage(new Page<>(1, 5));
//        System.out.println(productPage);
        mailUtils.sendMail("xxx@qq.com","这是一封测试邮件","测试");
    }

}
