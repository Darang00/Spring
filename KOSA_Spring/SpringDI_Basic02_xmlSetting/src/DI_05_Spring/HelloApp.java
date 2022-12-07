package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/*
		//DI_05_Spring.MyBean@15db9742 , DI_05_Spring.MyBean@6d06d69c , DI_05_Spring.MyBean@7852e922
		//새로 new 하는 애들이라서 셋 다 주소 다르게 나옴
		MyBean mybean = new MyBean();
		MyBean mybean2 = new MyBean("hong");
		MyBean mybean3 = new MyBean();
		
		System.out.println(mybean + " , " + mybean2 + " , " + mybean3);
		
		//DI_05_Spring.Singleton@4e25154f , DI_05_Spring.Singleton@4e25154f , DI_05_Spring.Singleton@4e25154f
		//새로 new 하는 게 아니라 singleton에서 return 하는 하나의 값에 3번 접근하므로 셋 다 같은 주소값 가진다
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		Singleton single3 = Singleton.getInstance();
		
		System.out.println(single + " , " + single2 + " , " + single3); */
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring");
		MyBean m1 = context.getBean("mybean", MyBean.class);
		MyBean m2 = context.getBean("mybean", MyBean.class);
		MyBean m3 = context.getBean("mybean", MyBean.class);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		
		Singleton single1 = context.getBean("single", Singleton.class);
		Singleton single2 = context.getBean("single", Singleton.class);
		
		System.out.println(single1 + " , " + single2);
		
		/*
		 getBean()
		 1. 컨테이너 안에 있는 객체를 리턴 (new 가 아니에요)
		 2. return type Object (타입에 맞는  casting)
		 3. ** 스프링 컨테이너 안에 객체들을 default singleton
		 4. ** 예외적으로 
		 */
		

	}
}
