// function moveShortsInfo(event){
//     if (event.target.matches('.pick-box img')) {
//         if (event.target.parentNode.dataset.locno == locationNo){
//             changePick(loginUserNo, locationNo);
//         }
//     } else if(event.target.matches('.search-content-box *')){ 
//         location.href = contextPath + "/detail?locationNo=" + locationNo;
//     }
// }

// transition: right 0.3s ease;

function showReplyBox(ev){
    let replyBox = document.querySelector('.shorts-reply-box');

    if (ev.dataset.check == "N"){
        replyBox.style.display = "block"
        ev.dataset.check = "Y"
    } else if (ev.dataset.check == "Y"){
        replyBox.style.display = "none"
        ev.dataset.check = "N"
    }
}