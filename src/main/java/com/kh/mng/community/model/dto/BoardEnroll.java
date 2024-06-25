package com.kh.mng.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardEnroll {
	private int boardNo;
	private int boardCategoryNo;
	private String boardTitle;
	private String boardContent;
	private int userNo;
}
