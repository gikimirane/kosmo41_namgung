


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