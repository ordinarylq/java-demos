package com.lq.jpademo.converter;

import com.lq.jpademo.bean.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * @author qili
 * @create 2022-06-30-21:26
 */
public class SexConverter implements AttributeConverter<SexEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum attribute) {
        return attribute.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer dbData) {
        return SexEnum.getEnumById(dbData);
    }
}
