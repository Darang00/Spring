package DI_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean messagebean = new MessageBean();
		messagebean.sayHello("Dayeong");
		
		/*
		 요구사항
		 1. 한글버전 (Dayeong) >> 안녕 Dayeong
		 2. 영문버전 (Dayeong) >> Hola Dayeong
		 
		 MessageBean_kr > 안녕 Dayoeng
		 MessageBean_en > Hello Dayoeng
		 
		 >> 인터페이스 다형성 (MessageBean 설계)
		 */

		//Spring 통해서 
		//1.
		//2. 

		//new ClasspathXmlApplicationContext("DIConfig.xml");
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring");
		MessageBean message = context.getBean("messagebean", MessageBean.class);
		message.sayHello("hong");
		

	}




}
