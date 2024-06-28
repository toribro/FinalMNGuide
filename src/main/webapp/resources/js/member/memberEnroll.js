// 스크립트 파일을 실행할 때 우선 스크립트 함수 제목만 쭉 기억함

// ###공통 회원가입###
// 전체 동의 선택 시 전체 선택, 취소
function clickAllAgree(){
    let allButton = document.querySelector("#check-agree-all");
    let agreeButton = document.querySelectorAll(".check-box>input");

    for (let agree of agreeButton){
        agree.checked = allButton.checked;
    }
}

// 네 가지 동의 누를 때마다 전체 선택 검사
function clickAgree(){
    let allButton = document.querySelector("#check-agree-all");
    let agreeButton = document.querySelectorAll(".check-box>input");

    let allCheck = true;
    
    for (let check of agreeButton){
        if (!check.checked){
            allCheck = false;
        }

        allButton.checked = allCheck;
    }
};


// 핸드폰 번호 정규식 검사
function checkDoublePhone(){
    let phone = document.querySelector('[name="userPhone"]');
    let phoneMessage = document.querySelector('#phone-message');
    let phoneCheck = document.querySelector('#phone-check');
    let certify = document.querySelector('#certify-phone-button');

    phone.dataset.check = 'false'
    phoneCheck.dataset.check = 'false'
    const regExp = /^[0-9]{9,12}$/;
    console.log(phone.dataset.check)
    console.log(phoneCheck.dataset.check)
    if(!regExp.test(phone.value)){
        if (phone.value == ""){
            changeStyle("X", phoneCheck, phoneMessage, "");
            changeStyle("X", certify, phoneMessage, "");
        } else {
            changeStyle("N", phoneCheck, phoneMessage, "사용할 수 없는 형식의 번호입니다.");
            changeStyle("N", certify, phoneMessage, "사용할 수 없는 형식의 번호입니다.");
        }
    } else {
        changeStyle("Y", phoneCheck, phoneMessage, "사용 가능한 형식의 번호입니다.");
    }
}

// 번호 중복 검사 ajax 실행 트리거 함수
function checkPhoneNumber(){
    let phone = document.querySelector('[name="userPhone"]').value;
    ajaxCheckPhoneNumber({userPhone:phone}, drawDoublePhoneResult)
}

// 번호 중복 검사 성공 시 콜백 함수
function drawDoublePhoneResult(result){
    let phoneMessage = document.querySelector('#phone-message');
    let phoneCheck = document.querySelector('#phone-check');
    let phone = document.querySelector('[name="userPhone"]')

    if (result == "NNNNY"){
        changeStyle("N", phoneCheck, phoneMessage, "이미 가입된 전화번호입니다.");
    } else if (result == "NNNNN"){
        changeStyle("Y", phoneCheck, phoneMessage, "사용 가능한 전화번호입니다.");
        // 인증 성공 시로 옮겨야 함
        phoneCheck.dataset.check = 'true';
        activeCertifyPhone();
    }
}

// 인증번호 요청 ajax 실행 트리거 함수
function getCertifyCode(){
    let getNum = document.querySelector('[name="userPhone"]').value;
    ajaxCertifyPhoneNum({getNum: getNum}, drawGetCertifySuccess);
}

// 인증번호 요청 ajax 성공 시 콜백 함수
function drawGetCertifySuccess(result){
    // 타이머 돌아가는 함수, 시간 지나면 데이터 삭제하는 함수
    console.log(result)
}

// 인증번호 입력 후 확인 시 ajax 실행 트리거 함수
function checkCertifyCode(){
    let certifyCode = document.querySelector('#certify-code').value;
    let phone = document.querySelector('[name="userPhone"]').value;

    ajaxCheckCertifyCode({phone:phone, certifyCode:certifyCode}, drawCertifyCodeSuccess);
}

