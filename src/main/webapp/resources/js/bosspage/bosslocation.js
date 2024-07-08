// 페이지 초기화 함수
function initializePage() {
    // 장소 정보를 로드
    loadLocationInfo();
    // 운영 시간 요소 추가
    addOperatingHoursElements();
    // 이미지 파일 입력 이벤트 초기화
    initializeLocationPage();
    // 이벤트 리스너 설정
    setupEventListeners();
    // 업종에 따른 섹션 숨기기
    hideProductRegistrationIfNeeded();
}

function hideProductRegistrationIfNeeded() {
    var locationCategory = document.getElementById("location-category").innerText;
    if (locationCategory === "병원") {
        document.getElementById("product-registration-section").style.display = "none";
    }
}

// 이벤트 리스너 설정 함수
function setupEventListeners() {
    // 상품 등록 버튼 클릭 이벤트 설정
    $("#add-product").off('click').on('click', function() {
        var newProductForm = $("<div class='product-registration'>" +
            "<input type='text' class='product-name' placeholder='상품명을 입력하세요.'>" +
            "<input type='text' class='commodity-price' placeholder='가격을 입력하세요.'>" +
            "<button class='delete-product'>" +
            "<img class='delete-productimg' src='resources/img/bosspage/-.png'>" +
            "</button>" +
            "</div>");
        $("#product-registration").before(newProductForm);
    });

    // 상품 삭제 버튼 클릭 이벤트 설정
    $(document).off('click', '.delete-product').on('click', '.delete-product', function() {
        $(this).closest(".product-registration").remove();
    });
}

// 장소 정보 저장 함수
function saveLocationInfo() {
    console.log("saveLocationInfo called");
    const locationPhone = document.getElementById("store-phone").value;
    const explanation = document.getElementById("store-description").value;
    const reservationLink = document.getElementById("reservation-link-input").value;
    const userNo = $("#userNo").val();

    // 운영 시간 정보를 배열로 수집
    const operationTimes = [];
    document.querySelectorAll('.operating-hours').forEach(element => {
        const day = element.querySelector('p').innerText;
        const startTime = element.querySelector('.open-time').value;
        const endTime = element.querySelector('.close-time').value;
        const restStatus = element.querySelector('input[name="휴무"]').checked;
        operationTimes.push({ day, startTime, endTime, restStatus });
    });

    // 반려동물 크기 정보를 배열로 수집
    const petSizes = [];
    document.querySelectorAll('input[name="animal-type"]:checked').forEach(element => {
        petSizes.push(element.value);
    });

    // 상품 정보를 배열로 수집
    const products = [];
    document.querySelectorAll('.product-registration').forEach(element => {
        const productName = element.querySelector('.product-name').value;
        const commodityPrice = element.querySelector('.commodity-price').value;
        products.push({ productName, commodityPrice });
    });

    // 장소 정보 객체 생성
    const locationInfo = {
        locationPhone: locationPhone,
        explanation: explanation,
        reservationLink: reservationLink,
        userNo: userNo,
        operationTimes: operationTimes,
        petSizes: petSizes,
        products: products // 상품 정보 추가
    };

    // 장소 정보 저장을 위한 AJAX 요청
    $.ajax({
        url: contextPath + '/saveLocationInfo.bm',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(locationInfo),
        success: function(response) {
            console.log("AJAX request successful");
            if (response.success) {
                uploadImages(response.locationNo); // 이미지 업로드 함수 호출
            } else {
                alert("장소정보 업데이트에 실패했습니다: " + response.message);
                $('.upload-bt').prop('disabled', false); // 버튼 다시 활성화
            }
        },
        error: function(xhr, status, error) {
            alert("장소정보 업데이트에 실패했습니다: " + error);
            $('.upload-bt').prop('disabled', false); // 버튼 다시 활성화
        }
    });
}



