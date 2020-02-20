<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta name="description" content="">
  	<meta name="author" content="">

  	<title>Grayscale - Start Bootstrap Theme</title>

  	<!-- Bootstrap core CSS -->
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">

  	<!-- Custom fonts for this template -->
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"">
  	<link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
  	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  	<!-- Custom styles for this template -->
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/grayscale.css">

<style>
	.prodimg {
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out;   /* 부드러운 모션을 위해 추가*/
}
	.prodimg:hover {
  transform: scale(1.2);
  -webkit-transform: scale(1.2);
  -moz-transform: scale(1.2);
  -ms-transform: scale(1.2);
  -o-transform: scale(1.2);
}
</style>

</head>
<body id="page-top">

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Start Bootstrap</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#projects">Projects</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#signup">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>


  <!-- Projects Section -->
  <section id="projects" class="projects-section bg-light">
    <article>
    	<div id ="imgarea" style = "text-align: center; vertical-align: middle">
    		<img class=".prodimg" src = "/test/resources/img/1.png" style = "width:20%">
    		<img class=".prodimg" src = "/test/resources/img/1.png" style = "width:20%">
    		<img class=".prodimg" src = "/test/resources/img/1.png" style = "width:20%">
    	</div>
 		<div id = "spectable">
 		
 		</div>
 		<div>
 		</div>
 		
 		
    </article>
  </section>

  <!-- Footer -->
  <footer class="bg-black small text-center text-white-50">
    <div class="container">
      Copyright &copy; Your Website 2019
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
 <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
 <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>

  <!-- Plugin JavaScript -->
 <script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>
  <!-- Custom scripts for this template -->
 <script src="<c:url value="/resources/js/grayscale.js" />"></script>

</body>
</html>