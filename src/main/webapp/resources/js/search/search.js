// 정렬 기준 박스 토글
function showOrderBox(){
    let orderBox = document.querySelector("#order-by-box");
    orderBox.classList.toggle('display-block');
}


    
// 페이지 로드 시 공감 및 가게 클릭 시 페이지 이동 이벤트핸들러
function handelPick(event, loginUserNo, locationNo){
    if (event.target.matches('.pick-box img')) {
        if (event.target.parentNode.dataset.locno == locationNo){
            changePick(loginUserNo, locationNo);
        }
    } else if(event.target.matches('.search-content-box *')){ 
        location.href = contextPath + "/detail?locationNo=" + locationNo;
    }
}

// 공감 ajax 실행 함수
function changePick(loginUserNo, locNo){
    if (loginUserNo != ""){
        ajaxSelectLikeInfo({
            loginUserNo : loginUserNo,
            locNo : locNo
        }, drawPickStatus);
    } else {
        alert("로그인을 먼저 해주세요")
    }
}

// 공감 성공 시 화면 출력
function drawPickStatus(pick){
    let pickList = document.querySelectorAll('.pick-box');
    console.log(pickList)
    console.log(pick.locationNo)
    let pickBox = "";
    for (let p of pickList){
        console.log(p.getAttribute('data-locno'))
        if (p.getAttribute('data-locno') == pick.locationNo){
            pickBox = p;
            console.log(pickBox)
        }
    }
    pickBox.innerHTML = "";
    
    let pickImg = document.createElement('img');
    let pickCount = document.createElement('span');

    // pickImg.onclick = function(){
    //     changePick(pick.userNo, pick.locationNo)
    // }

    pickBox.append(pickImg)
    pickBox.append(pickCount)

    if (pick.status == "I"){
        pickImg.src = 'resources/img/searchpage/like-after.png'
        pickCount.innerHTML = pick.locPickCount;
    } else if (pick.status == "D"){
        pickImg.src = 'resources/img/searchpage/like-pre.png'
        pickCount.innerHTML = pick.locPickCount;
    }
}

// 필터링 박스 선택 시
function searchFilter(keyword, loginUserNo){
    let cpage = 1;
    searchFilterForm(keyword, loginUserNo, cpage)
}

// 페이지네이션 클릭 시
function searchFilterPage(keyword, loginUserNo, cpage){
    searchFilterForm(keyword, loginUserNo, cpage)
}

// ajax 데이터 처리 및 함수 호출
function searchFilterForm(keyword, loginUserNo, cpage){
    let pets = document.querySelectorAll('.filter-pet');
    let locs = document.querySelectorAll('.filter-location');
    let orderList = document.querySelectorAll('[name="order"]');
    
    let petList = [];
    for (let p of pets){
        if (!p.checked){
            petList.push(p.value)
        }
    }

    if (petList.length === 0) {
        petList.push(100)
    } else if (petList.length === 4){
        petList = [];
        petList.push(100)
    }

    let locList = [];
    for (let l of locs){
        if (!l.checked){
            locList.push(l.value)
        }
    }

    if (locList.length === 0) {
        locList.push(100)
    } else if (locList.length === 7){
        locList = [];
        locList.push(100)
    }

    let order;
    for (o of orderList){
        if (o.checked){
            order = o;
        }
    }

    getLocationData({
        keyword : keyword,
        petList : petList.toString(),
        locList : locList.toString(),
        order : order.value,
        cpage : cpage,
        loginUserNo : loginUserNo
    }, drawSearchPage)
}


