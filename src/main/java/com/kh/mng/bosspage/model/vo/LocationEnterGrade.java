package com.kh.mng.bosspage.model.vo;

import java.sql.Time;

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
public class LocationEnterGrade {
	
	private int enterNo;
	private int locationNo;
	private int petSizeNo;
	private String petSizeName;
}
