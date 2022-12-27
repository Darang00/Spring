package dto;

import java.util.Date;

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

}
