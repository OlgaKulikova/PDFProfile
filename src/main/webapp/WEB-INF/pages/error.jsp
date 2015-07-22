<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error</title>
  <link href="<c:url value="/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css"/>"
        rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="panel panel-danger">
    <div class="panel-heading">Error</div>
    <div class="panel-body">
      Failed URL: <c:out value="${url}"/>
      Message: <c:out value="${ErrorMessage}"/>
    </div>
  </div>
</div>
</body>
</html>
