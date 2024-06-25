function clickGood(){

    let goodButton=document.querySelector("#good");
    goodButton.addEventListener("click",function(){
        updateGoodCountAjax(boardNo);
    })
}

function deleteBoard(){

    if(confirm("삭제하시겠습니까?")){
        location.href=contextPath+"/delete.bo?boardNo="+boardNo
    }
}


//수정버튼
function update(){
    location.href=contextPath+"/updateview.bo?boardNo="+boardNo;
}
