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
public class Video {
	private int videoNo;
	private String originName;
	private String changeName;
	private String filePath;
	private int shortsNo;

}
