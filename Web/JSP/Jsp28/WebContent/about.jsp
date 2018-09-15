<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

	<style>
	.sub-name1{
			border-width:1px;
			border-style:solid;       /* solid */
			border-color:black;
		}
	</style>
	
</head>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=0lgXJBQIHmJL00Z801JN&submodules=geocoder"></script>

<body>

<jsp:include page="home_a.jsp"/>

<div class="container">
	
	<div class="row">INSTAGRAM > <a href="https://www.instagram.com/placido_coffee">https://www.instagram.com/placido_coffee</a></div>
	<div class="row">
   	<div id="map" style="width:100%;height:400px;"></div>
  	</div>
    <script>
      var map = new naver.maps.Map('map');
      var myaddress = '독막로5길 32';// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
      naver.maps.Service.geocode({address: myaddress}, function(status, response) {
          if (status !== naver.maps.Service.Status.OK) {
              return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
          }
          var result = response.result;
          // 검색 결과 갯수: result.total
          // 첫번째 결과 결과 주소: result.items[0].address
          // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
          var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
          map.setCenter(myaddr); // 검색된 좌표로 지도 이동
          // 마커 표시
          var marker = new naver.maps.Marker({
            position: myaddr,
            map: map
          });
          // 마커 클릭 이벤트 처리
          naver.maps.Event.addListener(marker, "click", function(e) {
            if (infowindow.getMap()) {
                infowindow.close();
            } else {
                infowindow.open(map, marker);
            }
          });
          // 마크 클릭시 인포윈도우 오픈
          var infowindow = new naver.maps.InfoWindow({
              content: '<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyY6T2_9PJdZbS31xZ5gDKPWIWlD_g5EUcciZfksTxN4dfd7o5" width=100px height=100px style="border-style:none;">'
          });
      });
      </script>
      
      <br><br>
      
      <div class="row" align="center">
	     <div class="col"><img src="https://scontent.cdninstagram.com/vp/573ba054016a30b0c92cc4f545fddbbb/5C049941/t51.2885-15/sh0.08/e35/s640x640/38022181_2208841679361458_1070674667594842112_n.jpg" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKi1K0bnbqKjALYlJBtDbdnchDoDGd3tp8B32r00hXohhms59f" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVkxjMKKld4z4jpAfSESTaz_vkHt6_lhYQiKiicUcqQgLXPGCl" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://scontent-lhr3-1.cdninstagram.com/vp/fa5b05dae2dab14d7780bd7494334b34/5C1921C8/t51.2885-15/e35/39596403_468235863680180_3249153475367403520_n.jpg?se=7&ig_cache_key=MTg1ODYwMjI0NjYyODA3MjUzNw%3D%3D.2" width=200px height=200px alt="..." class="img-rounded"></div>
	      
      </div>
      
      	<div class="row" >
			
			 <br>
			 ADDRESS > 서울특별시 마포구 서교동 독막로5길 32
			 <br>
			 TEL > 02-324-4916
			 <br>
			 
		</div>
     
      
            
</div>
<jsp:include page="footer.jsp" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
