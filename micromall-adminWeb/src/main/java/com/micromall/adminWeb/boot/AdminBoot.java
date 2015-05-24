package com.micromall.adminWeb.boot;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by luffy on 2015/5/24.
 *
 * @author luffy luffy.ja at gmail.com
 */
public class AdminBoot extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                ApplicationConfig.class, WebConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}