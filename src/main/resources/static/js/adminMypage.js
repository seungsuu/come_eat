
		const tabs = $(".mypage-tab>li");
		tabs.on("click",function(){
			tabs.removeClass("active-tab");
			tabs.children().removeClass("active-tab");
			$(this).addClass("active-tab");
			if($(this).children().is("a")){
				$(this).children().addClass("active-tab");
			}
			const mypagecontent = $(".mypage-content");
			mypagecontent.hide();
			const index = tabs.index(this);
			mypagecontent.eq(index).show();
		});
		$(function(){
		    tabs.eq(0).click();
		});	
		
		
		$(".checkedChangeLevel").on("click",function(){
			//체크된 체크박스 가져옴
			const check =$(".chk:checked");
			//체크된 체크박스 없이 버튼을 누르면 함수를 종료시킴
			if(check.length == 0) {
				return; //->함수종료.
			}
			//체크된 회원 번호를 저장할 배열
			const no = new Array();
			//체크된 회원 등급을 저장할 배열
			const level = new Array();
			
			check.each(function(index,item){
				const memberNo = $(item).parent().next().text();
				//item은 checkbox
				const memberLevel = $(item).parent().parent().find("select").val();
				//체크박스->td->tr->find()-> 후손 전체중에 검색
				no.push(memberNo);
				level.push(memberLevel);
			});
			location.href="/member/checkedChangeLevel?no="+no.join("/")+"&level="+level.join("/");
		});
		
	
		$(".changeLevel").on("click",function(){
			const memberLevel = $(this).parent().prev().children().val();
			const memberNo = $(this).parent().parent().children().eq(1).text();
			location.href= "/member/changeLevel?memberNo="+memberNo+"&memberLevel="+memberLevel;
		});