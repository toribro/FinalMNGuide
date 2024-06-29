let contextPath=""
let locationNo=""

const urlStrPicked = window.location.href;
const url = new URL(urlStrPicked);

function init(path,masterKind){
    // if(targetId==="NNNNN"){
    //     targetId="toribro12345";
    //     //userId
    // }
    //contextPath가져오기
    contextPath=path
    const urlParams =url.searchParams
    locationNo= (urlParams.get("locationNo")!=null)?urlParams.get("locationNo"):0;

    //유저가 일반 유저면 사장님 target가져오고 바로 웹소켓(채팅방 열기)
    if(masterKind==='NNNNN'){
       targetId=document.querySelector("#masterId").value
      // roomNo=document.querySelector("#roomNo").value
       targetNo=document.querySelector("#masterNo").value
       openChatRoom(1,targetId,targetNo)
    }
    //사장님일때도 먼저 소켓을 연결한다. (알림창때문)
    else{

        const socket = new WebSocket(contextPath+"/server");
   

        socket.onopen=function(){
            console.log("웹소켓 연결 성공..");
        }
    
        socket.onclose = function(){
            console.log("웹소켓 연결 끊어짐...");
        }
    
        socket.onerr = function(){
            console.log("웹소켓 연결 실패....")
            alert("웹소켓 연결 실패")
        }
    
        //메세지 받기..
        socket.onmessage=function(ev){
            const now =new Date();
            const recevie =JSON.parse(ev.data);
                
            if(!document.querySelector("#chatRoom"+recevie.userNo)){
                drawUserList(recevie);
            }
          


            //유저목록 count 증가 시킨다. 
            if(document.querySelector("#notifyCount"+recevie.userNo)===null){
                let notifyCount=document.createElement("div");
                notifyCount.id="notifyCount"+recevie.userNo
                notifyCount.className="notify"
                notifyCount.innerText=1
                document.querySelector("#notice"+recevie.userNo).appendChild(notifyCount)
            }   
            else{
            document.querySelector("#notifyCount"+recevie.userNo).innerHTML=
            parseInt(document.querySelector("#notifyCount"+recevie.userNo).innerText)+1;
        }
         document.querySelector("#content"+recevie.userNo).innerHTML=recevie.msg;
         document.querySelector("#date"+recevie.userNo).innerHTML=`${now.getFullYear()}-${(now.getMonth()+1)>=10?now.getMonth():'0'+now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`
      }
    
    }
}

//웹소켓 여는 함수
function openChatRoom(roomNo,targetId,targetNo){

   
    let sendButton = document.querySelector("#send-button");
    let msgContainer = document.querySelector("#chatMsg");

    const socket = new WebSocket(contextPath+"/server");
   

    socket.onopen=function(){
        console.log("웹소켓 연결 성공..");
    }

    socket.onclose = function(){
        console.log("웹소켓 연결 끊어짐...");
    }

    socket.onerr = function(){
        console.log("웹소켓 연결 실패....")
        alert("웹소켓 연결 실패")
    }

    socket.onmessage=function(ev){

        const now = new Date();	
        const recevie =JSON.parse(ev.data);
     
        let sendUserNo=parseInt(document.querySelector("#userKey").value);

        //자신에게 오는 메세지면 메세지를 띄원다.
        if(recevie.userNo===sendUserNo){
         

            msgContainer.innerHTML+=`
                <div  class="send-master">
                                <div class="master-profile">
                                    <div class="img-div">
                                        <img src="resources/img/default/default_profile.jpg">
                                    </div>
                                    <div class="master-name title">${(recevie.nick!==undefined)?recevie.nick:"사장님"}</div>
                                </div>
                                <div class="content master-content master-color">
                                        <div> ${recevie.msg} </div>
                                    <div class="time">${now.getFullYear()}-${(now.getMonth()+1)>=10?now.getMonth():'0'+now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}</div>
                                </div>
                        </div>
            `

            //메세지 타겟 주기
             msgContainer.lastElementChild.scrollIntoView({behavior: 'smooth'})
        }

        else{

            //자신한테 오는 메세지가 아닐때 만약에 새로참가한 유저이면 유저리스트 생성
            if(!document.querySelector("#chatRoom"+recevie.userNo)){
                drawUserList(recevie);
            }

            //자신한테 오는 메세지가 아니면 카운트표시만 증가시킨다.
            //유저목록 count 증가 시킨다. 
            //let userNo=document.querySelector("#userKey").value
            if(document.querySelector("#notifyCount"+recevie.userNo)===null){
                let notifyCount=document.createElement("div");
                notifyCount.id="notifyCount"+recevie.userNo
                notifyCount.className="notify"
                notifyCount.innerText=1
                document.querySelector("#notice"+recevie.userNo).appendChild(notifyCount)
            }   
            else{
                document.querySelector("#notifyCount"+recevie.userNo).innerHTML=
                parseInt(document.querySelector("#notifyCount"+recevie.userNo).innerText)+1;
            }
                document.querySelector("#content"+recevie.userNo).innerHTML=recevie.msg;
                document.querySelector("#date"+recevie.userNo).innerHTML=`${now.getFullYear()}-${(now.getMonth()+1)>=10?now.getMonth():'0'+now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`

        }


    }


    //엔터키 누르면 실행
    let input=document.querySelector("input[name=msg]");

     input.addEventListener("keyup",function(e){
        if(e.keyCode===13){
            e.preventDefault();
            document.querySelector("#send-button").click();
            e.preventDefault();
        }
    })

    //채팅 보내기 버튼을 클릭시 실행되는 함수
    sendButton.onclick=function(){
        let sendMsg=document.querySelector("input[name=msg]");
        if(sendMsg.value===""){
            return false;
        }
        const now = new Date();
        const msgData = {
           message:sendMsg.value,
           target:targetId,
           roomNo:roomNo,
           targetNo:targetNo,
           locationNo:locationNo
       }



       //소켓에서 메세지 받아오는 메서드
       socket.send(JSON.stringify(msgData));
       
           //유저 넘버에따라서 출력하기?
           //특정 유저 메세지만 출력해주기
           // 클릭했을때 키값을 넘기면 된다.  

       
            msgContainer.innerHTML+=`
                   <div class="send-user">
                           <div>To:${targetId}</div>
                           <div class="content  user-content user-color">
                               <div>${sendMsg.value}</div>
                               <div class="time">${now.getFullYear()}-${(now.getMonth()+1)>=10?now.getMonth():'0'+now.getMonth()}-${now.getDate()} ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}</div>
                           </div>
                   </div>
   
               `
            sendMsg.value=""

        //메세지 타겟 주기
        msgContainer.lastElementChild.scrollIntoView({behavior: 'smooth'})

    }

   

  
}