// ajax 성공 시 화면 그리기
function drawSearchPage(locationInfo){
    // 컨텐츠 박스 가장 큰 테두리
    let searchResultBox = document.querySelector("#search-result-box");
    searchResultBox.innerHTML = "";

    // 검색 결과
    let searchTitleBox = document.createElement('div');
    searchTitleBox.id = 'search-title-box';

    searchResultBox.append(searchTitleBox)

    searchTitleBox.innerHTML += '<div id="search-title-text">"'+locationInfo.keyword+'" 검색 결과</div>'
    
    let searchOrderBy = document.createElement('div');
    searchOrderBy.id = 'search-order-by';

    searchTitleBox.append(searchOrderBy);

    // 정렬 기준
    let orderByTitle = document.createElement('div');
    orderByTitle.id = 'order-by-title';

    searchOrderBy.append(orderByTitle);
    
    orderByTitle.innerHTML += '<span>정렬기준</span><img id="order-by-icon" src="resources/img/searchpage/open-icon.png" alt="">'

    for (let i = 1; i <= 3; i++){
        let orderByContent = document.createElement('input');
        orderByContent.name = 'order';
        orderByContent.type = 'radio';
        orderByContent.style.display = 'none';
        orderByContent.value = i;

        if (i == 1){
            orderByContent.id = 'order-by-time';
        } else if (i == 2) {
            orderByContent.id = 'order-by-star';
        } else if (i == 3){
            orderByContent.id = 'order-by-pick';
        }

        if (orderByContent.value == locationInfo.order){
            orderByContent.checked = true;
        }

        searchOrderBy.append(orderByContent);

        orderByContent.onchange = function(){
            searchFilter(locationInfo.keyword, locationInfo.loginUserNo)
            // changeOrderColor(orderByContent)
        };
    }

    let orderByBox = document.createElement('div');
    orderByBox.id = 'order-by-box';

    searchOrderBy.append(orderByBox);

    if (locationInfo.order == 1){
        orderByBox.innerHTML += '<label class="order-by-list" for="order-by-time" style="color: var(--main-color)">최신순</label>'
                                + '<label class="order-by-list" for="order-by-star">별점순</label>'
                                + '<label id="order-by-last" class="order-by-list" for="order-by-pick">찜개수순</label></div>'
    } else if (locationInfo.order == 2){
        orderByBox.innerHTML += '<label class="order-by-list" for="order-by-time">최신순</label>'
                                + '<label class="order-by-list" for="order-by-star" style="color: var(--main-color)">별점순</label>'
                                + '<label id="order-by-last" class="order-by-list" for="order-by-pick">찜개수순</label></div>'
    } else if (locationInfo.order == 3){
        orderByBox.innerHTML += '<label class="order-by-list" for="order-by-time">최신순</label>'
                                + '<label class="order-by-list" for="order-by-star">별점순</label>'
                                + '<label id="order-by-last" class="order-by-list" for="order-by-pick" style="color: var(--main-color)">찜개수순</label></div>'
    }

    
    orderByTitle.onclick = showOrderBox;

    // 검색 결과 장소 컨텐츠 박스
    // 검색 결과가 없는 경우
    if (locationInfo.locationList.length == 0){
        let box = document.createElement('div');
        box.classList.add('gray-round-box', 'none-search')
       
        searchResultBox.append(box);

        let text = document.createElement('div');
        text.className = 'none-text'
        text.innerHTML = '검색 결과가 없습니다.'

        box.append(text);

    // 검색 결과가 있는 경우
    } else {
        // 장소 박스 for문
        for(let loc of locationInfo.locationList){
            let searchContentBox = document.createElement('div');
            searchContentBox.classList.add('search-content-box', 'gray-round-box');
            // searchContentBox.onclick = function (){
            //     location.href = contextPath + "/detail?locationNo=" + loc.locationNo;
            // }
            searchResultBox.append(searchContentBox)

            // 좌측 장소 대표 이미지
            if (loc.attachment != null){
                searchContentBox.innerHTML = '<img src="'+ loc.attachment.filePath + loc.attachment.changeName + '" alt="">';
            } else {
                searchContentBox.innerHTML = '<img src="resources/img/default/default_location_img.png" alt="">';
            }
            // 우측 장소 설명 박스
            let searchContent = document.createElement('div');
            searchContent.className = 'search-content';
        
            searchContentBox.appendChild(searchContent);

            // 상단 제목 + 공감 div
            let contentTitle = document.createElement('div');
            contentTitle.className = 'content-title';

            searchContent.appendChild(contentTitle);

            contentTitle.innerHTML = '<span>'+loc.locationName+'</span>';

            // 공감 박스
            let pickBox = document.createElement('div');
            pickBox.className = 'pick-box';
            pickBox.dataset.locno = loc.locationNo;
            contentTitle.append(pickBox);

            let pickImg = document.createElement('img');
            let pickCount = document.createElement('span');
            pickImg.style.cursor = 'pointer';
        
            // pickImg.onclick = function(event){
            //     changePick(loginUser.userNo, loc.locationNo)
            //     event.stopPropagation();
            // }

            pickBox.append(pickImg)
            pickBox.append(pickCount)

            if (loc.userPick > 0){
                pickImg.src = 'resources/img/searchpage/like-after.png'
            } else {
                pickImg.src = 'resources/img/searchpage/like-pre.png'
            }
            pickCount.innerHTML += loc.pickCount;

            // 공감 하트 / 장소 컨텐츠 박스 선택 시 이벤트 처리 (버블링 방지)
            searchContentBox.addEventListener('click', function(event) {
                if (event.target.matches('.pick-box img')) {
                    if (event.target.parentNode.dataset.locno == loc.locationNo){
                        changePick(locationInfo.loginUserNo, loc.locationNo);
                    }
                } else if(event.target.matches('.search-content-box *')){ 
                    location.href = contextPath + "/detail?locationNo=" + loc.locationNo;
                }
            });

            // 장소 내용 Upper
            let upperBox = document.createElement('div');
            upperBox.className = 'content-upper-box';
            searchContent.append(upperBox);

            // 장소 카테고리
            upperBox.innerHTML += '<div><span class="font-bold">분류</span>'
                                    + '<span>'+loc.locationCategoryNo+'</span></div>'
            
            // 평점
            let star = "";
            for (i = 0; i < Math.round(loc.locationStar); i++){
                star += '<img src="resources/img/searchpage/rating-star.png" alt=""> '
            }
            upperBox.innerHTML += '<div><span class="font-bold">평점</span>'
                                        + '<span>'+loc.locationStar+'</span>'
                                        + '<div>' + star + '</div></div>';

            // 반려동물 사이즈
            let sizeList = "";
            if (loc.enterList[0] == null){
                sizeList += "-";
            } else {
                for (let size of loc.enterList){
                    sizeList += size.petSizeNo + ' ';
                }
            }
            upperBox.innerHTML += '<div><span class="font-bold">종류</span><span>'
                                    + sizeList + '</span></div>';

            searchContent.innerHTML += '<hr>';

            // 장소 내용 Lower
            let lowerBox = document.createElement('div')
            lowerBox.className = 'content-lower-box';

            searchContent.append(lowerBox);

            // 장소 주소, 핸드폰 번호
            lowerBox.innerHTML += '<div><img src="resources/img/searchpage/location.png" alt="">'
                                        + '<span>'+loc.address+'</span></div>';
            
            lowerBox.innerHTML += '<div><img src="resources/img/searchpage/phone.png" alt="">'
                                        + '<span>'+loc.locationPhone+'</span></div>'
            
            // 장소 운영시간 (요소만 생성)
            let opTimeBox = document.createElement('div');
            lowerBox.append(opTimeBox);

            let opTimeImg = document.createElement('img');
            opTimeImg.src = 'resources/img/searchpage/time.png';

            opTimeBox.append(opTimeImg);

            let opTimeText = document.createElement('span');
            opTimeText.className = 'operation-time';
            if (loc.opTime == null){
                opTimeText.innerHTML = "-";
            } else {
                if (!loc.opTime.restStatus){
                    opTimeText.dataset.start = loc.opTime.startTime;
                    opTimeText.dataset.end = loc.opTime.endTime;
                    opTimeText.dataset.status = loc.opTime.restStatus;
                    opTimeText.dataset.category = loc.locationCategoryNo;
                } else if (loc.opTime.restStatus){
                    opTimeText.dataset.start = '00:00:00';
                    opTimeText.dataset.end = '00:00:00';
                    opTimeText.dataset.status = loc.opTime.restStatus;
                    opTimeText.dataset.category = loc.locationCategoryNo;
                }
            }
            opTimeBox.append(opTimeText);
        }

        // 페이징 바
        let pagingBar = document.createElement('div');
        pagingBar.id = 'paging-bar';

        searchResultBox.append(pagingBar);

        if (locationInfo.pi.currentPage != locationInfo.pi.startPage){
            let leftBtn = document.createElement('button');
            leftBtn.onclick = function(){
                searchFilterPage(locationInfo.keyword, locationInfo.loginUserNo, (locationInfo.pi.currentPage - 1))
            };

            pagingBar.append(leftBtn);
            leftBtn.innerHTML = "&lt;";
        }

        for (let i = locationInfo.pi.startPage; i <= locationInfo.pi.endPage; i++){
            let pagingBtn = document.createElement('button');
            pagingBtn.onclick = function(){
                searchFilterPage(locationInfo.keyword, locationInfo.loginUserNo, i)
            };

            pagingBar.append(pagingBtn);
            pagingBtn.innerHTML = i;
        }

        if (locationInfo.pi.currentPage != locationInfo.pi.maxPage){
            let rightBtn = document.createElement('button');
            rightBtn.onclick = function(){
                searchFilterPage(locationInfo.keyword, locationInfo.loginUserNo, (locationInfo.pi.currentPage + 1))
            };

            pagingBar.append(rightBtn);
            rightBtn.innerHTML = "&gt;";
        }

        // 운영시간 출력 함수 실행
        (function(){
            operationTime();
        })();
    }
}



