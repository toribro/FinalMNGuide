document.getElementById('imgFileInput').addEventListener('change', function (event) {
    var file = event.target.files[0]; // 파일 가져오기

    if (file) {
        // 파일이 선택되었을 때에만 처리
        // 원래 이름
        var originName = file.name;
        console.log(file);
        // 서버에 저장될 파일의 이름 생성
        var changeName = Date.now() + '_' + originName;
        // 서버에 저장될 파일의 경로 설정
        var filePath = 'resources/uploads/' + changeName;
        // 파일 레벨 설정 (예: 사용자 입력, 고정 값 등)
        var fileLevel = 1;

        // FormData 객체 생성
        var formData = new FormData();
        formData.append('file', file); // 파일 추가
        formData.append('changeName', changeName); // 변경된 파일 이름 추가
        formData.append('filePath', filePath); // 파일 경로 추가
        formData.append('fileLevel', fileLevel); // 파일 레벨 추가

        // AJAX를 통해 서버로 데이터 전송
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'insertPet.mp', true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('파일 업로드 성공');
                // 파일 업로드 성공 시 추가 작업 수행
            } else {
                console.error('파일 업로드 실패');
                // 파일 업로드 실패 시 처리
            }
        };
        xhr.send(formData); // FormData 전송
    } else {
        // 파일이 선택되지 않은 경우 처리
        console.error('파일이 선택되지 않았습니다.');
    }
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
                location.href = contextPath + 'myPagePetSignUp.mp';
            } else {
                alert('프로필 이미지 업로드 실패');
                location.href = contextPath + 'myPagePetSignUp.mp';
            }
        },
        error: function() {
            alert('프로필 이미지 업로드 실패');
            location.href = contextPath + 'myPagePetSignUp.mp';
        }
    });
}