// 인증번호 확인 ajax 성공 시 콜백 함수
function drawCertifyCodeSuccess(result){
    let dummy = document.querySelector('button');
    let certifyMessage = document.querySelector('#certify-message');
    
    if (result == "NNNNY"){
        document.querySelector('[name="userPhone"]').disabled = true;
        document.querySelector('#after-certify-box').style.display = 'none';
        document.querySelector('#checked-phone-text').style.display = 'block'
        document.querySelector('[name="userPhone"]').dataset.check = 'true'

        if (userKind == 'N'){
            activeCommonEnroll();
        } else if(userKind == 'Y'){
            activeBossEnroll();
        }

    } else if (result == "NNNNN"){
        changeStyle("N", dummy, certifyMessage, "인증번호가 일치하지 않습니다.");
    }
}







// 아이디 중복 확인
function checkDoubleId(){
    let userIdInput = document.querySelector("#user-id");
    ajaxSelectDoubleId({userId : userIdInput.value}, checkIdSuccess);
}

// 아이디 중복 확인 성공 시 실행
function checkIdSuccess(result) {
    let id = document.querySelector("[name='userId']");
    let idMessage = document.querySelector("#id-message");
    let dummy = document.querySelector('button');
    
    if (result == "NNNNY"){
        changeStyle("N", dummy, idMessage, "이미 가입된 아이디입니다.");
    } else {
        changeStyle("Y", dummy, idMessage, "사용 가능한 아이디입니다.");
        id.dataset.check = 'true';

        if (userKind == 'N'){
            activeCommonEnroll();
        } else if(userKind == 'Y'){
            activeBossEnroll();
        }
    }
    
}




// 아이디 정규식 검사
function checkId(){
    let id = document.querySelector("[name='userId']");
    let idMessage = document.querySelector("#id-message");
    let idCheck = document.querySelector('#id-check')

    id.dataset.check = 'false'
    
    if (userKind == 'N'){
        activeCommonEnroll();
    } else if(userKind == 'Y'){
        activeBossEnroll();
    }

    const regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+~\-={}[\]:;"'<>,.?/|\\`]{8,14}$/;
    
    if(!regExp.test(id.value)){
        if (id.value == ""){
            changeStyle("X", idCheck, idMessage, "");
        } else {
            changeStyle("N", idCheck, idMessage, "사용할 수 없는 형식의 아이디입니다.");
        }
    } else {
        changeStyle("Y", idCheck, idMessage, "사용 가능한 형식의 아이디입니다.");
    }
}



// 비밀번호 정규식 검사
function checkPwd(){
    let pwd = document.querySelector("[name='userPwd']");
    let pwdMessage = document.querySelector("#pwd-message");
    let dummy = document.querySelector('button');

    const regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+~\-={}[\]:;"'<>,.?/|\\`]{8,14}$/;
    
    if(!regExp.test(pwd.value)){
        if (pwd.value == ""){
            changeStyle("X", dummy, pwdMessage, "");
        } else {
            changeStyle("N", dummy, pwdMessage, "사용할 수 없는 형식의 비밀번호입니다.");
        }
    } else {
        changeStyle("Y", dummy, pwdMessage, "사용 가능한 비밀번호입니다.");
    }
}


// 비밀번호 확인 일치 검사
function checkPwdCorrect(){
    let pwd = document.querySelector("[name='userPwd']");
    let pwdCheck = document.querySelector("#pwd-check");
    let pwdMessage = document.querySelector("#pwd-check-message")
    let dummy = document.querySelector('button');
    
    if ((pwd.value == pwdCheck.value && pwdCheck.value != "") || pwdCheck.value == ""){
        changeStyle("X", dummy, pwdMessage, "");
    } else if (pwd.value != pwdCheck.value){
        changeStyle("N", dummy, pwdMessage, "비밀번호가 일치하지 않습니다.");
    }
}


// 중복 체크 있는 정규식 검사 스타일 바꾸는 함수
function changeStyle(able, button, message, text){
    button.dataset.check = 'false'
    if (able == "X"){
        message.innerHTML = "";
        button.disabled = true;
    } else if (able == "Y"){
        button.disabled = false;
        button.style.color = 'black';
        button.style.cursor = 'pointer';

        message.innerHTML = text;
        message.style.color = 'black';
    } else if (able == "N"){
        button.disabled = true;
        button.style.color = 'var(--border-color)'
        button.style.cursor = 'inherit';

        message.innerHTML = text;
        message.style.color = 'red';
    }
}





