// 현재 세션의 userNo로 등록된 반려동물 정보가 있는지 검사
function selectRegistPetModal(data, callback){
    $.ajax({
        url: contextPath + "/registPetModal.ma",
        data: data,
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 식당, 카페, 숙소 중 별점이 높은 장소 하나씩 가져옴
function selectPlaceTop(data, callback){
    $.ajax({
        url: contextPath + "/topPlace.ma", 
        data: data,
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 인기 조회글, 인기 추천글, 댓글 최다순
function selectBoardDetailTop(type, data, callback){
    $.ajax({
        url: contextPath + "/topBoard.ma?type=" + type,
        data: data,
        success: function (result) {
            callback(type, result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 조회수 높은 쇼츠
function selectShortsTop(data, callback){
    $.ajax({
        url: contextPath + "/topShorts.ma", 
        data: data,
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}