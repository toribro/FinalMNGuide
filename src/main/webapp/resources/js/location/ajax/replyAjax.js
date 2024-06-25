
function insertReplyAjax(reviewNo){
    const reply=document.querySelector("#reply-content"+reviewNo)

    $.ajax({
        type:"POST",
        url:contextPath+"/reply.in",
        data:{
            reviewNo:reviewNo,
            content:reply.value

        },
        success:function(response){
            reviewSelect(drawReivew);
            reply.value=""
        },
        error:function(errors){
            console.log("답글 달기 성공")
        }

    })

}