package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {
	private Recorder recorder;
	
	public Recorder getRecorder() {
		return this.recorder;
	}
	
	/*
	 setter를 통해서 Recorder 라는 타입의 객체 주소를 주입했었는데
	 <property name="recorder">
	  	<ref bean="recorder" />
	 <property>
	 
	 컨테이너 안에 Recorder 타입의 객체가 존재하면 자동 주입 발생 .. (By type)
	 
	 */
	
	//@Autowired(required = true) default >> 무조건 injection 하겠다
	//@Autowired(required = false) >> 컨테이너 안에 원하는 타입 객체가 없으면 안 하면 되지
	@Autowired
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
}
