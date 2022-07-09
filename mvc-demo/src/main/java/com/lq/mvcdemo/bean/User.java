package com.lq.mvcdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author qili
 * @create 2022-07-09-8:19
 */
@Alias("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -647923894230L;

    private Long id;
    private String userName;
    private String note;
}
