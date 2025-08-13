package com.xworkz.passport.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class PassportInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public PassportInitializer()
    {
        System.out.println("PassportInitializer constructor");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{PassportConfiguration.class};
    }


}
