// ***반려동물 등록 모달***
// 모달창 띄우기를 위한 ajax 통신을 해야하는지 검사 후 필요할 시 ajax 통신 실행
function compareRegistPetTime(userNo, userId){
    let currentTime = new Date();
    currentTime = currentTime.setTime(currentTime.getTime());
    let storageTime = getStorage(userNo+userId+"registPet");
    if (Number.isNaN(storageTime, currentTime) || (!Number.isNaN(storageTime, currentTime) && currentTime > storageTime)){
        selectRegistPetModal({userNo : userNo}, showRegistPetModal);
    } // 로컬 스토리지에 해당 name의 정보가 없는 경우 NaN이 반환되었음
}

    // 모달창 ajax 보내야 하는 조건
    // 1. storage에 해당 이름의 정보가 없으면 3일간 닫기 누른 적이 없으므로 무조건 떠야함
    // 2. storage에 해당 이름의 정보가 있는데 저장된 특정 시간을 지난 경우 떠야함

// ajax 결과가 NNNNN인 경우 반려동물 등록 모달창 띄우기
function showRegistPetModal(result){
    console.log("ajax성공")
    if (result == "NNNNN"){
        console.log("NNNNN")
        $("#regist-pet-alarm").show();
    }
}

// 3일간 보지 않기가 선택 되었는지 검사 후 선택 된 경우 setStorage에 userNo, userId 넘겨 시간 저장
function checkRegistPetTime(userNo, userId){
    let checkBtn = document.querySelector("#check-alarm");
    if (checkBtn.checked){
        setExpireStorage(userNo+userId+"registPet", 3);
    }
}

// 나중에 등록하기 클릭 시 모달창 없애기
function hideRegistPetModal(){
    $("#regist-pet-alarm").hide();
}

// 지금 등록하기 클릭 시 페이지 넘기기
function gotoRegistPet(){
    location.href = contextPath + '/myPagePetSignUp.mp';
}

// 해당 정보로 로컬 스토리지에 저장
function setExpireStorage(name, exp){
    console.log("들어옴")
    let date = new Date();

    date = date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000); // 현재 시각에 밀리세컨(ms)으로 바꾼 만료일수 더해 만료시간 정의
    localStorage.setItem(name, date); // 이름과 만료일수 세팅
}

// 해당 이름으로 저장된 정보 로컬 스토리지에서 가져옴
function getStorage(name){
    console.log(parseInt(localStorage.getItem(name)))
    return parseInt(localStorage.getItem(name));
}


// ***장소 추천***

// 장소 ajax 성공 시 추천 장소 출력
function drawPlaceTop(locationList){
    let placeImg = document.querySelector("#place-img");
    if (locationList[0].attachment != null){
        placeImg.src = locationList[0].attachment.filePath + locationList[0].attachment.changeName;
    } else if(locationList[0].attachment == null){
        placeImg.src = locationList[1].attachment.filePath + locationList[1].attachment.changeName;
    } else if(locationList[1].attachment == null){
        placeImg.src = locationList[2].attachment.filePath + locationList[2].attachment.changeName;
    } else {
        placeImg.src = 'resources/img/default/default_location_img.jpg'
    }
    
    let searchContentsWrap = document.querySelector("#search-contents-wrap");

    for (let loc of locationList){
        let placeContent = document.createElement('div');
        placeContent.classList.add("search-ranking-content", "gray-round-box");

        placeContent.onclick = function(){
            location.href = contextPath + "/detail?locationNo=" + loc.locationNo;
        }

        let place = "";

        // 장소 사진
        place += `<img src="`;
        if (loc.attachment != null){
            place += loc.attachment.filePath + loc.attachment.changeName;
        } else {
            place += 'resources/img/default/default_location_img.png';
        }
        
        place += `" alt="">`;

        // 텍스트를 감싸는 박스, 장소 이름
        place += `<div>
                    <span id="search-ranking-content-title">`+loc.locationName+`</span>`;

        // 별점
        place += `<div id="search-ranking-rating">
                    <div>`+loc.locationStar+`</div><div>`;
        for (i = 0; i < Math.round(loc.locationStar); i++){
            place += `<img class="ranking-star-img" src="resources/img/main/star.png" alt="">`
        };           
        place += `</div></div><hr>`

        // 주소
        place += `<div id="search-ranking-address">`+loc.address+`</div>`

        // 반려동물 분류
        place += `<div id="search-ranking-category">`
        for (enterGrade of loc.enterList){
            place += enterGrade.petSizeNo + ` `;
        };  
        place += `</div></div>`

        placeContent.innerHTML = place;
        searchContentsWrap.append(placeContent);
    }
}


