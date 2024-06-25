/**찜 관련 ajax함수 */

//클릭함수
function clickHeart(data,pickedCount){
     //console.log("클릭함수호출:"+data.status);
    
    const heart=document.getElementById("heart");
    heart.onclick=()=>{
        if(userNo==-1){
            alert("로그인을 먼저 해주세요")
            return false;
         }
    
       // console.log("클릭됨"+data.status);
        userPicked(data,(status)=>{

            setUserPickedState(status);

            //유저가 찜하면 전체 찜목록을 다시 한번 업데이트 해준다.
            pickedCount({locationNo:spaceNo},
                (count)=>{
                    setPickedCount(count);
            });
        });
    }

   
}


//이공간 전체찜 개수 가져오는 비동기 함수
function pickedCount(data,callback){
    console.log("pickCout"+data);
    $.ajax({
        type:"post",
        url:contextPath+"/count",
        headers :{
            "Content-Type" :"application/json",
        },
        data:JSON.stringify(data),
        success:function(result){
            console.log("pickedCount")
            callback(result)
        }

    })
}

//찜 상태 가져오는 비동기 함수

function pickedState(data,callback){
  
    $.ajax({
        type:"post",
        url:contextPath+"/state",
        headers :{
            "Content-Type" :"application/json",
        },
        data: JSON.stringify(data),
        success:function(result){
            console.log("picekdState")
            console.log("statusResult:"+result)
            callback(result)
        },
        error:function(error){
            console.log(error)
        }

    })
}

//찜 하고 해제하기 -> 상태 반환
function userPicked(data,callback){
    //console.log("insert:"+data.status);
    $.ajax({
        type:"post",
        url:contextPath+"/pick",
        headers :{
            "Content-Type" :"application/json",
        },
        data: JSON.stringify(data),
        success:function(result){
            console.log("picked")
            if(result=='y'){
                alert("공감되었습니다.")
            }
            else{
                alert("공감해제되었습니다.")
            }
            callback(result)
        },
        error:function(error){
            console.log(error)
        }

    })

}