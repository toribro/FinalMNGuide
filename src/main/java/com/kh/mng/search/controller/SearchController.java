package com.kh.mng.search.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.common.model.vo.Pagination;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.search.model.dto.SearchFilter;
import com.kh.mng.search.model.dto.SearchPick;
import com.kh.mng.search.service.SearchServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SearchController {
	
	@Autowired
	private SearchServiceImpl searchService;
	
	@GetMapping("search.pl")
	public String searchForm() {
		return "search/searchPage";
	}
	
	/*
	 * 검색 시 필요한 데이터
	 * - 현재 페이지 값
	 * - 검색한 키워드
	 * - 필터링 항목
	 * - 정렬 기준
	 *
	 * - 헤더 검색 시 필터링 결과, 키워드 결과는 남아 있어야 함
	 */
//	@GetMapping("searchKeyword.pl")
//	public String searchPage(@RequestParam(value="cpage", defaultValue="1") int currentPage, 
//								String keyword, Model model) {
//		
////		int locationCount = searchService.selectLocationListCount();
////		PageInfo pi = Pagination.getPageInfo(locationCount, currentPage, 10, 10);
////		
////		ArrayList<Location> list = searchService.selectSearchLocationList(keyword, pi);
////		
////		for(Location loc : list) {
////			System.out.println(loc);
////			System.out.println(loc.getEnterList());
////			System.out.println(loc.getOpTime());
////			System.out.println(loc.getAttachment());
////		}
//		
//		model.addAttribute("keyword", list);
//		
//		return "search/searchPage";
//	}
	
	@GetMapping("searchKeyword.pl")
	public String searchKeyword(@RequestParam(value="cpage", defaultValue="1") int currentPage, 
								@RequestParam(value="keyword", defaultValue="") String keyword, 
								@RequestParam(value="loginUserNo", defaultValue="0") String loginUserNo, Model model) {
		
		int locationCount = searchService.selectLocationListCount(keyword);
		PageInfo pi = Pagination.getPageInfo(locationCount, currentPage, 10, 10);
		
		SearchFilter sf = new SearchFilter();
		sf.setKeyword(keyword);
		if (loginUserNo != null) {
			sf.setLoginUserNo(loginUserNo);
		}
		
		ArrayList<Location> list = searchService.selectSearchLocationList(sf, pi);
		
		// 찜 개수 가져와야 함
		model.addAttribute("keyword", keyword);
		model.addAttribute("pi", pi);
		model.addAttribute("locationList", list);
		
		return "search/searchPage";
	}
	
	@ResponseBody
	@GetMapping(value="searchPage.pl", produces="application/json; charset-UTF-8")
	public String searchPage(@RequestParam(value="cpage", defaultValue="1") int currentPage, 
							String petList, String locList, String order, String keyword, String loginUserNo,
							Model model, HttpServletRequest request) {
//							@RequestParam(value="petList") Map<String, SearchFilter> petList, 
//							@RequestParam(value="locList")List<String> locList,
//							이런 식으로 List로 받을 게 아니라 그냥 아예 프론트에서 String으로 바꿔서 보낼 것

		ArrayList<Integer> pets = new ArrayList<Integer>();
		ArrayList<Integer> locs = new ArrayList<Integer>();
		
		log.info(order);
		
		if (!petList.isEmpty()) {
			for (String p : petList.split(",")) {
				pets.add(Integer.parseInt(p));
			}
		}
		
		if (!locList.isEmpty()) {
			for (String l : locList.split(",")) {
				locs.add(Integer.parseInt(l));
			}
		}
		
		SearchFilter sf = new SearchFilter();
		sf.setKeyword(keyword);
		sf.setPetList(pets);
		sf.setLocList(locs);
		sf.setOrder(order);
		
		if (loginUserNo != null) {
			sf.setLoginUserNo(loginUserNo);
		}
		
		int locationCount = searchService.selectFilterLocationListCount(sf);
		PageInfo pi = Pagination.getPageInfo(locationCount, currentPage, 10, 10);
		
		ArrayList<Location> list = searchService.selectFilterLocationList(sf, pi);
		
		sf.setLocationList(list);
		sf.setPi(pi);
		
		model.addAttribute("locationInfo", sf);
		
		return new Gson().toJson(sf);
	}
	
	@ResponseBody
	@GetMapping(value="selectLikeInfo.pl", produces="application/json; charset-UTF-8")
	public String selectUserPick(int loginUserNo, int locNo) {
		SearchPick pick = new SearchPick();
		pick.setLocationNo(locNo);
		pick.setUserNo(loginUserNo);
		
		int userPick = searchService.selectUserPick(pick);
		
		if (userPick > 0) {
			SearchPick sp = searchService.deleteUserPick(pick);
			
			if (sp.getLocPickCount() > 0) {
				pick.setStatus("D");
				return new Gson().toJson(pick);
			} else {
				pick.setStatus("N");
				return new Gson().toJson(pick);
			}
			
		} else {
			SearchPick sp = searchService.insertUserPick(pick);
			if (sp.getLocPickCount() > 0) {
				pick.setStatus("I");
				return new Gson().toJson(pick);
			} else {
				pick.setStatus("N");
				return new Gson().toJson(pick);
			}
		}
		
		

	}
	
}
