
$(document).ready(function () {
    // userId 입력값이 변경될 때마다 호출되는 함수
    $("#userId").on("input", function () {
        var userId = $(this).val().trim(); // 입력된 아이디 (양쪽 공백 제거)

        // 정규식을 사용하여 아이디가 숫자와 영어로만 이루어져 있고, 숫자와 영어가 무조건 1개 이상씩 들어가고, 8글자 이상 15글자 이하인지 확인
        var regex = /^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,15}$/;
        if (regex.test(userId)) { // 조건을 만족하지 않을 경우
            $("#hiddenId").hide(); // 경고 메시지 표시하기
            return; // 추가된 부분
        }

        // Ajax 요청을 통해 서버에 아이디를 전송하고 검증 결과를 받음
        $.ajax({
            url: "/checkUserId",
            method: "POST",
            data: { userId: userId },
            success: function (data) {
                if (data.exists) { // 아이디가 존재할 경우
                    $("#hiddenId").show(); // 경고 메시지 표시하기
                } else { // 아이디가 존재하지 않을 경우
                    $("#hiddenId").hide(); // 경고 메시지 숨기기
                }
            },
            error: function () {
                console.error("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });
});

// 팝업 창을 보여주는 함수
function showConfirmation() {
    var modal = document.getElementById("confirmationModal");
    modal.style.display = "block";
    modal.style.background = "white";

    // 배경을 어둡게 만듭니다.
    var parentElement = document.getElementById("mainWrapper"); // 모달창의 부모 요소로 설정
    var darkOverlay = document.createElement("div");
    darkOverlay.id = "darkOverlay";
    darkOverlay.style.position = "fixed";
    darkOverlay.style.top = "0";
    darkOverlay.style.left = "0";
    darkOverlay.style.width = "100%";
    darkOverlay.style.height = "100%";
    darkOverlay.style.backgroundColor = "rgba(0, 0, 0, 0.7)";
    darkOverlay.style.zIndex = "999"; // 모달보다 낮은 z-index로 설정
    parentElement.appendChild(darkOverlay);

    // 배경을 클릭했을 때 모달이 닫히지 않도록 설정
    darkOverlay.addEventListener("click", function (event) {
        event.stopPropagation();
    });

    // 모달이 닫힐 때 배경도 함께 사라지도록 설정
    modal.addEventListener("click", hideConfirmation);
}

// 팝업 창을 숨기는 함수
function hideConfirmation() {
    var modal = document.getElementById("confirmationModal");
    modal.style.display = "none";

    // 배경을 제거합니다.
    var darkOverlay = document.getElementById("darkOverlay");
    if (darkOverlay) {
        darkOverlay.parentNode.removeChild(darkOverlay);
    }
}

// 확인 버튼 클릭 시 회원 삭제 요청을 보내는 함수
function deleteMember(userNo, contextPath) {
    // 여기에 확인 버튼을 눌렀을 때 회원 삭제 요청을 보내는 코드를 추가
    // 예를 들어, AJAX를 사용하여 deleteMember.mp로 요청을 보낼 수 있습니다.
    $.ajax({
        url: contextPath + "/deleteMember.mp",
        method: "GET",
        data: {userNo: userNo},
        success: function (response) {
            // 회원 삭제에 성공했을 때 처리할 내용
            console.log("회원 삭제에 성공했습니다.");
            if (response === 'NNNNY') {
                location.href = contextPath + '/logout.me';
                alert("회원 탈퇴에 성공했습니다.");
            }
        },
        error: function (error) {
            // 회원 삭제에 실패했을 때 처리할 내용
            console.error("회원 삭제에 실패했습니다.");
            // 에러 메시지를 보여줄 수도 있습니다.
        }
    });

    // 팝업 창을 숨깁니다.
    hideConfirmation();
}

document.getElementById('fileInput').addEventListener('change', function () {
    var file = this.files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
        document.getElementById('profile').style.backgroundImage = "url('" + e.target.result + "')";
    }

    reader.readAsDataURL(file);
});

var petNames = ["반려동물1", "반려동물2", "반려동물3"]; // 실제로는 해당 데이터를 서버로부터 받아와야 합니다.

// 반려동물 이름을 표시할 요소
var petNamesElement = document.getElementById("petNames");

// 각 반려동물의 이름을 동적으로 생성하여 요소에 추가
for (var i = 0; i < petNames.length; i++) {
    var petNameElement = document.createElement("p");
    petNameElement.textContent = petNames[i];
}

function uploadProfileImage(contextPath) {
    var fileInput = document.getElementById('fileInput');
    var upfile = fileInput.files[0];
    var formData = new FormData();
    formData.append('profileImage', upfile);

    $.ajax({
        url: contextPath + '/insertProfileImg.mp',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            if(response === "NNNNY") {
                alert('프로필 이미지 업로드 성공');
                location.href = contextPath + 'myPageInfo.mp';
            } else {
                alert('프로필 이미지 업로드 실패');
                location.href = contextPath + 'myPageInfo.mp';
            }
        },
        error: function() {
            alert('프로필 이미지 업로드 실패');
            location.href = contextPath + 'myPageInfo.mp';
        }
    });
}

// 이메일을 불러올 때
const loginUser = { userEmail: userEmail }; // 예제 사용자 이메일
    
// 이메일의 '@' 뒤를 숨기는 함수
function maskEmail(email) {
    const [user, domain] = email.split('@');
    return domain ? `${user}@******` : user;
}

// 페이지가 로드될 때 실행
document.addEventListener("DOMContentLoaded", () => {
    const emailInput = document.getElementById('userEmail');
    emailInput.value = maskEmail(loginUser.userEmail);
});