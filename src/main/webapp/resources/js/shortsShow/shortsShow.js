
let currentDivIndex = 0;
let isScrolling = false;
let contextPath;
let userNo;

// 댓글 관련 함수들
$(document).ready(function () {

    contextPath = document.getElementById("contextPath").value;
    userNo = document.getElementById("userNo").value;

    $(document).on('click', '.comment-button', function (ev) {
        const btn = ev.target;
        const index = btn.dataset.index;
        btn.dataset.toggle = btn.dataset.toggle === "true" ? "false" : "true";
        const toggle = btn.dataset.toggle;

        if (toggle === "true") {
            $("#shorts-comment" + index).animate({ right: '30%' }, 'slow');
            $("#shorts-content" + index).animate({ left: '30%' }, 'slow');
        } else {
            $("#shorts-comment" + index).animate({ right: '10%' }, 'slow');
            $("#shorts-content" + index).animate({ left: '10%' }, 'slow');
        }
    });

    window.addEventListener('wheel', (event) => {
        if (isScrolling) return;
        isScrolling = true;

        if (event.deltaY > 0) {
            // 아래로 스크롤
            scrollToDiv(currentDivIndex + 1);
        } else {
            // 위로 스크롤
            scrollToDiv(currentDivIndex - 1);
        }

        // 짧은 시간 내에 여러 번 스크롤하는 것을 방지
        setTimeout(() => {
            isScrolling = false;
        }, 500);  // 필요에 따라 지연 시간을 조정
    });

    // 초기 데이터 로드
    loadItems();
    const loader = document.getElementById('loader');
    observer.observe(loader);
    scrollToDiv(0);  // 초기 스크롤 위치 설정
});

// 더미 데이터 생성 함수 (동영상 Ajax로 가져옴)
function createItem(num) {
    const item = document.createElement('div');
    item.className = 'shorts-view makeCenter';
    item.innerHTML = `
            <div class="shorts-content" id="shorts-content`+ num + `">
                <div id="video-container` + num + `">
                    Loading video...
                </div>
                <div class="text-overlay">
                    <div>
                        <img src="resources/img/community/reply.png" class="comment-button" data-index="` + num + `" data-toggle="true" id="show-reply-btn` + num + `" width="50" height="50">
                        <img src="" id="like-btn${num}" style="width: 50px; height: 50px;">
                        <div class="white-pont" id="thumbnail-like-count` + num +`"></div>
                        <div class="white-pont" id="thumbnail-reply-count` + num +`"></div>
                        <div id="thumbnail-profile` + num + `"></div>
                        <div class="white-pont" id="thumbnail-nickname` + num +`"></div>
                        <div class="white-pont" id="thumbnail-content` + num +`"></div>
                        <div class="white-pont" id="thumbnail-enroll-date` + num +`"></div>
                    </div>
                </div>
            </div>
            <div id="shorts-comment`+ num + `" class="shorts-comment tmp-box flex-box">
                <div style="width: 100%; height: 100%">
                    <div class="row-box" style="height: 10%">
                        <h1>댓글</h1>
                        <div style="color: var(--border-color)" id="reply-reply-count` + num +`"></div>
                    </div>
                    <div id="comments-list`+ num + `"class="tmp-box" style="height: 80%; max-height: 80%; overflow-y: auto; overflow-x: hidden; word-wrap: break-word;"></div>  
                    <div style="height: 10%">
                        <textarea id="comment-text`+ num + `" placeholder="댓글을 입력하세요"></textarea>
                        <button id="submit-comment`+ num + `">댓글달기</button>
                    </div>
                </div>
            </div>
            `;
    loadVideo(num, item);  // 1부터 시작함
    return item;
}



// 더미 데이터 로딩 함수
function loadItems(numItems = 10) {

    const container = document.getElementById('container');
    let lastItemNum = container.children.length;
    for (let i = 1; i <= numItems; i++) {
        const newItem = createItem(lastItemNum + i);
        container.appendChild(newItem);
    }
    updateDivs(); // 새로 로드된 아이템들로 divs 배열 업데이트
}

// Intersection Observer 콜백 함수
function handleIntersect(entries, observer) {
    const loader = document.getElementById('loader');
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            loadItems();
            observer.unobserve(loader); // 로더가 보이면 일단 관찰 해제
            observer.observe(loader); // 로더를 다시 관찰하여 무한 스크롤 구현
        }
    });
}

// Intersection Observer 설정
const observer = new IntersectionObserver(handleIntersect, {
    root: null,
    rootMargin: '0px',
    threshold: 1.0
});

function updateDivs() {
    divs = document.querySelectorAll('.makeCenter');
}

function scrollToDiv(index) {
    if (index >= 0 && index < divs.length) {
        const targetDiv = divs[index];
        const headerHeight = document.querySelector('.headerbar').offsetHeight;
        const targetPosition = targetDiv.offsetTop - headerHeight;
        window.scrollTo({
            top: targetPosition,
            behavior: 'smooth'
        });
        currentDivIndex = index;
    }
}



