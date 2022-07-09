package com.lq.mvcdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author qili
 * @create 2022-07-09-19:09
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 添加view controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/insert")
                .setViewName("add-user");

        registry.addViewController("/my/format/form")
                .setViewName("data-format");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
