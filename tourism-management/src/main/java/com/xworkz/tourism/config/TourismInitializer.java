package com.xworkz.tourism.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class TourismInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public TourismInitializer()
    {
        System.out.println("TourismInitializer constructor");
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
        return new Class[]{TourismConfiguration.class};
    }

}