// 이미지 업로드 함수
function uploadImages(locationNo) {
    console.log("uploadImages called with locationNo:", locationNo);
    const imageInputs = document.querySelectorAll('.company-file');
    let uploadSuccess = true;

    imageInputs.forEach(input => {
        if (input.files.length === 1) {
            const file = input.files[0];
            console.log("업로드할 파일:", file);
            const formData = new FormData();
            formData.append("file", file);
            formData.append("locationNo", locationNo);

            $.ajax({
                url: contextPath + '/uploadImage',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log("파일 업로드 성공:", response);
                },
                error: function(xhr, status, error) {
                    console.error("파일 업로드 실패:", error);
                    console.log("xhr:", xhr);
                    console.log("status:", status);
                    console.log("error:", error);
                    uploadSuccess = false;
                }
            });
        }
    });

    if (uploadSuccess) {
        alert("장소정보 업데이트가 완료되었습니다.");
    } else {
        alert("이미지 업로드 중 일부 파일에 실패했습니다.");
    }
}

// 장소 정보 로드 함수
function loadLocationInfo() {
    $.ajax({
        url: contextPath + '/getLocationInfo.bm',
        method: 'GET',
        success: function(response) {
            if (response.locationPhone) {
                document.getElementById("store-phone").value = response.locationPhone;
            }
            if (response.explanation) {
                document.getElementById("store-description").value = response.explanation;
            }
            if (response.reservationLink) {
                document.getElementById("reservation-link-input").value = response.reservationLink;
            }
            if (response.animalTypes) {
                response.animalTypes.forEach(type => {
                    document.querySelector(`input[name="animal-type"][value="${type}"]`).checked = true;
                });
            }
            if (response.operationTimes) {
                response.operationTimes.forEach(time => {
                    const element = document.querySelector(`.operating-hours p:contains('${time.day}')`).parentElement;
                    element.querySelector('.open-time').value = time.startTime;
                    element.querySelector('.close-time').value = time.endTime;
                    element.querySelector('input[name="휴무"]').checked = time.restStatus;
                });
            }
        },
        error: function(xhr, status, error) {
            alert("장소정보 로드에 실패했습니다: " + error);
        }
    });
}

// 운영 시간 요소 생성 함수
function createOperatingHoursElement(day) {
    return `
        <div class="operating-hours">
            <p>${day}</p>
            <p>휴무</p>
            <input type="checkbox" name="휴무">
            <select class="open-time">
                ${[...Array(24).keys()].map(hour => `<option value="${String(hour).padStart(2, '0')}:00">${String(hour).padStart(2, '0')}:00</option>`).join('')}
            </select>
            <p style="margin-right: 0;margin-left: 20px;">~</p>
            <select class="close-time">
                ${[...Array(24).keys()].map(hour => `<option value="${String(hour).padStart(2, '0')}:00">${String(hour).padStart(2, '0')}:00</option>`).join('')}
            </select>
        </div>
    `;
}

// 운영 시간 요소 추가 함수
function addOperatingHoursElements() {
    const days = ['월', '화', '수', '목', '금', '토', '일'];
    const container = document.getElementById('operating-hours-container');
    container.innerHTML = '';
    days.forEach(day => {
        container.innerHTML += createOperatingHoursElement(day);
    });
}

// 이미지 파일 입력 이벤트 초기화 함수
function initializeLocationPage() {
    const imageInputs = document.querySelectorAll('.company-file');
    
    imageInputs.forEach(input => {
        input.addEventListener('change', (ev) => {
            loadImg(ev.target);
        });
    });
}

// 이미지 로드 함수
function loadImg(inputFile) {
    if (inputFile.files.length === 1) {
        const reader = new FileReader();
        reader.readAsDataURL(inputFile.files[0]);
        reader.onload = function(ev) {
            const companyImg = document.querySelector(`label[for=${inputFile.id}] img`);
            companyImg.src = ev.target.result;
            companyImg.style.display = 'block';
        };
    } else {
        const companyImg = document.querySelector(`label[for=${inputFile.id}] img`);
        companyImg.src = "resources/img/myPage/+.png";
    }
}

