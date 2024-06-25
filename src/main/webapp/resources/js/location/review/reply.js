

function onReplyOnClick(reviewNo){

   if(userNo==-1){
      alert("로그인을 먼저 해주세요");
      return false;
   }
  
   let replyContent=document.querySelector("#master-reply-input-div"+reviewNo)
   if(replyContent.style.display==='block'){
         replyContent.style.display='none'
   }else{
        replyContent.style.display='block'
   }
   

}
function onReplyShow(reviewNo){
      let replyContent=document.querySelector("#master-reply-content"+reviewNo)
      if(replyContent.style.display==='block'){
            replyContent.style.display='none'
      }else{
           replyContent.style.display='block'
      }
      
}