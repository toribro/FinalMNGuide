/* 전화번호 변경 함수 호출 */
// 전화번호 변경 입력 필드를 표시하거나 숨깁니다.
function showPhoneChange() {
    const changePhoneContainer = document.getElementById('change-phone-container');
    const phoneInput = document.getElementById('boss-phone-input');

    // 입력 필드를 초기화합니다.
    phoneInput.value = '';

    // 전화번호 변경 입력 필드의 표시 여부를 토글합니다.
    changePhoneContainer.style.display = changePhoneContainer.style.display === 'none' ? 'inline' : 'none';
}

/* 이메일 도메인 선택 시 동작 */
// 페이지가 로드되면 이메일 도메인 선택 필드를 초기화합니다.
document.addEventListener('DOMContentLoaded', function () {
    updateEmailDomain();  // 초기 상태 설정
});

// 이메일 도메인 선택에 따라 입력 필드의 상태를 업데이트합니다.
function updateEmailDomain() {
    const emailDomainSelect = document.querySelector('select.boss-email');
    const emailDomainInput = document.getElementById('boss-email');

    // '직접입력'을 선택한 경우, 도메인 입력 필드를 활성화합니다.
    if (emailDomainSelect.value === '직접입력') {
        emailDomainInput.value = '';
        emailDomainInput.removeAttribute('readonly');
        emailDomainInput.focus();
    } else {
        // 다른 도메인을 선택한 경우, 도메인 입력 필드를 비활성화하고 선택한 값을 설정합니다.
        emailDomainInput.value = emailDomainSelect.value;
        emailDomainInput.setAttribute('readonly', true);
    }
}

/* 이메일 수정 입력 폼 보여주기/숨기기 */
// 이메일 수정 입력 폼을 표시하거나 숨깁니다.
function showEmailChange() {
    const changePersonal = document.getElementById('change-personal');
    const emailLocalPart = document.getElementById('email-local-part');
    const emailDomainPart = document.getElementById('boss-email');
    const emailDomainSelect = document.querySelector('select.boss-email');

    // 입력 필드를 초기화합니다.
    emailLocalPart.value = '';
    emailDomainPart.value = '선택해주세요';
    emailDomainSelect.value = '선택해주세요';

    // 이메일 수정 입력 폼의 표시 여부를 토글합니다.
    changePersonal.style.display = changePersonal.style.display === 'none' ? 'block' : 'none';
}

/* 탈퇴 모달창 */
// 탈퇴 확인 버튼의 활성화 여부를 결정합니다.
function toggleRemoveButton() {
    var yesChecked = document.querySelector('input[name="confirm"]:checked').value === 'yes';
    var passwordInput = document.getElementById('password-input').value;
    document.querySelector('.boss-remove').disabled = !yesChecked || passwordInput.length === 0;
}

// 탈퇴 모달창을 표시합니다.
function showRemoveModal() {
    document.getElementById('boss-remove-modal').classList.add('active');
    document.getElementById('modal-overlay').classList.add('active');
}

// 탈퇴 모달창을 숨깁니다.
function hideRemoveModal() {
    document.getElementById('boss-remove-modal').classList.remove('active');
    document.getElementById('modal-overlay').classList.remove('active');
    document.getElementById('password-input').value = ''; // 비밀번호 입력 필드를 초기화합니다.
    document.querySelectorAll('input[name="confirm"]').forEach(input => input.checked = false); // 라디오 버튼 선택을 해제합니다.
    document.querySelector('.boss-remove').disabled = true; // 탈퇴 버튼을 비활성화 상태로 초기화합니다.
    document.querySelector('.pwd-checkMessage').style.display = 'none'; // 비밀번호 불일치 메시지를 숨깁니다.
}

/* 비밀번호 변경 모달창 */
// 비밀번호 변경 모달창을 표시합니다.
function showPasswordModal() {
    document.getElementById('modal-overlay').style.display = 'flex';
    document.querySelector('.minibox-wrap').style.display = 'block';
}

// 비밀번호 변경 모달창을 숨깁니다.
function hidePasswordModal() {
    document.querySelector('.minibox-wrap').style.display = 'none';
    document.getElementById('modal-box-overlay').style.display = 'none';
}

// 모달 오버레이를 클릭하면 모달창을 숨깁니다.
document.getElementById('modal-overlay').addEventListener('click', function (event) {
    // 이벤트가 오버레이에서 발생했는지 확인합니다.
    if (event.target === this) {
        // 모든 모달창을 숨깁니다.
        hideModal('boss-remove-modal');
        hideModal('minibox-wrap');
        // 오버레이 자체도 숨깁니다.
        this.style.display = 'none';
    }
});

// 지정된 모달창을 숨깁니다.
function hideModal(modalId) {
    document.getElementById(modalId).classList.remove('active');
    document.getElementById('modal-overlay').classList.remove('active');
}
