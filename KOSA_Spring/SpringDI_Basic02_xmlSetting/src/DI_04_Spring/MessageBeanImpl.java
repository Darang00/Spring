package DI_04_Spring;

public class MessageBeanImpl implements MessageBean {
	
	private String name;
	private String greeting;
	
	public MessageBeanImpl(String name) { //생성자 함수
		this.name = name;
	}
	public void setGreeting(String greeting) { //setter 함수
		this.greeting =greeting;
	}
	
	@Override
	public void sayHello() {
		System.out.println(this.name + " / " + this.greeting);
		
	}

}
