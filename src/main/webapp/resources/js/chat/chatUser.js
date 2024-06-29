function moveGpt(){
  let kind=document.querySelector("#kind").value;
  location.href='gpt.view?prompt='+kind

}
function deleteChat(locationNo){
  if(confirm("나가시겠습니까?")){
    location.href='delete.chat?locationNo='+locationNo
  }else{
    return false;
  }
  
}