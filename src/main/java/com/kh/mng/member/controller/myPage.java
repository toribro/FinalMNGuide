package com.kh.mng.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.MyPageEnter;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Picked;
import com.kh.mng.location.model.vo.WishListNo;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberService;
import com.kh.mng.pet.model.vo.Pet;
import com.kh.mng.pet.service.PetService;

@Controller
public class myPage {

	@Autowired
	private PetService petService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/myPageMain.mp")
	public String myPageMain(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			List<MyPageReview> ReviewList = petService.getReviewList(userNo);
			
			List<MyPageReview> reviews = new ArrayList<>();
			
			for(MyPageReview review : ReviewList) {
				int reviewNo = review.getReviewNo();
				System.out.println(reviewNo);
				List<ProfileImg> reviewImg = memberService.getReivewImg(reviewNo);
				review.setReviewImg(reviewImg);
				reviews.add(review);
			}
			session.setAttribute("reviews", reviews);
			System.out.println(reviews);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageMain"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageMain"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageMain"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}
	
    @ResponseBody
    @PostMapping("/updateReview.mp")
    public String updateReview(@RequestParam int reviewNo,
                               @RequestParam String reviewContent,
                               HttpSession session) {
        // 세션에서 로그인 사용자 정보 가져오기
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "SESSION_EXPIRED"; // 예시: 세션이 만료된 경우 처리 방법
        }

        // 사용자 정보 설정
        int userNo = loginUser.getUserNo();
        String userNickName = loginUser.getUserNickname();

        // 리뷰 정보 설정
        MyPageReview myReview = new MyPageReview();
        myReview.setUserNo(userNo);
        myReview.setReviewNo(reviewNo);
        myReview.setReviewContent(reviewContent);

        // 리뷰 수정 서비스 호출
        int result = memberService.updateReview(myReview);

        // 결과에 따른 처리
        if (result > 0) {
            return "NNNNY"; // 수정 성공
        } else {
            return "NNNNN"; // 수정 실패
        }
    }
	
	@ResponseBody
	@PostMapping("/deleteReview.mp")
	public String deleteReview(@RequestParam int reviewNo, HttpSession session) {	    	    	    
	    int result = memberService.deleteReview(reviewNo);
	    System.out.println(reviewNo);
	    if (result > 0) {
	        // 삭제 성공 시 리스트에서도 삭제
	        return "NNNNY"; // 삭제 성공
	    } else {
	        return "NNNNN"; // 삭제 실패
	    }
	}

