const mainSwiper = new Swiper('.swiper-main', {
    spaceBetween: 0,    // 슬라이드 사이 여백
    //slidesPerView : 'auto', // 한 슬라이드에 보여줄 갯수
    centeredSlides: true,    //센터모드​
    autoplay: { //자동슬라이드(false:비활성화)
        delay: 1500 //시간설정
    },
    loop: true, //슬라이드 반복여부
    navigation: { //버튼설정
        nextEl: '.swiper-button-next', //next버튼
        prevEl: '.swiper-button-prev', //prev버튼
    },
    pagination: { //슬라이드 갯수 표현
        el: '.swiper-pagination', //버튼담을 태그 설정
        type: 'bullets', //버튼모양
        clickable: true, // 버튼 클릭 가능 여부
    },
    resistance : false, // 슬라이드 터치 저항 여부
    slideToClickedSlide : true, // 해당 슬라이드 클릭시 슬라이드 위치로 이동
    allowTouchMove : true // (false-스와이핑안됨)버튼으로만 슬라이드 조작이 가능
});

const magazineSwiper = new Swiper('.swiper-magazine', {
    spaceBetween: 30,
    slidesPerView : 4,
    centeredSlides: true,
    autoplay: {
        delay: 1500
    },
    loop: true,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});