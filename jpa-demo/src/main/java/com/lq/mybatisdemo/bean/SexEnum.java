package com.lq.mybatisdemo.bean;

/**
 * @author qili
 * @create 2022-06-30-20:43
 */
public enum SexEnum {
    MALE(1, "男性"),
    FEMALE(2, "女性");

    private final int id;
    private final String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id) {
        for (SexEnum sex : SexEnum.values()) {
            if(sex.getId() == id) {
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
