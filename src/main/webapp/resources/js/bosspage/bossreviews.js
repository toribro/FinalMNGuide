function giveCoupon() {
    var modal = document.getElementById('modal');
    var modalBackground = document.getElementById('modal-background');
    modal.style.display = 'block'; // 모달 창 보이기
    modalBackground.style.display = 'block'; // 배경 보이기
}

function closeModal() {
    var modal = document.getElementById('modal');
    var modalBackground = document.getElementById('modal-background');
    modal.style.display = 'none'; // 모달 창 숨기기
    modalBackground.style.display = 'none'; // 배경 숨기기
}

function closeReply() {
    var replyArea = document.getElementById('replyArea');
    var replyContent = document.getElementById('replyContent');

    // 답글 내용을 replyContent div에 설정
    replyContent.innerText = replyArea.value;

    // 답글 입력란 숨기기
    document.getElementById('reply').style.display = 'none';
    replyArea.value = '';  // 입력란 내용 지우기

    // "답글 달기" 버튼 숨기기
    document.getElementById('showReplyButton').style.display = 'none';
    // "답글 완료" 메시지 보이기
    document.querySelector('.complete-message').style.display = 'block';
    // 답글 완료 섹션 보이기
    document.getElementById('rpple-complete').style.display = 'flex';
}

function showReply() {
    var replyDiv = document.getElementById('reply');
    var replyArea = document.getElementById('replyArea');

    // 답글 입력란 표시 여부 토글
    if (replyDiv.style.display === 'none' || replyDiv.style.display === '') {
        replyDiv.style.display = 'block';
    } else {
        replyDiv.style.display = 'none';
        replyArea.value = '';  // 답글 입력란 내용 지우기
    }
}

function onDeleteClick() {
    var replyContent = document.getElementById('replyContent');

    // 답글 내용 지우기
    replyContent.innerText = '';

    // 답글 완료 섹션 숨기기
    document.getElementById('rpple-complete').style.display = 'none';
    // "답글 달기" 버튼 다시 보이기
    document.getElementById('showReplyButton').style.display = 'block';
    // "답글 완료" 메시지 숨기기
    document.querySelector('.complete-message').style.display = 'none';
}

/*쿠폰발급 모달창*/
function issueCoupon() {
    var checkbox = document.querySelector('#modal-body input[type="checkbox"]');
    if (checkbox.checked) {
        alert("쿠폰 정상 발급 되었습니다.");
        closeModal(); // 모달 창 닫기
        disableInteraction(); // 버튼 비활성화 및 스타일 변경
    } else {
        alert("발급할 쿠폰 항목을 선택해주세요.");
    }
}

function disableInteraction() {
    var deleteButton = document.querySelector('.rpple-removal');
    var couponButton = document.querySelector('[onclick="giveCoupon()"]');

    deleteButton.disabled = true; // 삭제 버튼 비활성화
    couponButton.disabled = true; // 쿠폰 발급 버튼 비활성화

    deleteButton.style.backgroundColor = '#bababa'; // 삭제 버튼 회색 처리
    couponButton.style.backgroundColor = '#bababa'; // 쿠폰 발급 버튼 회색 처리
}

function increaseCount() {
    var adminCount = document.getElementById('content-admin');
    var currentCount = parseInt(adminCount.innerText.trim()); // 현재 숫자 가져오기
    if (currentCount < 4) { //숫자가 4이상이면 증가 안됨
        adminCount.innerText = currentCount + 1
    }
}

function decreaseCount() {
    var adminCount = document.getElementById('content-admin');
    var currentCount = parseInt(adminCount.innerText.trim()); // 현재 숫자 가져오기
    if (currentCount > 1) { // 숫자가 1보다 큰 경우에만 감소
        adminCount.innerText = currentCount - 1; // 숫자 감소
    }
}