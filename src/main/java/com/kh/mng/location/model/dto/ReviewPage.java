package com.kh.mng.location.model.dto;

import java.util.ArrayList;

import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.vo.Review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewPage {
	private PageInfo page;
	private ArrayList<Review> reviews;
}