////////
//채팅방 선택(현재 내가 사장일때)
function chooseChatRoom(roomNo,id){
 //선택시 채팅 목록에 유저 넘버 키값 부여하기?
  document.querySelector("#userKey").value=id;
  let masterNo=document.querySelector("#master-No");

   let userId=document.querySelector("#userId"+id);
   let userNo=document.querySelector("#userNo"+id);
   targetId=userId.value;
   targetNo=userNo.value;

   
   //유저닉네임 채팅목록에 띄워주기
   document.querySelector("#chattingUser").innerText=document.querySelector("#userNickName"+id).innerText;
   // 채팅 목록 불러와서 먼저 띄워주기
    onloadChatList({
        userNo:masterNo.value,
        targetNo:userNo.value
    },drawChatList);

    openChatRoom(roomNo,targetId,targetNo);
    
}

//채팅 리스트 그리기
function drawChatList(chatList){

  let chatDiv=document.querySelector("#chatMsg");
  let content="";
   
   for(let chat of chatList ){
     

       if(chat.userNo=== parseInt(document.querySelector("#master-No").value)){
         content+=`
            	<div class="send-user">
						<div class="content  user-content user-color">
								<div>${chat.message}</div>
									<div class="time">${chat.enrollTime}</div>
								</div>
						</div>
                 </div>
         `
       }else{
         content+=`
            <div class="send-master">
				<div class="master-profile">
					<div class="img-div">
						<img src="resources/img/default/default_profile.jpg">
					</div>
					 <div class="master-name title">${chat.userNickName}</div>
				</div>
				<div  class="content master-content master-color">
					    <div>${chat.message}</div>
						<div class="time">${chat.enrollTime}</div>
				</div>
			</div>
         
         `
       }

    }

    chatDiv.innerHTML=content;



}


//유저채팅 목록 그리기
function drawUserList(userList){
   const chatDiv=document.querySelector("#chat-div");
   let userListContent="";
  
   userListContent=`
        <div  id="chatRoom${userList.userNo}" class="chat-grid chat-list-area" onclick="chooseChatRoom(1,'${userList.userNo}')">
								<div class="profile-area">
									<div class="img-div">
										<img src="resources/img/default/default_profile.jpg">
									</div>
									<div class="profile-list">
										<input id="userId${userList.userNo}" type="text"  value="${userList.id}" hidden>
										<input id="userNo${userList.userNo}" type="text"  value="${userList.userNo}" hidden>
										<div id="userNickName${userList.userNo}" class="title">${userList.nick}</div>
										<div id="content${userList.userNo}">${userList.msg}</div>
									</div>
								</div>
								
								<!--실시간 처리-->
								<div id="notice${userList.userNo}" class="notice">
									<div id=date${userList.userNo}  class="date">${userList.time}</div>
								
									<div id="notifyCount${userList.userNo}" class="notify">0</div>
									
								</div>
							</div>
   
   
   `
   chatDiv.innerHTML+= userListContent;
}


function onloadChatList(data,callback){
 
    $.ajax({
        url:contextPath+"/view.chat",
        data:data,
        success:function(response){
            console.log(response)
            callback(response)
            if(document.querySelector("#notifyCount"+data.targetNo)){
                document.querySelector("#notifyCount"+data.targetNo).remove();
                document.querySelector("#content"+data.targetNo).innerHTML="";
                document.querySelector("#date"+data.targetNo).innerHTML="";
            }
          
        },
        error:function(){
            alert("에러발생")
        }
    })

  
}

function moveToLocation(){
    location.href=contextPath+"/detail?locationNo="+locationNo;
}

function deleteChat(){
    if(confirm("나가시겠습니까?")){
        location.href='delete.chat?locationNo='+locationNo
      }else{
        return false;
      }
}