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
<body>
<center>
    <form name="Form1"
          method="post"
          action="services/getSomething">
    <table>
        <tr>
            <td>Arg1</td>
            <td><input type="text" name="Username" size="25" value=""></td>
        </tr>
        <tr>
            <td>Arg2</td>
            <td><input type="password" name="Password" size="25" value=""></td>
        </tr>
        <tr>
            <td>ArgDB</td>
            <td><input type="text" name="ArgDB" size="25" value=""></td>
        </tr>
    </table>
    <input type="submit" value="Submit">
    </form>

    <form name="Form2"
          method="post"
          action="services/uploadPD">
          <input type="file" name="Choose process data file" id="uploadPD">
          <input type="submit" value="Upload">
    </form>

</center>
</body>
</html>