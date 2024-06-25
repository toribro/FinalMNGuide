package com.kh.mng.bosspage.model.vo;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.location.model.vo.EnterGrade;
import com.kh.mng.location.model.vo.OperationTime;

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
public class LocationPetKind {
	
	private int petKindNo;
	private String petKindName;
}
