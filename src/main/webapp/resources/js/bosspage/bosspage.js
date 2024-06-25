function bosspageInit(path){
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

