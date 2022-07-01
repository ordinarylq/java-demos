package com.lq.mybatisdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author qili
 * @create 2022-07-01-19:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias(value = "user")// 起别名
public class User {
    private Long id;
    private String userName;
    private SexEnum sex;
    private String note;
}
