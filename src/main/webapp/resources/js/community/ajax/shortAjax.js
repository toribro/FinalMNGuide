

function pagIngShorts(currentPage){

    $.ajax({
        url:contextPath+"/shorts",
        data:{
            shortsPageNo:currentPage
        },
        success:function(response){
            drawShorts(response.shorts)
            drawShortsPage(response.page)
        },
        error:function(){
            console.log("에러")
        }



    })

}