<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>  
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>GitHub Recommender</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<script>var myContextPath = "${pageContext.request.contextPath}"</script>
		<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
		<script src="<c:url value="/resources/js/autocustom.js" />"></script>

	</head>
	<body>
<div class="navbar navbar-default navbar-static-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">RS</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#about">About</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</div>

<div class="container">
  
  <div class="text-center">
    <h1>GitHub Repositories Recommendation System</h1>
    <p class="lead">Use this system to quickly get recommendations on any project in the database.</p>
  </div>
<!-- <div>
	<input type="text"  id="w-input-search" value="">
	<span>
	  <button id="button-id" type="button">Search</button>
	</span>
  </div> -->
	<div class="row" style="padding-top:25px;">       
        <div id="custom-search-input">
            <div class="input-group col-md-12">
                <input type="text" class="  search-query form-control" placeholder="Search projects" id="w-input-search" />
                <span class="input-group-btn">
                    <button class="btn btn-success" type="button" id="search">
                        <span class=" glyphicon glyphicon-search"></span>
                        <span class="glyphicon glyphicon glyphicon-refresh" id="anim"></span>
                    </button>
                </span>
            </div>
        </div>
    </div>

</div><!-- /.container -->
	<!-- script references -->
		
	</body>
</html>