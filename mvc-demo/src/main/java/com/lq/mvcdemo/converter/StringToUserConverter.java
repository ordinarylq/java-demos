package com.lq.mvcdemo.converter;

import com.lq.mvcdemo.bean.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qili
 * @create 2022-07-12-19:40
 */
@Component
public class StringToUserConverter implements Converter<String, User> {
    /**
     * source格式为："id-username-note"
     * @param source
     * @return
     */
    @Override
    public User convert(String source) {
        String[] strList = source.split("-");

        Long id = Long.parseLong(strList[0]);
        String userName = strList[1];
        String note = strList[2];

        return new User(id, userName, note);
    }
}
