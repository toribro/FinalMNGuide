package com.kh.mng.bosspage.model.dto;

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
public class CouponKind {
	private int couponNo;
	private String couponContent;
	private String couponExpirationPeriod;
	private boolean status;
	private int count;
	private int usedCount;
	private String locationNo;
	private String loginUserNo;
}
