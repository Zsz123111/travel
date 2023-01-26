package com.itbaizhan.travel.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private String url;//访问路径
    private Date visitTime;//访问时间
    private String username;//访问者
    private String ip;//访问ip
    private int excutionTime;//访问时长
    private String exceptionMessage;//异常信息
}
