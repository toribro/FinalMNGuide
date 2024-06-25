function pagIngBoard(currentPage,category=0,boardContent="default"){

    $.ajax({
        url:contextPath+"/boards",
        data:{
            boardPageNo:currentPage,
            boardCategoryNo:category,
            boardContent:boardContent
        },
        success:function(response){
            drawBoards(response.boards)
            drawBoardPage(response.page,category,boardContent)
        },
        error:function(){
            console.log("에러")
        }

    })

}

function categoryAjax(category){
    $.ajax({
        url:contextPath+"/category",
        data:{
            boardCategoryNo:category
        },
        success:function(response){
            drawBoards(response.boards)
            drawBoardPage(response.page,category)
        },
        error:function(){
            console.log("에러")
        }

    })
}

function boardSearchAjax(category,boardContent){

 
    $.ajax({
        url:contextPath+"/search",
        data:{
            boardCategoryNo:category,
            boardContent:boardContent
        },
        success:function(response){
            drawBoards(response.boards)
            drawBoardPage(response.page,category,boardContent)
        },
        error:function(){
            console.log("에러")
        }

    })
}