<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>Fill all items</h1>
  <br>
  <div class="input-group">
    <form action="/PDFProfile/form" method="post">
      <input type="text" name="firstName" class="form-control"
           placeholder="First name" aria-describedby="basic-addon1">
      <h5> * min 2, max 20 letters</h5>
      <br><br>

      <input type="text" name="lastName" class="form-control"
             placeholder="Last name" aria-describedby="basic-addon1">
      <h5> * min 2, max 20 letters</h5>
      <br><br>

      <input type="text" name="passport" class="form-control"
             placeholder="Passport" aria-describedby="basic-addon1">
      <h5> * min 8, max 14 letters and numbers</h5>
      <br><br>

      <input class="btn btn-default" type="submit" value="Submit">
    </form>
  </div>
</body>
</html>
