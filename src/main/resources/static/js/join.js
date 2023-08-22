const allAgreement = document.querySelector("#allAgreement");
allAgreement.addEventListener("change",function(){
    const status = allAgreement.checked;
    const essentialAgree = document.querySelector("#essentialAgree1");
    const essentialAgree2 = document.querySelector("#essentialAgree2");
    const option = document.querySelector("#optionAgreement");
        essentialAgree.checked = status;
        essentialAgree2.checked = status;
        option.checked = status;
});



const comment = $(".comment");

//비밀번호 정규표현식 체크
$("#memberPw").on("change",function(){
    const pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    const inputPw = $(this).val();
    const check = pwReg.test(inputPw);

    if(check){
        comment.eq(0).text("사용가능한 비밀번호 입니다.");
        comment.eq(0).css("color","black");
        comment.eq(0).css("font-size","12px");
        comment.eq(0).css("font-weight","400");

    }else{
        comment.eq(0).text("영문+숫자+특수문자를 조합하여 8~16자리를 입력해주세요.");
        comment.eq(0).css("color","red");
        comment.eq(0).css("font-size","12px");
        comment.eq(0).css("font-weight","400");
    }
});
//비밀번호 비밀번호 확인 하기
$("#memberPwRe").on("change",function(){
    //비밀번호 확인 : 비밀번호와 값이 같은지
    const pwValue = $("#memberPw").val();
    const pwReValue = $("#memberPwRe").val();
    if(pwValue == pwReValue){
        comment.eq(1).text("비밀번호가 일치합니다.");
        comment.eq(1).css("color","black");
        comment.eq(1).css("font-size","12px");
        comment.eq(1).css("font-weight","400");
    }else{
        comment.eq(1).text("비밀번호가 일치하지않습니다.");
        comment.eq(1).css("color","red");
        comment.eq(1).css("font-size","12px");
        comment.eq(1).css("font-weight","400");
    }
});
$("#memberName").on("change",function(){
    const nameReg = /^[가-힣]{2,}$/;
    const nameValue = $(this).val();
    const check = nameReg.test(nameValue);
    if(check){
        comment.eq(2).text("사용가능한 이름입니다.");
        comment.eq(2).css("color","black");
        comment.eq(2).css("font-size","12px");
        comment.eq(2).css("font-weight","400");
    }else{
        comment.eq(2).text("이름은 한글만(최소2글자) 입력 가능합니다.");
        comment.eq(2).css("color","red");
        comment.eq(2).css("font-size","12px");
        comment.eq(2).css("font-weight","400");1
    }
});

$(".modal-open-btn").on("click",function(){
    console.log("클릭");
    $(".join-modal-wrap").css("display","flex");
    
});
$(".input-wrap>input[type=button]").on("click",function(){
    $(".join-modal-wrap").css("display","none");
});



$(document).mouseup(function (e){
	if($(".login-modal").has(e.target).length === 0){
		$(".login-modal").fadeOut(500);
	}
});