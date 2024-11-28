<html lang="en">
<head>
    <style>
    table, th, td {
      border: 1px solid black;
    }
    </style>
    <meta charset="UTF-8">
    <title>testPage</title>
</head>
<body style="background-color: gray">
<center>
    <table>
        <tr>
            <td>hello</td>
            <td><a href="rest/hello">hello</a></td>
        </tr>
    </table>

    <form name="Form2"
          method="post"
          action="rest/services/employee/create">
          <tr>
              <td>Employee name</td>
              <td><input type="text" name="empName" size="25" value=""></td>
          </tr>
          <input type="submit" value="Submit 2">
    </form>

    <form name="Form3"
          method="post"
          action="rest/services/address/create">
          <tr>
              <td>Street</td>
              <td><input type="text" name="street" size="25" value=""></td>
          </tr>
          <tr>
              <td>House Number</td>
              <td><input type="text" name="houseNumber" size="25" value=""></td>
          </tr>
          <tr>
              <td>City</td>
              <td><input type="text" name="city" size="25" value=""></td>
          </tr>
          <input type="submit" value="Submit 3">
    </form>

    <%-- <form name="Form2"
          method="post"
          enctype="multipart/form-data"
          action="services/uploadPD">
          <input type="file" name="pd">
          <!--<input type="text" name="InText" size="25" value="">-->
          <input type="submit" value="Upload">
    </form> --%>

</center>
</body>
</html>