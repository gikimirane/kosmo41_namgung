<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>WebPage Project</title>
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

<script>
	
</script>

</head>
<body>
<jsp:include page="home_a.jsp" />
<div class="container">
	
        <div class="jumbotron">
          <h1 class="display-3">WELCOME, PLACIDO COFFEE ! </h1>
          <p class="lead">
         	반갑습니다! PLACIDO COFFEE 입니다. <br>
         	회원가입 후 많은 정보를 얻으시길 바랍니다.
          </p>
          <p>
           <a class="btn btn-lg btn-success" href="#" role="button">SIGN UP TODAY!</a></p>
        </div>

        <div class="row marketing">
          <div class="col-lg-6">
            <h4>PLACIDO COFFEE</h4>
            <p>DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE </p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
          </div>

          <div class="col-lg-6">
            <h4>PLACIDO COFFEE</h4>
            <p>DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE DUKES COFFEE </p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
          </div>
        </div>
      
</div>
<jsp:include page="footer.jsp" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
