package com.akash.evm.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author akash.sahi
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, DBConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}

}

/**
 * XML equivalent -- web.xml
 * 
 * <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi=
 * "http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=
 * "http://java.sun.com/xml/ns/javaee
 * http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
 * 
 * <display-name>Gradle + Spring MVC Hello World</display-name>
 * <description>Spring MVC web application</description>
 * 
 * <!-- For web context --> <servlet>
 * <servlet-name>hello-dispatcher</servlet-name> <servlet-class>
 * org.springframework.web.servlet.DispatcherServlet </servlet-class>
 * <init-param> <param-name>contextConfigLocation</param-name>
 * <param-value>/WEB-INF/spring-mvc-config.xml</param-value> </init-param>
 * <load-on-startup>1</load-on-startup> </servlet>
 * 
 * <servlet-mapping> <servlet-name>hello-dispatcher</servlet-name>
 * <url-pattern>/</url-pattern> </servlet-mapping>
 * 
 * <!-- For root context --> <listener> <listener-class>
 * org.springframework.web.context.ContextLoaderListener </listener-class>
 * </listener>
 * 
 * <context-param> <param-name>contextConfigLocation</param-name>
 * <param-value>/WEB-INF/spring-core-config.xml</param-value> </context-param>
 * 
 * </web-app>
 */