// 여자 남자 선택
function selectGender(gender){
    let selectF = document.querySelector("#select-f");
    let selectM = document.querySelector("#select-m");
    let radioF = document.querySelector("#radio-f");
    let radioM = document.querySelector("#radio-m");

    if (gender == "F"){
        selectFemail(radioF, selectF, selectM);
    } else if (gender == "M"){
        selectMale(radioM, selectF, selectM);
    }

}

function selectFemail(radioF, selectF, selectM){
    radioF.checked = true;
    selectF.style.border = "1px solid var(--main-color)";
    selectF.style.color = "var(--main-color)";
    selectM.style.border = "1px solid var(--border-color)";
    selectM.style.color = "black";
}

function selectMale(radioM, selectF, selectM){
    radioM.checked = true;
    selectM.style.border = "1px solid var(--main-color)";
    selectM.style.color = "var(--main-color)";
    selectF.style.border = "1px solid var(--border-color)";
    selectF.style.color = "black";
}


// 이메일 select 박스 선택 시 input 창 값 변경 및 활성화 여부
function selectPostEmail(){
    let selectInput = document.querySelector("#email-select-box");
    let postInput = document.querySelector("#email-post");

    postInput.value = selectInput.value;

    if (selectInput.value == "직접입력"){
        postInput.disabled = false;
    } else {
        postInput.disabled = true;
    }

    changeEmail();
}

// 이메일 select 박스, input 바뀌는 value로 서버에 넘길 input value 실시간 수정
function changeEmail(){
    let preInput = document.querySelector("#email-pre");
    let postInput = document.querySelector("#email-post");
    let userEmail = document.querySelector("[name='userEmail']");
    userEmail.value = preInput.value + "@" + postInput.value;
}

// 모든 항목 기입 시 버튼 활성화
function activeEnroll(userInfo, userId, userPhone, enroll){
    let checkDisable = false;
    let result = 1;
    let phoneResult = 1;
    // console.log(userInfo)
    for (let info of userInfo){
        if (info.value == "" || info.value == null){
            result = result * 0;
            phoneResult = 0;
        }
    }

    let pwdCheck = document.querySelector('#pwd-check');
    if (userInfo[0].value != pwdCheck.value){
        result = result * 0;
    }

    console.log(pwdCheck.value)
    console.log(userInfo[0].value)

    if (userId.dataset.check == 'false'){
        result = result * 0;
    }

    if (userPhone.dataset.check == 'false'){
        result = result * 0;
    }

    let agree = document.querySelector("#check-agree-all");
    if (!agree.checked){
        result = result * 0;
    }

    if (result == 0){
        checkDisable = true;
    }

    enroll.disabled = checkDisable;
    if (!checkDisable){
        enroll.style.background = "var(--main-color)";
        enroll.style.color = "#FFFFFF";
        enroll.style.cursor = "pointer";
        userPhone.disabled = false;
    } else {
        enroll.style.background = "inherit";
        enroll.style.color = "var(--border-color)";
        enroll.style.cursor = "inherit";
        if (phoneResult == 1){
            userPhone.disabled = true;
        }
        
    }
}

// 번호 기입 후 약관 전체 동의 시 번호 인증 버튼 활성화
function activeCertifyPhone(){
    let checkDisable = false;
    let result = 1;

    let certifyButton = document.querySelector('#certify-phone-button')
    
    let phoneCheck = document.querySelector('#phone-check');
    if (phoneCheck.dataset.check == 'false'){
        result = result * 0;
    }
    
    let agree = document.querySelector("#check-agree-all");
    if (!agree.checked){
        result = result * 0;
    }

    if (result == 0){
        checkDisable = true;
    }

    certifyButton.disabled = checkDisable;
    if (!checkDisable){
        certifyButton.style.background = "white";
        certifyButton.style.cursor = "pointer";
        certifyButton.style.color = "black";
    } else {
        certifyButton.style.background = "inherit";
        certifyButton.style.color = "var(--border-color)";
        certifyButton.style.cursor = "inherit";
    }
}



