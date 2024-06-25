

function getLocationData(data, callback){
    console.log('들어와라')
    $.ajax({
        url: contextPath + "/searchPage.pl",
        data: data,
        success: function (result) {
            callback(result)
            console.log(result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// 좋아요
function ajaxSelectLikeInfo(data, callback){
    $.ajax({
        url: contextPath + "/selectLikeInfo.pl", 
        data: data,
        success: function (result) {
            callback(result)
        },
        error: function () {
            console.log("정보를 불러오는데 실패 했습니다.");
        }
    })
}

// function drawSearchPlace(){

// }