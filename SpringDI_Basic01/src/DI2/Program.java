package DI2;

public class Program {
	
	public static void main(String[] args) {
		//NewRecordView view = new NewRecordView(100, 50, 40);
		//view.print();
		
		NewRecordView view = new NewRecordView();
		//놀다가
		//NewRecord 객체가 필요하면
		NewRecord record = new NewRecord(100, 50, 50);
		view.setRecord(record); //의존객체의 주소 주입 (setter 함수)ㄴ
		view.print();
	}

}
