function infoConfirm(){
	if(document.reg_frm.id2.value.length==0){
		alert("아이디는 필수사항입니다.");
		reg_frm.id2.focus();
		return;
	}
	
	if(document.reg_frm.id2.value.length<4){
		alert("아이디는 4글자 이상이여야 합니다.");
		reg.frm.id2.focus();
		return;
	}
	
	if(document.reg_frm.pw2.value.length==0){
		alert("비밀번호는 필수사항입니다.");
		reg_frm.pw2.focus();
		return;
	}
	
	if(document.reg_frm.pw2.value!=document.reg_frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw2.focus();
		return;
	}
	
	if(document.reg_frm.name.value.length==0){
		alert("이름은 필수사항입니다.");
		reg_frm.name.focus();
		return;
	}

	if(document.reg_frm.eMail.value.length==0){
		alert("메일은 필수사항입니다.");
		reg_frm.eMail.focus();
		return;
	}
	document.reg_frm.submit();
}


function updateInfoConfirm(){
	if(document.reg_frm.pw.value==""){
		alert("패스워드를 입력하세요.");
		document.reg_frm.pw.focus();
		return;
	}
	if(document.reg_frm.pw.value!=document.reg_frm.pw_check.value){
		alert("패스워드가 일치하지 않습니다.");
		document.reg_frm.pw.focus();
		return;
	}
	if(document.reg_frm.eMail.value.length==0){
		alert("이메일은 필수사항입니다.");
		document.reg_frm.eMail.focus();
		return;
	}
	document.reg_frm.submit();
}

function deleteInfoConfirm(){
	if(document.reg_frm.pw.value==""){
		alert("패스워드를 입력하세요.");
		document.reg_frm.pw.focus();
		return;
	}
	if(document.reg_frm.pw.value!=document.reg_frm.pw_check.value){
		alert("패스워드가 일치하지 않습니다.");
		document.reg_frm.pw.focus();
		return;
	}
	
	document.reg_frm.submit();
}