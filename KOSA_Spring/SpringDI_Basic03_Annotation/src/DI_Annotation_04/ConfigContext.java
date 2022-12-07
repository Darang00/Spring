package DI_Annotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //DI_Config.xml 과 같은 역할 //설정 파일을 만드는 annotation
public class ConfigContext {
	//xml <bean id="user" class="DI_Annotation_04_User">
	//자바 코드에서는 함수를 통해서 객체를 return
	@Bean 
	public User user() {
		return new User();
	}
	
	//xml <bean id="user" class="DI_Annotation_04_User2">
	@Bean
	public User2 user2() { //함수 이름 달라야 한다 User 클래스의 user() 함수랑 User2 클래스의 user2 함수 이름 !=
		return new User2();
	}
}