// 운영시간 출력 함수
function operationTime(){
    let day = new Date();

    let year = day.getFullYear();
    let month = day.getMonth();
    let date = day.getDate();

    day = day.setTime(day.getTime());

    let spanList = document.querySelectorAll('.operation-time');

    for(let opTime of spanList){
        let start = "";
        let end = "";
        start = opTime.dataset.start;
        end = opTime.dataset.end;

        // 장소 카테고리가 숙소인 경우 (체크인/체크아웃)
        if (opTime.dataset.category == '숙소'){
            // 숙소일 때 1부터 12로 출력되는 오후 시간을 12부터 24로 변환
            let startStr = (Number(start.substr(0, 2)) + 12).toString() + start.substr(2, 3);
            opTime.innerHTML = '체크인 ' + startStr + ' / 체크아웃 ' + end.substr(0, 5);

        // 예외 처리
        } else if (opTime == null){
            opTime.innerHTML = '-'

        // 운영시간 정해져 있는 나머지 장소
        } else {
            // 휴무가 아닌 경우
            if (opTime.dataset.status == 'false'){
                let startTime = new Date(year, month, date, 
                                        start.substr(0, 2),
                                        start.substr(3, 2),
                                        start.substr(6, 2));
                startTime = startTime.setTime(startTime.getTime())
            
                let endTime = new Date(year, month, date, 
                                        end.substr(0, 2),
                                        end.substr(3, 2),
                                        end.substr(6, 2));
                endTime = endTime.setTime(endTime.getTime())
            
                if (day >= startTime && day <= endTime){
                    opTime.innerHTML = '영업 중 ' + end.substr(0, 5) + ' 종료'
                } else {
                    opTime.innerHTML = '영업 종료'
                    opTime.style.color = 'red';
                }
            // 휴무인 경우
            } else if (opTime.dataset.status == 'true'){
                opTime.innerHTML = '휴무'
                opTime.style.color = 'red';
            }
        }
    }
}






