package com.model;

import lombok.Data;

@Data
//DB에 Article 테이블 있다고 가정 1:1 매핑
public class NewArticleCommand {
	private int parentId;
	private String title;
	private String content;
	
}
