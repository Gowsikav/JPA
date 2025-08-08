package com.xworkz.bookstore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BookStoreInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public BookStoreInitializer()
    {
        System.out.println("BookStoreInitializer constructor");
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
        return new Class[]{BookStoreConfiguration.class};
    }


}
