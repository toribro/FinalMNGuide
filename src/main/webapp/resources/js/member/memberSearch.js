function checkPhoneNumber(){
    let phone = document.querySelector('[name="userPhone"]').value;
    ajaxCheckPhoneNumber({userPhone:phone}, drawDoublePhoneResult)
}

// phone.value로 안 해서 maximum call stack size exceeded 오류 남

function drawDoublePhoneResult(result){
    let phone = document.querySelector('[name="userPhone"]').value;
    let message = document.querySelector('#check-number-message');

    console.log(result)
    // 중복 되는 번호 있음
    if (result == "NNNNY"){
        message.innerHTML = "";
        ajaxCertifyPhoneNum({getNum:phone}, drawGetCertifySuccess)
    } else if (result == "NNNNN"){
        message.innerHTML = "가입 되어 있는 번호가 아닙니다.";
    }
}

function drawGetCertifySuccess(result){
    console.log('성공')
    // let enrollBtn = document.querySelector('#enroll-button')
    // enrollBtn.disabled = false;
    // enrollBtn.style.backgroundColor = 'var(--main-color)';
    // enrollBtn.style.cursor = ''
    // 시간 관련한 함수들
}