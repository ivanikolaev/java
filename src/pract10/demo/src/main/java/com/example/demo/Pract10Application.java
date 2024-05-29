package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Pract10Application {

	private final ApplicationContext context;

	public Pract10Application(ApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Pract10Application.class);

		if (args.length == 0) {
			System.out.println("Please provide the bean name as an argument.");
			return;
		}

		String beanName = args[0];

		Programmer programmer = (Programmer) context.getBean(beanName);
		programmer.doCoding();
	}

}
