let index = {
	init: function() {
		$("#btn-save").on("click", () => { //()=>쓰는 이유는 this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-update").on("click", () => { //()=>쓰는 이유는 this를 바인딩하기 위해서
			this.update();
		});

		//$("#btn-login").on("click", ()=>{ //()=>쓰는 이유는 this를 바인딩하기 위해서
		//this.login();
		//});

	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		//console.log(data);

		//ajax는 비동기 호출이 기본
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청을 한다
		$.ajax({ //회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json" // 서버에 요청해서 응답이 왔을때 문자열을 javascript로 바꿔줌
		}).done(function(resp) {
			if (resp.status === 500) { //중복 가입 방지
				alert("회원가입이 실패하였습니다");
			} else {
				alert("회원가입이 완료되었습니다");
				location.href = "/"
			}

		}).fail(function() {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json" // 서버에 요청해서 응답이 왔을때 문자열을 javascript로 바꿔줌
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다");
			//console.log(resp);
			location.href = "/"
		}).fail(function() {
			alert(JSON.stringify(error));
		});
	},
	/* 스프링 시큐리티 사용 안 할 때의 로그인
		login: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};
		
		$.ajax({ //로그인 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터 타입
			dataType: "json" // 서버에 요청해서 응답이 왔을때 문자열을 javascript로 바꿔줌
		}).done(function(resp){ 
			alert("로그인이 완료되었습니다");
			//console.log(resp);
			location.href = "/"
		}).fail(function(){ 
			alert(JSON.stringify(error));
		}); 
	}
	*/
}

index.init();