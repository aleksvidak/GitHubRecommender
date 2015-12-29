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
		<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet">
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

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center"><c:out value="${projectName}" /></h2>
            <div class="text-justify"><c:out value="${projectReadme}" /></div>
            
        </div>

    </div>
    <div class="row">
            <div class="col-md-12">
            <h2 class="text-left">Recommended Projects</h2>
            <table>
			<c:forEach items="${simProjects}" var="sim">
<tr>
<c:set var="count" value="${count + 1}" scope="page"/>


		        <td class="text-justify"><c:out value = "${count}"></c:out>. <b><c:out value="${sim.simProjectName}"/></b> - <c:out value="${sim.simProjectDescription}"/></td>
<%-- 		        <li>Similarity: <c:out value="${sim.similarity}"/></li>   --%>
</tr>
			</c:forEach>
    			</table>



        </div>
    </div>

    <div class="row">
        <div class="col-md-8 text-justify">                  
            <br />
            <br />
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">
                <span class="glyphicon glyphicon-hand-left"></span> Back to search
            </a>
        </div> 
    </div>
    <br>
</div>
</div><!-- /.container -->
	<!-- script references -->
		
	</body>
</html>