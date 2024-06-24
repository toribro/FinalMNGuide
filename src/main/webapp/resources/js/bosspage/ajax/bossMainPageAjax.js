/* 전화번호 업데이트 함수 */
// 사용자가 입력한 전화번호를 서버로 전송하여 업데이트합니다.
function updatePhoneNumber(userNo, contextPath) {
    const phoneNumberInput = document.getElementById('boss-phone-input');

    // 전화번호 형식 검증
    if (!phoneNumberInput.value || !/^\d{2,3}-\d{3,4}-\d{4}$/.test(phoneNumberInput.value)) {
        alert('전화번호 형식이 올바르지 않습니다. 예: 010-1234-5678');
        return;
    }

    // AJAX 요청을 사용하여 전화번호를 업데이트합니다.
    $.ajax({
        url: contextPath + '/updatePhoneNumber.bm',
        type: 'POST',
        data: {
            phoneNumber: phoneNumberInput.value, // 전화번호 데이터
            userNo: userNo // 사용자 번호
        },
        success: function (response) {
            alert(response); // 서버 응답 메시지 출력
            document.getElementById('phone-display').textContent = phoneNumberInput.value; // 화면에 전화번호 업데이트
            document.getElementById('change-phone-container').style.display = 'none'; // 전화번호 변경 입력 필드 숨기기
        },
        error: function (error) {
            alert('전화번호 변경에 실패했습니다.'); // 오류 메시지 출력
        }
    });
}

/* 이메일 업데이트 함수 */
// 사용자가 입력한 이메일을 서버로 전송하여 업데이트합니다.
function updateEmail(userNo, contextPath) {
    const emailLocalPart = document.getElementById('email-local-part').value;
    const emailDomainPart = document.getElementById('boss-email').value;
    const fullEmail = emailLocalPart + '@' + emailDomainPart;

    // 이메일 입력 검증
    if (!emailLocalPart || !emailDomainPart || emailDomainPart === '선택해주세요') {
        alert('이메일을 올바르게 입력해주세요.');
        return;
    }

    // AJAX 요청을 사용하여 이메일을 업데이트합니다.
    $.ajax({
        url: contextPath + '/updateEmail.bm',
        type: 'POST',
        data: {
            email: fullEmail, // 이메일 데이터
            userNo: userNo // 사용자 번호
        },
        success: function (response) {
            alert(response); // 서버 응답 메시지 출력
            document.getElementById('email-display').textContent = fullEmail; // 화면에 이메일 업데이트
            document.getElementById('change-personal').style.display = 'none'; // 이메일 변경 입력 필드 숨기기
        },
        error: function (error) {
            alert('이메일 변경에 실패했습니다.'); // 오류 메시지 출력
        }
    });
}

/* 비밀번호 변경 함수 */
// 사용자가 입력한 비밀번호를 서버로 전송하여 업데이트합니다.
function updatePassword(event, userNo, contextPath) {
    event.preventDefault(); // 폼의 기본 제출 동작을 방지합니다.

    // 비밀번호 변경 모달을 표시합니다.
    document.getElementById('boss-pws-div').style.display = 'block';
    document.getElementById('modal-overlay').style.display = 'block';

    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const mismatchError = document.getElementById('mismatch-error');

    // 비밀번호 입력 검증
    if (!password || !confirmPassword) {
        alert('비밀번호를 입력해주세요.');
        return false;
    }

    // 비밀번호 일치 여부 확인
    if (password !== confirmPassword) {
        mismatchError.style.display = 'block'; // 비밀번호 불일치 메시지 표시
        return false;
    } else {
        mismatchError.style.display = 'none'; // 비밀번호 불일치 메시지 숨기기
    }

    // AJAX 요청을 사용하여 비밀번호를 업데이트합니다.
    $.ajax({
        url: contextPath + '/updatePwd.bm',
        type: 'POST',
        data: {
            bossPwd: password, // 비밀번호 데이터
            userNo: userNo // 사용자 번호
        },
        success: function (response) {
            // 비밀번호 변경 모달 숨기기
            document.getElementById('boss-pws-div').style.display = 'none';
            document.getElementById('modal-overlay').style.display = 'none';
            if (response === '비밀번호 변경에 성공였습니다.') {
                location.href = contextPath + '/bossMainPage.bm'; // 메인 페이지로 이동
                alert(response); // 성공 메시지 출력
            }
        },
        error: function (error) {
            alert('비밀번호 변경에 실패했습니다.'); // 오류 메시지 출력
        }
    });
}

/* 비밀번호 확인 후 탈퇴 함수 */
// 사용자가 입력한 비밀번호를 서버로 전송하여 계정을 탈퇴 처리합니다.
function checkPassword(contextPath, userNo) {
    const inputPassword = document.getElementById('password-input').value;
    var yesChecked = document.querySelector('input[name="confirm"]:checked').value === 'yes';
    if (yesChecked) {
        // AJAX 요청을 사용하여 비밀번호를 확인하고 계정을 탈퇴 처리합니다.
        $.ajax({
            url: contextPath + '/deleteBossUser.bm',
            type: 'POST',
            data: {
                bossPwd: inputPassword, // 비밀번호 데이터
                userNo: userNo // 사용자 번호
            },
            dataType: 'text', // 서버에서 반환되는 데이터 유형
            success: function (response) {
                var messageElement = document.querySelector('.pwd-checkMessage');
                if (response.trim() === "RRRR") {
                    messageElement.style.display = 'block'; // 비밀번호 불일치 메시지 표시
                    messageElement.textContent = '비밀번호가 일치하지 않습니다.';
                } else if (response.trim() === "NNNN") {
                    alert('탈퇴 처리 실패'); // 탈퇴 실패 메시지 출력
                } else if (response.trim() === "YYYY") {
                    alert('탈퇴 처리가 완료되었습니다.'); // 탈퇴 성공 메시지 출력
                    window.location.href = contextPath; // 메인 페이지로 이동
                }
            },
            error: function (error) {
                var messageElement = document.querySelector('.pwd-checkMessage');
                messageElement.style.display = 'block'; // 서버 오류 메시지 표시
                messageElement.textContent = '서버와의 통신 중 오류가 발생했습니다.';
            }
        });
    }
}
