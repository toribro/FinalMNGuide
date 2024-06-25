package com.kh.mng.common.model.vo;

import java.util.Date;

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
public class Attachment {
	private int picNo;
	private String originName;
	private String changeName;
	private String filePath;
	private int fileLevel;
	private int locationNo;
	private int locationOptionNo;
	private int reviewNo;
	private int userNo;
	private int petNo;
	private int boardNo;
}