// ***인기 커뮤니티 게시글***

// ajax 성공 시 인기 조회글 출력
function drawBoardDetailTop(type, boardList){
    let boardBox = document.querySelector("#community-ranking>div");

    let communityRankingBox = document.createElement("div");
    communityRankingBox.className = "community-ranking-box";

    let communityTable = document.createElement('table');
    communityTable.className = "community-ranking-table";

    boardBox.append(communityRankingBox);
    communityRankingBox.appendChild(communityTable);

    let board = "";

    board += `<tr><th colspan="2">`;
    switch(type){
        case 1 : 
            board += '인기 조회글';
            break;
        case 2 :
            board += '인기 추천글';
            break;
        case 3 :
            board += '댓글 최다순';
            break;
    }
    board += `</th></tr>`;
    communityTable.innerHTML = board;
    // let communityTable;
    // switch(type){
    //     case 1 : 
    //         communityTable = document.querySelector("#table-count");
    //         break;
    //     case 2 : 
    //         communityTable = document.querySelector("#table-good");
    //         break;
    //     case 3 : 
    //         communityTable = document.querySelector("#table-reply");
    //         break;
    // }
    
    for (i = 0; i < boardList.length; i++){
        board = boardList[i];
        let boardRanking = document.createElement('tr');
        boardRanking.className = "community-ranking-box-content";
        let boardNo = board.boardNo;

        boardRanking.onclick = function(){
            location.href = contextPath + "/detailView.bo?bno=" + boardNo;
        console.log(contextPath + "/detailView.bo?bno=" + boardNo)
        }
        communityTable.append(boardRanking);

        let boardDetail = "";

        // 순위
        boardDetail += `<td id="community-ranking-box-ranking">`+ (i + 1) + `</td>`;

        // 내용-제목
        boardDetail += `<td id="community-ranking-box-content">
                            <div class="community-ranking-box-title">
                                <div>`
        boardDetail += boardList[i].boardTitle + `</div>`;

        // 내용-댓글수
        boardDetail += `<span>[`+boardList[i].replyCount+`]</span></div>`;

        // 내용-작성자
        boardDetail += `<div class="community-ranking-box-member">`+boardList[i].userNo+`</div></td>`;
        boardRanking.innerHTML = boardDetail;
    }
}


// ***쇼츠 추천***

// ajax 성공 시 쇼츠 출력
function drawShortsTop(shortsList){
    let shortsRanking = document.querySelector("#shorts-ranking>div");
    
    for(let shorts of shortsList){
        let shortsRankingContent = document.createElement('div');
        shortsRankingContent.className = "shorts-ranking-content";
        shortsRanking.append(shortsRankingContent);

        shortsRankingContent.onclick = function(){
            location.href = contextPath + "/shortsView.bo?sno=" + shorts.shortsNo;
        }

        shortsBox = "";

        // 썸네일 이미지
        shortsBox += `<div><img src=` + shorts.attachment.filePath + shorts.attachment.changeName + ` alt=""></div>`;

        // 내용
        shortsBox += `<div class="shorts-ranking-text">
                            <div class="shorts-ranking-text-title">
                                <div class="shorts-ranking-text-user">`
        shortsBox += shorts.userNo;
        shortsBox += `</div>
                          <div class="shorts-ranking-text-date">`
        shortsBox += shorts.enrollDate;
        shortsBox += `</div></div>
                          <div class="shorts-ranking-text-content">`
        shortsBox += shorts.shortsContent;
        shortsBox += `</div></div></div>`

        shortsRankingContent.innerHTML = shortsBox;
    }

}
// for(let todo of todoList){
//     const todoLi = document.createElement('li'); // <li><li>
//     todoLi.className = todo.isDone ? "done" : "";
//     todoLi.innerHTML = todo.title; // <li>${todo.title}</li>
//     todoUl.appendChild(todoLi); // todoUl.innerHTML += `<li>${todo.title}
//     // todoUl 뒤에 innerHTML로 넣어줄게

