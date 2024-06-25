package com.kh.mng.location.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.ProfileImg;

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

public class MyPageEnter {
	private int enterNo;
	private int locationNo;
	private int petSizeNo;
	private String petSizeName;
}