// $(".pick-box>img").on("click", function(event){
// 	//event.stopPropagation();
// 	event.stopImmediatePropagation(); //이것만 써주어도 잘 되었다.
// 	//event.preventDefault();
//     alert("kkk");
// });
// [출처] 자바스크립트 이벤트 중첩, 버블링, 이벤트 제거, 내 이벤트만 실행|작성자 dflos






// // 강아지 선택 시 강아지 사이즈 전체 선택, 취소
// function clickAllAgree(){
//     let allButton = document.querySelector("#check-agree-all");
//     let agreeButton = document.querySelectorAll(".check-box>input");

//     for (let agree of agreeButton){
//         agree.checked = allButton.checked;
//     }
// }

// // 강아지 사이즈 누를 때마다 전체 선택 검사
// function clickAgree(){
//     let allButton = document.querySelector("#check-agree-all");
//     let agreeButton = document.querySelectorAll(".check-box>input");

//     let allCheck = true;
    
//     for (let check of agreeButton){
//         if (!check.checked){
//             allCheck = false;
//         }

//         allButton.checked = allCheck;
//     }
// }




// orderBox.style.display = (orderBox.style.display = 'none' ? 'block' : 'none')
    // if (orderBox.style.display == 'none'){
    //     console.log("none")
    //     orderBox.style.display = 'block';
    // } else {
    //     console.log("block")
    //     orderBox.style.display = 'none';
    // }

// function searchFilter(contextPath, keyword){
//     let petList = document.querySelectorAll('.filter-pet');
//     let locList = document.querySelectorAll('.filter-location');
//     // let order = document.querySelector('.order-by-list');
//     // let cpage;

//     let pets = [];
//     for (let p of petList){
//         if (p.checked){
//             pets.push({petSizeNo : p.value});
//         }
//     }

//     let locs = [];
//     for (let l of locList){
//         if (l.checked){
//             locs.push({locationCategoryNo : l.value})
//         }
//     }
//     // let pet = JSON.parse(pets)

//     location.href = contextPath + "/searchKeyword.pl"
//                     + "?keyword=" + "" + keyword
//                     + "&pets=" + pets
//                     + "&locs=" + locs;
// }

// function selectOrder(orderNum){
//     switch(orderNum){
//         case 1 :
//             document.querySelector('#order-by-time').checked = true;
//             break;
//         case 2 :
//             document.querySelector('#order-by-star').checked = true;
//             break;
//         case 3 :
//             document.querySelector('#order-by-pick').checked = true;
//             break;
//     }
// }