//     const removeBtn = document.createElement('div');
//     removeBtn.className = 'todo-remove-btn';
//     removeBtn.innerHTML = '<i class="fa-solid fa-xmark"></i>'
//     todoLi.appendChild(removeBtn);




    // console.log(locationList);
    // for(let location of locationList){
    //     let ranking = document.createElement('div');
    //     let star = document.createElement('img');
    //     let rankingStar = document.createElement('div');

    //     ranking.class
    //     star.src = "/resources/img/main/star.png";

    //     console.log(locationList);
    //     for (i = 0; i < Math.round(location.locationStar); i++){
    //         // 여기서 오류가 나버리면 이 파일 전체가 로드 되지 않을 수도 있음
    //         star.src = contextPath + "/resources/img/main/star.png";
    //         rankingStar.appendChild(star);
    //     }

    //     ranking.appendChild(rankingStar);
    
    //     let searchRankingContent = document.createElement('div');
    
        
    
    //     searchRankingContent.classList.add('search-ranking-search', 'gray-round-box');
    //     // searchRankingContent.appendChild();
    //     searchContentsWrap.appendChild(searchRankingContent);
    
    //     // console.log("들어오지도 않은 거야?")
    //     // test.innerHTML = '안녕하세요 innerHTML 입니다.'
    //     // console.log(test);
    //     // test.style.background = "red";

    // }

    // function drawPlaceStar(location){
//     let star = "";
//     star += `<div id="search-ranking-rating">
//                 <div>`+Math.round(location.locationStar)+`</div><div>`;
//     for (i = 0; i < Math.round(location.locationStar); i++){
//         star += `<img class="ranking-star-img" src="resources/img/main/KakaoTalk_20240507_002829702_08.png" alt="">`
//     };           
//     star += `</div></div>`
// }

// $(function() {
// 	$("#regist-pet-alarm").modal("show");
// });
// document.addEventListener("DOMContentLoaded", function() {
//     var modal = document.getElementById("modal");
//     modal.classList.add("show");
// });

// var now = new Date();
//       now = now.setTime(now.getTime());
//       // 현재 시각과 스토리지에 저장된 시각을 각각 비교하여
//       // 시간이 남아 있으면 true, 아니면 false 리턴
//       return parseInt(localStorage.getItem(name)) > now

// /* 스토리지 제어 함수 정의 */
// var handleStorage = {
//     // 스토리지에 데이터 쓰기(이름, 만료일)
//     setStorage: function (name, exp) {
//       // 만료 시간 구하기(exp를 ms단위로 변경)
//       var date = new Date();
//       date = date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);
  
//       // 로컬 스토리지에 저장하기
//           // (값을 따로 저장하지 않고 만료 시간을 저장)
//       localStorage.setItem(name, date)
//     },
//     // 스토리지 읽어오기
//     getStorage: function (name) {
//       var now = new Date();
//       now = now.setTime(now.getTime());
//       // 현재 시각과 스토리지에 저장된 시각을 각각 비교하여
//       // 시간이 남아 있으면 true, 아니면 false 리턴
//       return parseInt(localStorage.getItem(name)) > now
//     }
//   };

// function selectRegistPetModal(loginUser){
//     console.log("들어왔어요")
//     let button = document.querySelector("#regist-pet-button");
//     button.click;
// }


// function sendToPage(url){
//     location.href = contextPath + url;
// }