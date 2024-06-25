function  editpageStyle(){
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

function insertImg(){
    let img = document.querySelector('[name="upfile"]');
    img.click();
    console.log(img)
}

function thumbnailImg(imgFile){
    // const reader = new FileReader();
    let nameBox = document.querySelector('#img-box>span');
    fileName = imgFile.files[0].name;
    nameBox.innerHTML = fileName;
    
    removeExistedFile()
}
function removeExistedFile(){

    let tempExistedFile=document.querySelector("#tempExistedFile")
    let existedFileChange=document.querySelector("#existedFileChange")
    existedFileChange.value=tempExistedFile.value;

   let existedFile=document.querySelector("#existingFile")
   existedFile.remove();
 
}
