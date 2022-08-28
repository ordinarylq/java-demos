package com.lq.mvcdemo.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author qili
 * @create 2022-07-12-20:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatorPojo {

    // 非空判断
    @NotNull(message = "id不能为空")
    private Long id;

    @Future(message = "需要一个将来的日期")
    // @Past 只能是过去的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式转换
    @NotNull // 不能为空
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1") // 最小值
    @DecimalMax(value = "10000.00") // 最大值
    private Double doubleValue;

    @NotNull
    @Min(value = 1, message = "最小值为1")
    @Max(value = 888, message = "最大值为88")
    private Integer integerValue;

    @Range(min = 1, max = 888, message = "范围是1-888")
    private Long range;

    @Email(message = "邮箱格式有误")
    private String email;

    @Size(min = 3, max = 5, message = "字符串长度要求3-5之间")
    private String size;
}
