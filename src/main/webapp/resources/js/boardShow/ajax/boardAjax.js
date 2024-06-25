

function updateGoodCountAjax(boardNo){
     
    $.ajax({
        url:contextPath+'/updategoodcount.bo',
        type:"GET",
        data:{
            boardNo:boardNo
        },
        success:function(response){
            if(response.message!=="fail"){
                alert(response.message)
                document.querySelector("#goodCount").innerHTML=parseInt(response.goodCount);
            }
            else{
                alert("로그인을하세요")
            }
          
          
        },
        error:function(){
            alert("공감실패")
        }
    })



}