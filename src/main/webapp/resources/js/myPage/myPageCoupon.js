// 복제할 원본 div 요소를 가져옵니다
var originalDiv = document.getElementById('right2');

// 새로운 div를 생성하고 복제한 후 container에 추가합니다
for (var i = 0; i < 2; i++) {
    var newDiv = originalDiv.cloneNode(true); // true를 전달하여 하위 요소들도 함께 복제합니다
    document.getElementById('container').appendChild(newDiv);
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
                location.href = contextPath + '/myPageCoupon.mp';
            } else {
                alert('프로필 이미지 업로드 실패');
                location.href = contextPath + '/myPageCoupon.mp';
            }
        },
        error: function() {
            alert('프로필 이미지 업로드 실패');
            location.href = contextPath + '/myPageCoupon.mp';
        }
    });
}