package com.lq.redisdemo3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author qili
 * @create 2022-07-05-20:10
 */
@Alias("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -61273489027410L;

    private Long id;
    private String userName;
    private String note;
}
