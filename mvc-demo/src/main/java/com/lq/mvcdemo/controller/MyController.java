package com.lq.mvcdemo.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qili
 * @create 2022-07-09-18:40
 */
@Controller
@RequestMapping("/my")
public class MyController {

    // 无注解的情况下获得参数，要求请求参数名与控制器方法形参名一致，参数可以不传，此时控制器方法获取null值
    @GetMapping("/no/anno")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String strVal) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("strVal", strVal);

        return map;
    }

    // 该注解的目的：将前端参数与后端参数对应起来
    // 默认时请求必须带上这些请求参数，否则报400，可以通过设置注解的required属性为false，
    // 这样在请求不传递该参数时，控制器方法获取的值为null
    @GetMapping("/anno")
    @ResponseBody
    public Map<String, Object> requestParam(
            @RequestParam("int_val") Integer intVal,
            @RequestParam("long_val") Long longVal,
            @RequestParam("str_val") String strVal) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("strVal", strVal);

        return map;
    }

    @GetMapping("/array")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, long[] longArray, String[] strArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("intVal", intArr);
        map.put("longVal", longArray);
        map.put("strVal", strArr);
        return map;
    }

    @ResponseBody
    @PostMapping("/data/format")
    public Map<String, Object> formatData(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @NumberFormat(pattern = "#,###.##") Double number) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("num", number);
        return map;
    }

    @GetMapping("/matrix/{path}")
    @ResponseBody
    public Map<String, Object> testMatrixVariable(
            @MatrixVariable("low") Integer low,
            @MatrixVariable("high") Integer high,
            @PathVariable("path") String path,
            HttpServletRequest request
    ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("high",high);
        map.put("path", path);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("请求URL：" + requestURL);
        return map;

    }
}
