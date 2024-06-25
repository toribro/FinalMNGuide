



//전체 공간 찜개수 함수
function setPickedCount(count){
    console.log("count:"+count)
    const pickedCount=document.getElementById("count"); 
    pickedCount.innerText=count;
}

//유저 찜 상태

function getUserPickedState(status){
    console.log("status:"+status)
    const heart=document.getElementById("heart");
    if(status==='y'){
        heart.style.color='red';
    }
    else{
        heart.style.color='rgb(255, 215, 222)';
    }
    return status;
}

function setUserPickedState(status){
    console.log("setUSerstatus:"+status)
    const heart=document.getElementById("heart");
    if(status==='y'){
        heart.style.color='red';
      
    }
    else{
        heart.style.color='rgb(255, 215, 222)';
        
    }
    return status;
    
}

