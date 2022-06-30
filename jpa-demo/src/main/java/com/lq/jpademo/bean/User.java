package com.lq.jpademo.bean;

import com.lq.jpademo.converter.SexConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author qili
 * @create 2022-06-30-20:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    private String note;
}
