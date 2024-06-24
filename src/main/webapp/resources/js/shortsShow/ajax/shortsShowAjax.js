// 댓글 가져오는 함수
$(document).on('click', '[id^="show-reply-btn"]', function () {

    const num = parseInt(this.id.replace('show-reply-btn', ''));

    $.ajax({
        url: contextPath + '/loadReply.sh',
        data: {
            num: num
        },
        dataType : "json",
        success: function (response) {
            const replyList = response;
            console.log(replyList);

            if (replyList.length === 0) {
                const newComment = $('<div class="no-reply"></div>').text("아직 댓글이 없어요ㅠㅠ");
                $('#comments-list' + num).append(newComment);
            } else {
                $('#comments-list' + num).empty();
                for (let i = 0; i < replyList.length; i++) {
                    const newCommentContainer = $('<div class="comment-container"></div>');

                    // 프로필 사진을 담는 div
                    const profilePicDiv = $('<div class="profile-pic"></div>');
                    const profilePicImg = $('<img class="profile-img">');
                    profilePicImg.attr('src', replyList[i].filePath + replyList[i].changeName);
                    profilePicDiv.append(profilePicImg);
                    newCommentContainer.append(profilePicDiv);

                    // 사용자 정보와 댓글 내용을 담는 div
                    const commentInfoDiv = $('<div class="comment-info"></div>').text(replyList[i].userNickname + " : " + replyList[i].replyContent + " - " + replyList[i].enrollDate);
                    newCommentContainer.append(commentInfoDiv);

                    $('#comments-list' + num).append(newCommentContainer);
                }
            }
        },
        error: function () {
            console.log("댓글 로드 실패");
        }
    });
});


// 댓글 입력하는 함수
$(document).on('click', '[id^="submit-comment"]', function () {

    const loginUserNo = parseInt(userNo);
    const num = parseInt(this.id.replace('submit-comment', ''));
    const commentText = $('#comment-text' + num).val().trim();

    console.log(loginUserNo);
    console.log(num);
    console.log(commentText);

    if (loginUserNo === 0) {
        alert("로그인한 회원만 댓글을 작성할 수 있습니다.");
        return;
    }

    if (commentText === "") {
        alert("댓글 내용을 입력하세요.");
        return;
    }
    
    $.ajax({
        url: contextPath +'/addComment.sh',
        data: {
            userNo: loginUserNo,
            num: num,
            comment: commentText
        },
        dataType : "json",
        success: function (response) {
            const reply = response;
            console.log(reply);
            
            if ($('#comments-list' + num).find('.no-reply').length > 0) {
                $('#comments-list' + num).empty();
            }

            const newCommentContainer = $('<div class="comment-container"></div>');

            // 프로필 사진을 담는 div
            const profilePicDiv = $('<div class="profile-pic"></div>');
            const profilePicImg = $('<img class="profile-img">');
            profilePicImg.attr('src', reply.filePath + reply.changeName);
            profilePicDiv.append(profilePicImg);
            newCommentContainer.append(profilePicDiv);

            // 사용자 정보와 댓글 내용을 담는 div
            const commentInfoDiv = $('<div class="comment-info"></div>').text(reply.userNickname + " : " + reply.replyContent + " - " + reply.enrollDate);
            newCommentContainer.append(commentInfoDiv);

            $('#comments-list' + num).prepend(newCommentContainer);
            $('#comment-text' + num).val('');

            let replyCount = parseInt($('#reply-reply-count' + num).html());
            replyCount += 1;
            $('#reply-reply-count' + num).html(replyCount);
            $('#thumbnail-reply-count' + num).html(replyCount);
          
        },
        error: function () {
            alert("댓글을 추가하는 데 실패했습니다. 다시 시도해주세요.");
        }
    });
    
});

// Ajax로 동영상을 가져와서 동영상 요소 생성
function loadVideo(num, item) {
    const likeButtonImg = item.querySelector(`#like-btn${num}`);
    $.ajax({
        url: contextPath + '/getVideo.sh',
        data: {
            videoId: num,
            userNo: parseInt(userNo)
        },
        success: function (response) {
            const totalShortsInfo = response;
            console.log(totalShortsInfo);

            const videoContainer = document.getElementById('video-container' + num);
            videoContainer.innerHTML = `
                        <video id="video${num}" controls autoplay muted width="720" height="1080">
                            <source src="` + totalShortsInfo.shortsPath + totalShortsInfo.shortsName + `" type="video/mp4">
                            Your browser does not support the video tag.
                        </video>
                    `;

            const videoElement = document.getElementById('video' + num);
            videoElement.addEventListener('ended', function() {
                videoElement.currentTime = 0;
                videoElement.play();
            });

            const likeCountSection = document.getElementById('thumbnail-like-count' + num);
            likeCountSection.innerHTML = totalShortsInfo.likeCount;
            const rePlyCountSection = document.getElementById('thumbnail-reply-count' + num);
            rePlyCountSection.innerHTML = totalShortsInfo.replyCount;
            const replyRePlyCountSection = document.getElementById('reply-reply-count' + num);
            replyRePlyCountSection.innerHTML = totalShortsInfo.replyCount;
            const nicknameSection = document.getElementById('thumbnail-nickname' + num);
            nicknameSection.innerHTML = totalShortsInfo.userNickname;
            const contentSection = document.getElementById('thumbnail-content' + num);
            contentSection.innerHTML = totalShortsInfo.shortsContent;
            const enrollDateSection = document.getElementById('thumbnail-enroll-date' + num);
            enrollDateSection.innerHTML = totalShortsInfo.enrollDate;
            const profileSection = document.getElementById('thumbnail-profile' + num);
            profileSection.innerHTML = `<img src="${totalShortsInfo.profilePath + totalShortsInfo.profileName}" class="shorts-profile-img">`;
            // totalShortsInfo.isLike boolean형으로 받아오는 것 확인했음.
            // 추후 js만든 후 해당 데이터 이용해서 좋아요 깜빡이게 구현 필요.
            if(totalShortsInfo.isLike){
                likeButtonImg.src = "resources/img/searchpage/like-after.png";
            } else{
                likeButtonImg.src = "resources/img/searchpage/like-pre.png";
            }

            

        },
        error: function () {
            console.log("동영상 로드 실패");
        }
    });
}

// 좋아요 누르는 함수
$(document).on('click', '[id^="like-btn"]', function () {

    if (userNo === null) {
        alert("로그인한 회원만 좋아요를 누를 수 있습니다.");
        return;
    }
    const num = parseInt(this.id.replace('like-btn', ''));
    

    $.ajax({
        url: contextPath + '/like.sh',
        data: {
            num: num,
            userNum: parseInt(userNo)
        },
        dataType : "json",
        success: function (response) {
            console.log(response);
            if(response === "sucess"){
                if(likeButtonImg.src === "resources/img/searchpage/like-after.png") {
                    likeButtonImg.src = "resources/img/searchpage/like-pre.png";
                } else {
                    likeButtonImg.src = "resources/img/searchpage/like-after.png"
                }
            } else {
                alert("좋아요 실패");
            }
        },
        error: function () {
            console.log("좋아요 실패");
        }
    });
});