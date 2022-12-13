package AOP_Basic_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class Calc {

/*	
간단한 사칙 연산 프로그램
-주관심(업무) : 사칙연산(ADD, MUL) >> 기능 함수 구현
-보조관심(공통관심) : 연산에 걸린 시간
-log 출력 (console.log( red 한 글자 출력))

시간이 지나고 유지보수 시간....
변화 ..... 100개의 함수에 모두 .... 100개의 함수를 찿아서 모두 수정 .......
	
*/	
	
	public int Add(int x, int y) {
		Log log = LogFactory.getLog(this.getClass()); //LogFactory는 사용자의 시스템에 알맞은 Log를 생성해주는 팩토리 클래스이다
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		
		//주업무
		int result = x + y;
		
		sw.stop();
		log.info("[타이머 종료]");
		log.info("[Time Log Method : ADD]");
		log.info("[Time log Method: ] " + sw.getTotalTimeMillis()); 
		//getTotalTimeMillis(): Get the total time in milliseconds for all tasks.
		
		
		return result;
	}
	
	public int Mul(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		try {
			Thread.sleep(1000); //1000밀리초 동안 프로그램 멈추는 함수
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//주업무
		int result = x * y;
		
		sw.stop();
		log.info("[타이머 종료]");
		log.info("[Time Log Method : MUL]");
		log.info("[Time log Method: ] " + sw.getTotalTimeMillis());
		
		
		return result;
	}
}
