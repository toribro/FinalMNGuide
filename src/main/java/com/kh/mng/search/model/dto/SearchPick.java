package com.kh.mng.search.model.dto;

import java.util.ArrayList;

import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.vo.Location;

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
public class SearchPick {
	private int locationNo;
	private int userNo;
	private String status;
	private int locPickCount;
}
