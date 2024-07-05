function bosspageInit(){
    // 장소 정보를 로드
    loadLocationInfo();
    // 운영 시간 요소 추가
    createCheckInOutHoursElement();
    // 이미지 파일 입력 이벤트 초기화
    initializeLocationPage();
       
    /*객실 모달창 열기*/
    const modal = document.querySelector('.rooms-modal');
    const roomOpenModal = document.querySelector('.roomOpen-modal');
    const closeModalBtn = document.querySelector('.closeModal-bt');

    roomOpenModal.addEventListener("click", () => {
        modal.style.display = "flex";
    })

    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    /*객실 이미지 업로드*/

    const registrationupload = document.querySelectorAll('.registration-upload');

    for (let uploadImg of registrationupload) {
        uploadImg.onclick = (ev) => {
            ev.stopPropagation();
            ev.preventDefault();

            console.log(uploadImg)
            const companyImg = document.getElementById(uploadImg.dataset.target);
            companyImg.onchange = (ev) => {
                loadImg(ev.target, uploadImg.dataset.target);
            }
            companyImg.click();
        }; 
    }

    function loadImg(inputFile, id){
        //inputFile : 현재 변화가 생긴 input type=file 요소객체
        //id : 현재 선택된 input id
        
        //inputFile.files[0] => 선택된 파일이 담겨있다.
        //inputFile.files.length -> 1
        console.log(inputFile.files.length)

        if (inputFile.files.length == 1){//파일을 하나 선택했다. => 미리보기
            //파일을 읽어들일 FileReader객체생성
            const reader = new FileReader();

            //파일을 읽어들이는 메소드
            //해당파일을 읽어들이는 순간 해당 파일만의 고유한 url부여
            reader.readAsDataURL(inputFile.files[0]);

            //파일 읽어들이기 완료했을 때 실행할 함수 정의
            reader.onload = function(ev){
                // console.log(ev.target.result) => 읽어들인 파일의 고유한 url
                const companyImg = document.querySelector(`label[for=${id}] img`);
                companyImg.src = ev.target.result;
            }
        } else { // 선택된 파일을 취소한 경우 => 미리보기 지워준다.
            const companyImg = document.querySelector(`label[for=${id}] img`);
            companyImg.src = path + "/resources/img/myPage/+.png";
        }
    }
    
}

//체크인 체크아웃 시간 요소 생성 함수
function createCheckInOutHoursElement(){
    return `
    <div class="in-out-hours">
        <p style="margin-left:0px">체크인</p>
        <select class="check-in-time" style="margin-left: 0px; margin-top: 13px;">
        ${[...Array(24).keys()].map(hour => `<option value="${String(hour).padStart(2, '0')}:00">${String(hour).padStart(2, '0')}:00</option>`).join('')}
        </select>
        <p style="margin-left: auto;">체크아웃</p>
        <select class="check-out-time" style="margin-left: 0px; margin-top: 13px;">
        ${[...Array(24).keys()].map(hour => `<option value="${String(hour).padStart(2, '0')}:00">${String(hour).padStart(2, '0')}:00</option>`).join('')}
        </select>    
    </div> 
    `;
}

    function addCheckInOutHoursElements(){
    const container = document.getElementById('in-out-hours');
    container.innerHTML = '';
    container.innerHTML += createCheckInOutHoursElement();
    }
