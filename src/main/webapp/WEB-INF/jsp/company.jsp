<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 21.10.2018
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Company</title>
</head>
<body>
<table border="2">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Country</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>${company.getId()}</td>
        <td>${company.getName()}</td>
        <td>${company.getCountry()}</td>
    </tr>

    </tbody>
</table>
</body>
</html>
