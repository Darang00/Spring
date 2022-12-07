package DI;

//NewRecord를 사용해서 점수를 출력하는 클래스
public class NewRecordView {
	//NewRecordview는 점수를 받기 위해서 NewRecord가 필요합니다.
	
	private NewRecord record; //포함관계로 쓰겠다
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	
	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
	
}
