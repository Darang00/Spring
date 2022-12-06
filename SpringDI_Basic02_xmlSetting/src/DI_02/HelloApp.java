package DI_02;

public class HelloApp {

	public static void main(String[] args) {
		//영문
		MessageBean_en messagebean_en = new MessageBean_en();
		messagebean_en.sayHello("Dayeong");
		
		//한글
		MessageBean_kr messagebean_kr = new MessageBean_kr();
		messagebean_kr.sayHello("Dayeong");
		
		
		MessageBean messagebean = new MessageBean_en();
		messagebean.sayHello("Dayeong"); 

	}

}
