package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
	
	public static void main(String[] args) {
		/*
		//NewRecordView view = new NewRecordView(100, 50, 40);
		//view.print();
		
		NewRecordView view = new NewRecordView();
		//놀다가
		//NewRecord 객체가 필요하면
		NewRecord record = new NewRecord(100, 50, 50);
		view.setRecord(record); //의존객체의 주소 주입 (setter 함수)
		
		view.input();		
		view.print();
		
		*/
		
		/*
		 (스프링은 자신만의 메모리 공간이 필요해요)
		 1. SpringFramework 가 제공하는 컨테이너에 객체를 생성하는 작업 (메모리 생성: IOC(제어의 역전) 컨테이너 )
		 메모리 공간 만드는 코드 : ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		 
		 2. 컨테이너를 만들고 그 메모리에 필요한 객체를 생성하고 조립(주입)한다.... 자동으로 소멸한다
		 컨테이너가 생성되고 DIConfig.xml 를 read 하고 컴파일 해요... 
		 	DIConfig.xml 컴파일하면 bean 객체를 생성하고 필요한 객체를 주입하고... 작업이 끝나요
		
		 */
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		//Spring 필요한 메모리공간을 생성하고 생성된 메모리에 DIConfig.xml read 해서 객체 생성하는 코드
		
		//컨테이너 안에 필요한 객체가 생성되어 있어요
		//컨테이너 안에 여러개의 블럭들이 생성되어 있고요... 필요한 블럭만 가지고 와서 놀면 돼요  >> getBean() 함수 안에 넣는 것: xml 파일의 id 값!
		RecordView view = (RecordView)context.getBean("view"); //getBean(): 메모리에 있는 걸 끄집어내는 역할
		
		
		
		view.input();
		view.print();
		
		//에러 터짐_ Caused by: java.lang.ClassNotFound: org.apache.common.logging.logFactory
	}
}
