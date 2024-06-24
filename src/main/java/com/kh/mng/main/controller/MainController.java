package com.kh.mng.main.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.main.service.MainServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	private MainServiceImpl mainService;
	
	@ResponseBody
	@RequestMapping(value="topPlace.ma", produces="application/json; charset-UTF-8")
	public String ajaxSelectLocationMainList() {
		ArrayList<Location> list = mainService.ajaxSelectLocationMainList();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="topBoard.ma", produces="application/json; charset-UTF-8")
	public String ajaxSelectBoardMainList(int type) {
		ArrayList<Board> list = new ArrayList<Board>();
		switch(type) {
		case 1 :
			list = mainService.ajaxSelectBoardCountList();
			break;
		case 2 :
			list = mainService.ajaxSelectBoardGoodList();
			break;
		case 3 :
			list = mainService.ajaxSelectBoardReplyList();
			break;
		}
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="topShorts.ma", produces="application/json; charset-UTF-8")
	public String ajaxSelectShortsList() {
		ArrayList<Shorts> list = mainService.ajaxSelectShortsList();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping("registPetModal.ma")
	public String ajaxSelectPetCount(int userNo) {
		int result = mainService.ajaxSelectPetCount(userNo);

		if (result > 0) {
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}
	
}
