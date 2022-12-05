package DI2;

//NewRecord를 사용해서 점수를 출력하는 클래스
public class NewRecordView {
	//NewRecordview는 점수를 받기 위해서 NewRecord가 필요합니다.
	
	private NewRecord record; //포함관계로 쓰겠다
	
	//setter 함수를 통해서 필요한 객체의 주소를 받기
	//언젠가 주소가 필요하겠지 그럼 그때가 오면 setter 함수를 통해서 주입받으면 되겠지
	public void setRecord(NewRecord record) {
		this.record = record;
	}
	
	/*
	 나는 니가 필요해
	 나는 너의 객체 주소가 필요해.....
	 1. 생성자를 통해서 필요한 객체를 생성 or 주입 >> DI >> 복합, 집합
	 2. 함수(setter)를 통해서 필요한 객체를 주입 >> DI >> 집합 >> 필요에 따라서  
	 */
	
	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
	
}
