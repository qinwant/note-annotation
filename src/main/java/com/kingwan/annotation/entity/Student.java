package com.kingwan.annotation.entity;

import com.kingwan.annotation.annotation.KingwanColumn;
import com.kingwan.annotation.annotation.KingwanTable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：
 */
@Data
@KingwanTable("t_student")
public class Student {
    @KingwanColumn("stu_name")
    private String stuName;
    @KingwanColumn("stu_age")
    private Integer stuAge;
    @KingwanColumn("stu_birth")
    private Date stuBirth;

    @Value("12")
    private String password;


}
