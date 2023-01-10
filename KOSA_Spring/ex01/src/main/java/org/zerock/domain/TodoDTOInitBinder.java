package org.zerock.domain;

import java.util.Date;

import lombok.Data;

//6.3.4 (p135) @InitBinder
@Data
public class TodoDTOInitBinder {
	private String title;
	private Date dueDate;
}
