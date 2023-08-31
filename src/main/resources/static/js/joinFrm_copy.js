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
        comment.eq(1).text("사용가능한 비밀번호 입니다.");
        comment.eq(1).css("color","black");
        comment.eq(1).css("font-size","14px");
        comment.eq(1).css("font-weight","400");

    }else{
        comment.eq(1).text("영문+숫자+특수문자를 조합하여 8~16자리를 입력해주세요.");
        comment.eq(1).css("color","red");
        comment.eq(1).css("font-size","14px");
        comment.eq(1).css("font-weight","400");
        
    }
});
//비밀번호 비밀번호 확인 하기
$("#memberPwRe").on("change",function(){
    //비밀번호 확인 : 비밀번호와 값이 같은지
    const pwValue = $("#memberPw").val();
    const pwReValue = $("#memberPwRe").val();
    if(pwValue == pwReValue){
        comment.eq(2).text("비밀번호가 일치합니다.");
        comment.eq(2).css("color","black");
        comment.eq(2).css("font-size","14px");
        comment.eq(2).css("font-weight","400");
    }else{
        comment.eq(2).text("비밀번호가 일치하지않습니다.");
        comment.eq(2).css("color","red");
        comment.eq(2).css("font-size","14px");
        comment.eq(2).css("font-weight","400");
    }
});
$("#memberName").on("change",function(){
    const nameReg = /^[가-힣]{2,}$/;
    const nameValue = $(this).val();
    const check = nameReg.test(nameValue);
    if(check){
        comment.eq(3).text("사용가능한 이름입니다.");
        comment.eq(3).css("color","black");
        comment.eq(3).css("font-size","14px");
        comment.eq(3).css("font-weight","400");
    }else{
        comment.eq(3).text("이름은 한글만(최소2글자) 입력 가능합니다.");
        comment.eq(3).css("color","red");
        comment.eq(3).css("font-size","14px");
        comment.eq(3).css("font-weight","400");
    }
});
$("#memberPhone").on("change",function(){
    	console.log("출력");
    	const phoneReg = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
    	const phoneValue = $(this).val();
    	const check = phoneReg.test(phoneValue);
    	if(check) {
    		comment.eq(4).text("사용가능한 전화번호 입니다.");
    		comment.eq(4).css("color","black");
    		comment.eq(4).css("font-size","14px");
    		comment.eq(4).css("font-weight","400");
    	}else {
    		comment.eq(4).text("(-)를 포함해 올바른 양식으로 입력해주세요.");
    		comment.eq(4).css("color","red");
    		comment.eq(4).css("font-size","14px");
    		comment.eq(4).css("font-weight","400");
    	}
});	

/* 모달 */
$(".modal-open-btn").on("click",function(){
    $(".join-modal-wrap").css("display","flex");
    
});


$(".join-modal-close").on("click",function(){
    $(".join-modal-wrap").css("display","none");
});

/* 모달 */
$(".modal-open-btn2").on("click",function(){
    $(".join-modal-wrap-privacy").css("display","flex");
    
});


$(".join-modal-close").on("click",function(){
    $(".join-modal-wrap-privacy").css("display","none");
});


//아이디 중복검사
$("#memberId").on("change",function(){
	const memberId = $("#memberId").val();
    	//console.log(memberId);
    const idReg = /^[a-zA-z0-9]{4,8}$/;
    if(idReg.test(memberId)) {
     $.ajax({
      url : "/member/ajaxCheckId",
      type : "get",
      data : {memberId : memberId},
      success : function(data) {
      if(data == "0"){
						comment.eq(0).text("사용가능한 아이디 입니다.");
						comment.eq(0).css("color","black");
						comment.eq(0).css("font-size","14px");
						comment.eq(0).css("font-weight","400");
					}else {
						comment.eq(0).text("이미 사용중인 아이디 입니다.");
						comment.eq(0).css("color","red");
						comment.eq(0).css("font-size","14px");
						comment.eq(0).css("font-weight","400");
						
					}
				}
			});
		}else {
			comment.eq(0).text("아이디는 영어/숫자로 4~8글자 입니다.");
			comment.eq(0).css("color","red");
			comment.eq(0).css("font-size","14px");
			comment.eq(0).css("font-weight","400");
		}		
	});
	
	


    
