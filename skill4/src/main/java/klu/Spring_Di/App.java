package klu.Spring_Di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	System.out.println("Hello World!");
    	ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        
        Employee e1=(Employee)context.getBean("emp1");
        System.out.println(e1);
    }
}
