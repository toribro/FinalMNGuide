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
public class BoardFileInfo {
	private int picNo;
	private String originName;
	private String changeName;
	private String filePath;
	private int userNo;
	private int boardNo;
}
