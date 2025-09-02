package com.xworkz.library.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class LibraryInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public LibraryInitializer()
    {
        System.out.println("LibraryInitializer constructor");
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
        return new Class[]{LibraryConfiguration.class};
    }

}
