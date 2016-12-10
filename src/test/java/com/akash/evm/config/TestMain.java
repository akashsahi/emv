package com.akash.evm.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(AppConfig.class, DBConfig.class);
		context.refresh();
		
		String[] beanName =context.getBeanDefinitionNames();
		for (int i = 0; i < beanName.length; i++) {
			System.out.println("Bean["+i+1+"] is >"+beanName[i]);
		}
		context.close();
	}

}
