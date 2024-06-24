package com.kh.mng.community.model.vo;

import java.sql.Date;
import java.util.List;

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
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int count;
	private Date createDate;
	private Date editDate;
	private boolean status;
	private String userNo;
	private String boardCategoryNo;
	private int goodCount; // 데이터 조회 시 사용할 추천 횟수
	private int replyCount; // 데이터 조회 시 사용할 댓글수
	private List<ProfileImg> boardImg; // 마이페이지용 게시글 사진 리스트
}
