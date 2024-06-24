package com.kh.mng.bosspage.model.vo;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.mng.common.model.vo.Attachment;

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
public class BossLocationOption {

	private int locationOptionNo;
	private int locationNo;
	private String goods;//상품이름
	private String goodPrice;//상품가격
	private String roomInfo;//객실 정보
	private boolean roomStatus; //수용상태
    private int capacity;//수용인원
}
