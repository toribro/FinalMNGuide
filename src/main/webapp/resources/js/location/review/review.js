//file

const dataTransfer = new DataTransfer();

function fileUpload(_this) {

  let fileArr = _this.files;

  if (fileArr != null && fileArr.length > 0) {

    if (dataTransfer.files.length == 3) {
      alert("이미지는 최대 3장까지 가능합니다")
      return false;
    }
    fileList(_this.files);

    dataTransfer.items.add(fileArr[0]);
    _this.files = dataTransfer.files;

    let reader = new FileReader();
    reader.onload = (e) => {
      document.getElementById("img-background").style.backgroundImage
        = `url(${e.target.result})`;
    }
    reader.readAsDataURL(fileArr[fileArr.length - 1]);
    document.getElementById("img-count").innerText = _this.files.length;
  }
}

function fileList(files) {
  const fileList = document.getElementById('file-list');
  for (let i = 0; i < files.length; i++) {
    const item = document.createElement('div');
    const fileName = document.createTextNode(files[i].name);
    const deleteButton = document.createElement('button');

    deleteButton.addEventListener('click', (event) => {
      item.remove();
      event.preventDefault();
      console.log(dataTransfer.items)
      dataTransfer.items.remove(files[i])
      if (dataTransfer.files.length == 0) {
        document.getElementById("img-background").style.backgroundImage
          = `url(resources/img/myPage/+.png)`;
      }

      document.getElementById("img-count").innerText = dataTransfer.files.length;
      // const deleteItem = document.createElement('input');
      // deleteItem.setAttribute("name", "deleteFilesId");
      // deleteItem.setAttribute("value", files[i].id);
      // deleteItem.setAttribute("type", "hidden");
      // fileList.appendChild(deleteItem);
    });

    deleteButton.innerText = "X";
    item.appendChild(fileName);
    item.appendChild(deleteButton);
    fileList.appendChild(item);
  }


}

//review data

function reviewData() {
  const formData = new FormData();
  const starCount = document.querySelector('input[name="rating"]:checked');

  if(starCount==null){
    return null;
  }
  const content = document.querySelector("#review-content");
  const fileInput = document.querySelector("#fileInput");

  for (let file of fileInput.files) {
    formData.append('files', file)
  }


  formData.append('userNo', userNo);
  formData.append('locationNo', spaceNo)
  formData.append('starCount', starCount.value)
  formData.append('content', content.value)


  return formData

}

//reivewList
function drawReivew(review) {
 

  let reviewContent = document.querySelector("#review-content-box");
  let reviewBody = "";
  let checkUser= "";

  for (let r of review) {
    let imgs = "";
    let reply = "";
    let reviewStar = '';

    for (let a of r.attachment) {
      imgs += `<div class="img-div"><img src="${a.filePath}${a.changeName}" alt="Profile Image"></div>
             `
    }

    if (r.masterReply != null) {

      reply = `<div id="master-reply-content${r.reviewNo}" class="master-reply-input"  style="align-items: right;">
                 <div class="review-section reply">
        
                    <div class="reply-master">
                      <div class="title master">사장님</div>
                      <div>${r.masterReply.enrollDate}</div>
                    </div>

                    <div class="content master-reply">${r.masterReply.ownerReplyContent}</div>
                 </div>
              </div>`
    }

    for (let i = 1; i <= r.reviewStar; i++) {
      reviewStar += '★'
    }

    checkedMasterInput="";
    if(checkedMaster==="YYYY"){
      checkedMasterInput=`
       <a id="reply-button${r.reviewNo}" class="reply-button" onclick="onReplyOnClick('${r.reviewNo}')">답글작성</a>&nbsp;`
    }

    if(r.userNo==userNo){
       checkUser=`
       <a href="myPageMain.mp">수정</a>
       <a onclick="reviewDelete('${r.reviewNo}','${r.userNo}')">삭제</a> 
       `
    }
    
    reviewBody += `
        <div class="review-section">
						<div class="profile-star">
							<div class="profile">
								<div class="img-div"><img src="${r.userProfile.filePath}${r.userProfile.changeName}"></div>
								<div><span class="title">${r.userNickName}</span><br>
									<span>${r.enrollDate}</span>
								</div>
							</div>

							<div>
                <span style="color:#FE8B94;">`+ reviewStar + `</span>
                <span>`+checkUser+`</span>
							</div>
						</div>
            <div class="img-content">`+ imgs + `</div>
            <div class="content">${r.reviewContent}</div>

           
            <div>`+checkedMasterInput+`
                 <a id="reply-button-show${r.reviewNo}" class="reply-button" onclick="onReplyShow('${r.reviewNo}')">답글</a></div>
            <div id="master-reply-input-div${r.reviewNo}" class="master-reply-input show-reply">
              <textarea id="reply-content${r.reviewNo}" class="master-reply-content"></textarea>
              <button class="master-reply-button" onclick=" insertReplyAjax('${r.reviewNo}')">작성하기</button>
            </div>
					</div>

         

        `+ reply


        checkUser=""
       
  }

  reviewContent.innerHTML = reviewBody;
}


//페이징 부분 비동기 처리
function drawPaging(reviewPi,type) {
  let pageDiv = document.querySelector("#page-div");
  let previousButton = "";
  let nextButton = "";
  let pageItem = "";

  if (reviewPi.currentPage == 1) {
    previousButton = `
    <div id="previous-button" class="prv-button">
      <li class="page-disabled"><a class="page-button">◀</a></li>
    </div >
  `
  }
  else {
    previousButton = `
    <div id="previous-button" class="prv-button">
      <li><a class="page-button" onclick="reviewPaging('${reviewPi.currentPage - 1}','${type}')">◀</a></li>
    </div >
    `
  }

  for (let p = reviewPi.startPage; p <= reviewPi.endPage; p++) {
    pageItem += `
      <li class="page-item"><a class="page-link" onclick="reviewPaging('${p}','${type}')">${p}</a></li>
    `
  }


  if (reviewPi.currentPage == reviewPi.maxPage) {
    nextButton = `
    <div id="next-button" class="next-button">
				<li class="page-disabled"><a class="page-button">▶</a></li>
		</div >
  `
  }
  else {
    nextButton = `
     <div id="next-button" class="next-button">
         <li ><a  class="page-button" onclick="reviewPaging('${reviewPi.currentPage + 1}','${type}')">▶</a></li>
      </div>
    `
  }
  pageDiv.innerHTML = previousButton + pageItem + nextButton

}