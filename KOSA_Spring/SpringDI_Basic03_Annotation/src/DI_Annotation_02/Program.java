package DI_Annotation_02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		//DI_01.xml 파일에 만들어 놔서 필요 없다
		/*
		MonitorViewer viewer = new MonitorViewer();
		Recorder recorder = new Recorder();
		viewer.setRecorder(recorder);
		System.out.println(viewer.getRecorder());
		
		*/
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_Annotation_02/DI_02.xml");
		MonitorViewer viewer = context.getBean("monitorViewer", MonitorViewer.class); //getBean 안에 앞에꺼는 xml 파일에서 id 값, 뒤에거는 받아오는 객체 이름.class
		System.out.println(viewer.getRecorder());  //오타주의                                                 // 뒤에 MonitorViewer.class 안쓰면 앞에서 다운캐스팅 해야한다
	}
}
