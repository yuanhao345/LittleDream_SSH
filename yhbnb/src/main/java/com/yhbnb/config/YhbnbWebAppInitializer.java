package com.yhbnb.config;

import com.yhbnb.util.FilePathUtil;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;

public class YhbnbWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        /*
         * 加载IntrospectorCleanupListener 此监听器主要用于解决java.beans.Introspector导致的内存泄漏的问题
         * 该监听器要先于ContextLoaderListener执行
         */
        IntrospectorCleanupListener introspectorCleanupListener = new IntrospectorCleanupListener();

        servletContext.addListener(introspectorCleanupListener);


        super.onStartup(servletContext);

        // 设置Tomcat同级路径
        String projectPath = servletContext.getRealPath("/");
        String tomcatPath = new File(projectPath).getParentFile().getParentFile().getParentFile().getAbsolutePath();
        System.out.println(tomcatPath);
        FilePathUtil.setProjectPath(tomcatPath);

    }
    //配置DispatcherServlet映射到 '/' 什么情况进入DispatherServlet
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //spring 注入bean
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }
    //spring mvc 配置
    @Override
    protected Class[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {//注册过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{new HiddenHttpMethodFilter(), characterEncodingFilter};
    }
}
