//  let slides, currentSlide, slideCount;

// function showSlide(n) {
//     slides.forEach((slide, index) => {
//         if (index === currentSlide) {
//             slide.style.opacity = 0;
//             setTimeout(() => {
//                 slide.style.display = 'none';
//             }, 300); // transition 시간과 동일
//         }
//     });

//     currentSlide = (n + slides.length) % slides.length;
//     slides[currentSlide].style.display = 'block';
//     setTimeout(() => {
//         slides[currentSlide].style.opacity = 1;
//     }, 3); // display가 block으로 설정된 후 opacity 변경
// }

// function nextSlide() {
//     showSlide(currentSlide + 1);
// }

// function prevSlide() {
//     showSlide(currentSlide - 1);
// }

// document.addEventListener('DOMContentLoaded', () => {
//     slides = document.querySelectorAll('.slide>img');
//     currentSlide = 0;
//     slideCount = slides.length;

//     // 첫 슬라이드를 바로 표시
//     slides[currentSlide].style.display = 'block';
//     slides[currentSlide].style.opacity = 1;

//     setInterval(nextSlide, 3000); // 초마다 자동 슬라이드
// });


/* JavaScript */
let slides, currentSlide, slideCount;

function showSlide(n) {
    slides.forEach((slide, index) => {
        if (index === currentSlide) {
            slide.classList.remove('active');
        }
    });

    currentSlide = (n + slides.length) % slides.length;
    slides[currentSlide].classList.add('active');
}

function nextSlide() {
    showSlide(currentSlide + 1);
}

function prevSlide() {
    showSlide(currentSlide - 1);
}

// document.addEventListener('DOMContentLoaded', () => {
//     slides = document.querySelectorAll('.slide > img');
//     currentSlide = 0;
//     slideCount = slides.length;

//     // 첫 슬라이드를 바로 표시
//     slides[currentSlide].classList.add('active');

//     setInterval(nextSlide, 3000); // 초마다 자동 슬라이드
// });