	@GetMapping("/myPageWish.mp")
	public String myPageWishList(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			List<Picked> pickList = memberService.getPickList(userNo);
			List<BossLocation> locationList = memberService.getLocationList();
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			
			System.out.println(userNo);
			System.out.println(locationList);
			List<BossLocation> wishList = new ArrayList<>();
			for (Picked picked : pickList) {
				for (BossLocation location : locationList) {
					if (picked.getLocationNo() == location.getLocationNo()) {
						ProfileImg locationImg = memberService.getLocationImg(location.getLocationNo());
						List<Picked> pickCount = memberService.getPickCount(location.getLocationNo());
						List<MyPageEnter> enterGrade = memberService.getEnterList(location.getLocationNo());
						location.setLocationImg(locationImg);
						location.setPickCount(pickCount.size());
						location.setGetEnterList(enterGrade);
						wishList.add(location);
					}
				}
			}
			System.out.println(wishList);
			session.setAttribute("wishList", wishList);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageWishList"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageWishList"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageWishList"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}

	@ResponseBody
	@GetMapping("/wishListDelete")
	public String wishListDelete(@RequestParam int locationNo, HttpSession session) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		int userNo = loginUser.getUserNo();
		 List<BossLocation> wishList = (List<BossLocation>) session.getAttribute("wishList");
		 BossLocation deleteList = null;
		for (BossLocation location : wishList) {
			if(location.getLocationNo() == locationNo) {
				deleteList = location;
				break;
			}
		}
		WishListNo wishListNo = new WishListNo();
		wishListNo.setLocationNo(locationNo);
		wishListNo.setUserNo(userNo);
		int result = memberService.wishListDelete(wishListNo);
		if (result > 0) {
			wishList.remove(deleteList);
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}

	@GetMapping("/myPageCoupon.mp")
	public String myPageCoupon(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageCoupon"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageCoupon"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageCoupon"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}

	@GetMapping("/myPageBoard.mp")
	public String myPageBoardList(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			List<CommunityBoard> BoardList = petService.getBoardList(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			
			List<CommunityBoard> boards = new ArrayList<>();
			for(CommunityBoard board : BoardList) {
				int boardNo = board.getBoardNo();
				List<ProfileImg> boardImg = memberService.getBoardImg(boardNo);
				board.setBoardImg(boardImg);
				boards.add(board);
			}
			
			session.setAttribute("boards", boards);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageBoardList"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageBoardList"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageBoardList"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}
	
	@ResponseBody
	@PostMapping("/deleteBoard.mp")
	public String deleteBoard(@RequestParam int boardNo, HttpSession session) {	    	    	    
	    int result = memberService.deleteBoard(boardNo);
	    System.out.println(boardNo);
	    if (result > 0) {
	        // 삭제 성공 시 리스트에서도 삭제
	        return "NNNNY"; // 삭제 성공
	    } else {
	        return "NNNNN"; // 삭제 실패
	    }
	}

	@GetMapping("/myPageShorts.mp")
	public String myPageShortsList(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			List<Shorts> shortsList = memberService.getShortsList(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			System.out.println(shortsList);
			session.setAttribute("shortsList", shortsList);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageShortsList"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageShortsList"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageShortsList"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}

	@GetMapping("myPageInfo.mp")
	public String myPageInfo(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				model.addAttribute("petList", petList);
				return "myPage/myPageInfo"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPageInfo"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "myPage/myPageInfo"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}

	@GetMapping("myPagePetInfo.mp")
	public String myPagePetInfo(Model model, HttpSession session) {
		// 세션에서 로그인된 사용자의 정보를 가져옴
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser != null) {
			int userNo = loginUser.getUserNo(); // 사용자 번호
			// 사용자 번호를 사용하여 펫 데이터를 불러옴
			List<Pet> petList = petService.getPetByUserNo(userNo);
			ProfileImg profileImg = memberService.getProfileImg(userNo);
			List<ProfileImg> imgList = petService.getImgList();
			session.setAttribute("imgList", imgList);
			System.out.println(imgList);
			System.out.println(petList);
			
			for(Pet pet : petList) {
				for(ProfileImg img : imgList) {
					if(pet.getPetNo() == img.getPetNo()) {
						pet.setPetImg(img);
						break;
					}
				}
			}
			if (petList != null) {
				// 펫 데이터를 모델에 추가하여 HTML에 전달
				session.setAttribute("petList", petList);
				return "myPage/myPagePetInfo"; // 펫 정보를 수정하는 페이지로 이동
			} else {
				// 펫 데이터가 없는 경우
				return "myPage/myPagePetInfo"; // 펫 정보 페이지로 이동
			}
		} else {
			// 세션에 사용자 정보가 없는 경우
			model.addAttribute("errorMsg", "로그인이 필요합니다.");
			return "member/memberLogin"; // 로그인 페이지로 이동 또는 에러 메시지 표시
		}
	}

	@GetMapping("myPagePetSignUp.mp")
	public String myPagePetSignUp() {
		return "myPage/myPagePetSignUp";
	}

	@ResponseBody
	@GetMapping("deleteMember.mp")
	public String deleteMember(HttpSession session) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		int userNo = loginUser.getUserNo();

		int result = memberService.deleteMember(userNo);
		System.out.println(userNo);
		if (result > 0) {
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}
	
	@ResponseBody
	@PostMapping("/insertProfileImg.mp")
	public String insertOrUpdateProfileImg(@RequestParam("profileImage") MultipartFile upfile, HttpSession session) {
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    int userNo = loginUser.getUserNo();
	    
	    // 이미지 정보를 데이터베이스에서 가져오기
	    ProfileImg existingProfileImg = memberService.getProfileImg(userNo);
	    
	    System.out.println(existingProfileImg);
	    // 새로 업로드된 파일 처리
	    if (!upfile.isEmpty()) {
	        String changeName = saveFile(upfile, session);
	        
	        ProfileImg profileImg = new ProfileImg();
	        profileImg.setOriginName(upfile.getOriginalFilename());
	        profileImg.setChangeName(changeName);
	        profileImg.setUserNo(userNo);
	        profileImg.setFilePath("resources/img/user/");
	        profileImg.setFileLevel(0);
	        
	        // 이미 등록된 이미지가 있으면 update 수행
	        if (existingProfileImg != null) {
	            profileImg.setPicNo(existingProfileImg.getPicNo());
	            int updateResult = memberService.updateProfileImg(profileImg);
	            
	            if (updateResult > 0) {
	            	loginUser.setUserProfile(profileImg);
	            	session.setAttribute("loginUser", loginUser);
	                return "NNNNY"; // Update 성공 시 반환할 메시지
	            } else {
	                return "NNNNN"; // Update 실패 시 반환할 메시지
	            }
	        } else {
	            // 등록된 이미지가 없으면 insert 수행
	            int insertResult = memberService.insertProfileImg(profileImg);
	            
	            if (insertResult > 0) {
	            	loginUser.setUserProfile(profileImg);
	            	session.setAttribute("loginUser", loginUser);
	                return "NNNNY"; // Insert 성공 시 반환할 메시지
	            } else {
	                return "NNNNN"; // Insert 실패 시 반환할 메시지
	            }
	        }
	    }
	    
	    return "NNNNN"; // 파일이 없을 경우 반환할 메시지
	}
	
	public String saveFile(MultipartFile upfile, HttpSession session) {
		//파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
				String originName = upfile.getOriginalFilename();
				
				//년월일시분초 
				String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				//5자리 랜덤값
				int ranNum = (int)(Math.random() * 90000) + 10000;
				
				//확장자
				String ext = originName.substring(originName.lastIndexOf("."));
				
				//수정된 첨부파일명
				String changeName = currentTime + ranNum + ext;
				
				//첨부파일을 저장할 폴더의 물리적 경로(session)
				String savePath = session.getServletContext().getRealPath("resources/img/user/");
				try {
					upfile.transferTo(new File(savePath + changeName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return changeName;
		
	}
}
