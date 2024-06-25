const urlStrPicked = window.location.href;
const url = new URL(urlStrPicked);

let contextPath;
let userNo;
let spaceNo;
let statusResult='';
let checkedMaster;

//초기화 함수
 function init(path,userNum, checkMaster){
    
    contextPath=path;
    userNo=userNum;
    checkedMaster=checkMaster
    //path와 유저,공간번호 초기화
    const urlParams =url.searchParams
    spaceNo=urlParams.get('locationNo')
    console.log(spaceNo);
    console.log(path)
  //로그인 정보 가져오는 비동기 함수


   ///찜

    //페이지 로딩될때 이공간에대한 전체 찜개수 먼저 불러온다.
     pickedCount({locationNo:spaceNo},(count)=>{
      setPickedCount(count);
  });

   //페이지 로딩될때 유저의 찜한 상태를 가져온다.
   pickedState({locationNo:spaceNo,userNo:userNo},(status)=>{
     statusResult= getUserPickedState(status);//상태 초기화
     //하트 클릭시 유저가 찜하거나 찜해제한다. (상태 검사부터) 
     
   })

   
   //하트를 누르면 하트개수 추가
   clickHeart({locationNo:spaceNo,userNo:userNo},pickedCount);

   //review작성 함수
   reviewInsert();

}


//로그인정보 가져오기
//  async function getLoginUser(contextPath){ 
  
//     try {
//       const response = await fetch(contextPath+"/user.ge"); // 로그인 정보를 가져올 API 엔드포인트
//       if (!response.ok) {
//           throw new Error('네트워크에러');
//       }
//         loginInfo = await response.json();
//         console.log("비동기"+loginInfo);
//         //parseInt(loginInfo);
//         //console.log(userNo);
//         return parseInt(loginInfo);
//     } catch (error) {
//          console.error('정보를가져오지 못했습니다.', error);
//       return -1;
//     }
     
// }

  //   let userNum=-1;



  




  function getLoginUser(contextPath){

    $.ajax({
      type:"GET",
      url:contextPath+"/user.ge",
      success:function(response){
        console.log("비동기로그인:",response)
        userNo=parseInt(response);
        console.log("response"+userNo)
      },
      error:function(){
          console.log("로그인 여부 조회 실패")
      }
    
    })
  
}



 

