<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>Login Demo - Kakao JavaScript SDK</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<body>
<div id="plusfriend-addfriend-button"></div>
<div id="plusfriend-chat-button"></div>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('0eb1acd742b3f93613dfd26663dd28cd');
    // 플러스친구 친구추가 버튼을 생성합니다.
    Kakao.PlusFriend.createAddFriendButton({
      container: '#plusfriend-addfriend-button',
      plusFriendId: '_Dmrxjj' // 플러스친구 홈 URL에 명시된 id로 설정합니다.
    });
    
    Kakao.PlusFriend.createChatButton({
        container: '#plusfriend-chat-button',
        plusFriendId: '_Dmrxjj' // 플러스친구 홈 URL에 명시된 id로 설정합니다.
      });
  //]]>
</script>

<a href="javascript:void plusFriendChat()">
  <img src="https://developers.kakao.com/assets/img/about/logos/plusfriend/consult_small_yellow_pc.png"/>
</a>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('0eb1acd742b3f93613dfd26663dd28cd');
    function plusFriendChat() {
      Kakao.PlusFriend.chat({
        plusFriendId: '_Dmrxjj' // 플러스친구 홈 URL에 명시된 id로 설정합니다.
      });
    }
  //]]>
</script>

</body>
</html>