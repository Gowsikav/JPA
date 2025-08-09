package com.xworkz.foodorder.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FoodOrderInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public FoodOrderInitializer()
    {
        System.out.println("FoodOrderInitializer constructor");
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
        return new Class[]{FoodOrderConfiguration.class};
    }

}
