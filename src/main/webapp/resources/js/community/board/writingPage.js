// 아이콘 클릭 시 첨부파일 온클릭 이벤트 실행 함수
function insertImg(){
    let img = document.querySelector('[name="upfile"]');
    img.click();
}

// const dataTransfer = new DataTransfer();

// 첨부파일 선택 시 첨부파일 파일명과 삭제 버튼 출력
function thumbnailImg(imgFile){
    // const reader = new FileReader();
    let nameBox = document.querySelector('#img-box>span');
    fileName = imgFile.files[0].name;
    
    let deleteBtn = document.createElement('img');
    deleteBtn.src = 'resources/img/default/xicon.png';
    // deleteBtn.classList.toggle('delete-img');
    deleteBtn.style.width = '10px';
    deleteBtn.style.height = '10px';
    deleteBtn.style.cursor = 'pointer';
    deleteBtn.style.marginLeft = '10px';
    
    deleteBtn.addEventListener('click', deleteImg)

    nameBox.innerHTML = fileName;
    nameBox.append(deleteBtn)

}

// 삭제 버튼 클릭 시 첨부파일 취소
function deleteImg(){
    let img = document.querySelector('[name="upfile"]');
    let nameBox = document.querySelector('#img-box>span');
    img.value = '';
    nameBox.innerHTML = "";
}



// 스타일
function writingpageStyle(){
    let textarea = document.querySelector('#summernote');

    textarea.style.resize = 'none';
    textarea.style.width = '100%';
    textarea.style.height = '500px';
    textarea.style.borderColor = 'var(--border-color)';
    textarea.style.borderRadius = '15px';
    textarea.style.padding = '30px';
    textarea.style.resize = 'none';
    textarea.style.resize = 'none';
    textarea.style.resize = 'none';

    let boardTitle = document.querySelector('#titleInput');

    boardTitle.style.fontSize = '25px'
    boardTitle.style.fontWeight = 'bold';

    
}