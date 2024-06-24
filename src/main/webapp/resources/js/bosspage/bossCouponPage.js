document.addEventListener('DOMContentLoaded', (event) => {
    const couponContentElements = document.querySelectorAll('.registed-coupon');
    if (couponContentElements.length > 1) {
        document.getElementById('coupon-none').style.display = 'none';
    }
});

function showNewCouponForm() {
    const newCouponFormTemplate = document.querySelector('.new-coupon-form').cloneNode(true);
    newCouponFormTemplate.style.display = 'table-row';
    newCouponFormTemplate.querySelector('.white-button').setAttribute('onclick', 'removeCouponForm(this)');
    document.getElementById('coupon-regist').before(newCouponFormTemplate);
}

function removeCouponForm(button) {
    const row = button.closest('tr');
    row.parentNode.removeChild(row);
}

// 쿠폰 수정 선택 시 수정 폼으로 변경
function editCouponForm(button) {
    const row = button.closest('tr');
    const couponTitle = row.querySelector('.coupon-title').innerText.trim();
    const couponExpiration = row.querySelector('.coupon-expiration').innerText.trim();

    const editForm = document.createElement('tr');
    editForm.classList.add('coupon-content');
    editForm.innerHTML = `
        <td class="coupon-title">
            <input type="text" value="${couponTitle}" class="minibox-input" placeholder="쿠폰명을 입력하세요">
        </td>
        <td class="coupon-expiration">
            <input type="number" value="${couponExpiration}" class="minibox-input" placeholder="ex)14">
        </td>
        <td class="coupon-count">${row.querySelector('.coupon-count').innerText}</td>
        <td class="coupon-admin">
        <button class="common-button pink-button" onclick="saveCouponForm(this)">확인</button>
        <button class="common-button white-button" onclick="cancelEditCouponForm(this, '${couponTitle}', '${couponExpiration}')">취소</button>
        </td>
    `;
    row.parentNode.replaceChild(editForm, row);
}

function cancelEditCouponForm(button, originalTitle, originalExpiration) {
    const row = button.closest('tr');
    const originalRow = document.createElement('tr');
    originalRow.classList.add('coupon-content');
    originalRow.innerHTML = `
        <td class="coupon-title">${originalTitle}</td>
        <td class="coupon-expiration">${originalExpiration}</td>
        <td class="coupon-count">${row.querySelector('.coupon-count').innerText}</td>
        <td class="coupon-admin">
            <button class="common-button pink-button" onclick="editCouponForm(this)">수정</button>
            <button class="common-button pink-button" data-toggle="modal" data-target="#delete-coupon">삭제</button>
        </td>
    `;
    row.parentNode.replaceChild(originalRow, row);
}

function saveCouponForm(button) {
    const row = button.closest('tr');
    const newTitle = row.querySelector('.coupon-title input').value.trim();
    
    const originalRow = document.createElement('tr');
    originalRow.classList.add('coupon-content');
    originalRow.innerHTML = `
        <td class="coupon-title">${newTitle}</td>
        <td class="coupon-expiration">${newExpiration}</td>
        <td class="coupon-count">${row.querySelector('.coupon-count').innerText}</td>
        <td class="coupon-admin">
            <button class="common-button pink-button" onclick="editCouponForm(this)">수정</button>
            <button class="common-button pink-button" data-toggle="modal" data-target="#delete-coupon">삭제</button>
        </td>
    `;
    row.parentNode.replaceChild(originalRow, row);
}