// function enrollStylePink(enroll){
//     enroll.style.background = "var(--main-color)";
//     enroll.style.color = "#FFFFFF";
// }

// function enrollStyleInit(enroll){
//     enroll.style.background = "inherit";
//     enroll.style.color = "var(--border-color)";
// }


// ###일반 회원가입###

// 일반 회원가입 버튼 활성화 데이터
function activeCommonEnroll(){
    let userId = document.querySelector("[name='userId']");
    let userPwd = document.querySelector("[name='userPwd']");
    let userName = document.querySelector("[name='userName']");
    let userNickname = document.querySelector("[name='userNickname']");
    let userGender = document.querySelector("[name='userGender']");
    let userBirthday = document.querySelector("[name='userBirthday']");
    let userPreEmail = document.querySelector("#email-pre");
    let userPostEmail = document.querySelector("#email-post");
    let userPhone = document.querySelector("[name='userPhone']");
    let enroll = document.querySelector("#enroll-button");

    let userInfo = [userPwd, userName, userNickname, userGender, userBirthday, userPreEmail, userPostEmail];

    activeEnroll(userInfo, userId, userPhone, enroll);
}


// ###사장 회원가입###

// 주소 실시간 수정
function changeAddress(){
    let content = document.querySelector("#address-content");
    // let detail = document.querySelector("#address-detail");
    let userAddress = document.querySelector("[name='address']");
    userAddress.value = content.value
    console.log(userAddress)
    // userAddress.value = content.value + " " + detail.value;
}

// 사장 회원가입 버튼 활성화 데이터
function activeBossEnroll(){
    let userId = document.querySelector("[name='userId']");
    let userPwd = document.querySelector("[name='userPwd']");
    let userName = document.querySelector("[name='userName']");
    let userGender = document.querySelector("[name='userGender']");
    let userBirthday = document.querySelector("[name='userBirthday']");
    let userPreEmail = document.querySelector("#email-pre");
    let userPostEmail = document.querySelector("#email-post");
    let userPhone = document.querySelector("[name='userPhone']");

    let businessNo = document.querySelector("[name='businessNo']");
    let locationName = document.querySelector("[name='locationName']");
    let addressContent = document.querySelector("#address-content");
    // let addressDetail = document.querySelector("#address-detail");

    let enroll = document.querySelector("#enroll-button");

    // let userInfo = [userPwd, userName, userGender, userBirthday, userPreEmail, userPostEmail,
    //                 businessNo, locationName, addressContent, addressDetail];

    let userInfo = [userPwd, userName, userGender, userBirthday, userPreEmail, userPostEmail,
        businessNo, locationName, addressContent];

    activeEnroll(userInfo, userId, userPhone, enroll);
}


// 사업자 등록 번호 인증
function certifyBusinessNo(){
    let businessNo = document.querySelector('[name=businessNo]').value;
    ajaxCertifyBusinessNo({businessNo: businessNo}, drawSuccessBusinessNo);
}

// 사업자 등록 번호 인증 ajax 성공 시
function drawSuccessBusinessNo(result){
    let message = document.querySelector('#business-no-message')

    if (result == "NNNNY"){
        document.querySelector('#check-admin-button').style.display = 'none';
        document.querySelector('#checked-boss-info').style.display = 'block';
        document.querySelector('[name="businessNo"]').dataset.check = 'true';
        message.innerHTML = "";

        activeBossEnroll();
    } else if (result == "NNNNN"){
        message.innerHTML = "유효한 사업자 등록 번호가 아닙니다.";
    }
}



// 우편번호 주소 api
function showAddress(){
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // // 조합된 참고항목을 해당 필드에 넣는다.
                    // document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address-zipCode').value = data.zonecode;
                document.getElementById("address-content").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                // document.getElementById("address-detail").focus();
            }
        
    }).open();
}