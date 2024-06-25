const urlStrPicked = window.location.href;
const url = new URL(urlStrPicked);
let boardNo;
let contextPath;
let userNickName;
let userNo;

let userFilePath=""
let userChangeName=""

function init(path,userNick,userNum,filePath,changeName){
    console.log(path)

    contextPath=path
    userNickName=(userNick==="")?"로그인하세요":userNick
    userNo=(userNum==="")?-1: parseInt(userNum)
    const urlParams =url.searchParams
    boardNo=urlParams.get('bno')

    userFilePath=filePath;
    userChangeName=changeName

    //답글 입력
    replyInsert();

    //좋아요
    clickGood();
}


