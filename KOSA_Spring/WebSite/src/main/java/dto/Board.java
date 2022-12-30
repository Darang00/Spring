package dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class Board {
	
	private String seq;
	private String title;
	private String writer;
	private String content;
	private Date writedate;
	private int hit;
	private String filesrc;
	private String filesrc2;
	
	//다중 파일 업로드.....3개 . 4개 파일 추가된는 가정하에 대비하겠다.......
		private List<CommonsMultipartFile> files;
		
	    
		public List<CommonsMultipartFile> getFiles() {
			return files;
		}
		public void setFiles(List<CommonsMultipartFile> files) {
			this.files = files;
		}
		/////////////////////////////////////////////////////////////

}
