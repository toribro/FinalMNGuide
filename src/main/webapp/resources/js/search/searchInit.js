let contextPath;
let loginUserNo;

// body onload 시 실행되는 초기 함수
function init(path, userNo){
    contextPath = path;
    loginUserNo = userNo;
    console.log(loginUserNo)
    operationTime();
    // drawSearchPage();
    // document.querySelector('.like-btn').click();
}