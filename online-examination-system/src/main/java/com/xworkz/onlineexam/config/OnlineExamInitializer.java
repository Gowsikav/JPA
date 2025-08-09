package com.xworkz.onlineexam.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class OnlineExamInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public OnlineExamInitializer()
    {
        System.out.println("OnlineExamInitializer constructor");
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
        return new Class[]{OnlineExamConfiguration.class};
    }


}
