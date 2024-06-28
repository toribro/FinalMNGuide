// 아이디 중복 확인
function ajaxSelectDoubleId(data, callback){
    $.ajax({
        url: contextPath + "/checkId.me", 
        data: data,
        success: function (result) {
            callback(result);
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 전화번호 중복 확인
function ajaxCheckPhoneNumber(data, callback){
    $.ajax({
        url: contextPath + "/checkPhone.me", 
        data: data,
        success: function (result) {
            callback(result);
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}


// 번호 인증 요청
function ajaxCertifyPhoneNum(data, callback){
    $.ajax({
        url: contextPath + "/certification.me", 
        data:data,
        type: "post",
        success: function (result) {
            callback(result);
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 인증번호 입력 후 일치 확인
function ajaxCheckCertifyCode(data, callback){
    $.ajax({
        url: contextPath + "/checkCertifyCode.me", 
        data:data,
        type: "post",
        success: function (result) {
            callback(result);
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 사업자 등록 번호 인증
function ajaxCertifyBusinessNo(data, callback){
    $.ajax({
        url: contextPath + "/certifyBusinessNo.me", 
        data: data,
        success: function (result) {
            callback(result);
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}