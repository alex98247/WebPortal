<%--
https://bootswatch.com
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Games</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/theme.css" rel="stylesheet">
    <link href="../css/template.css" rel="stylesheet">
</head>
<style>
    header {
        background-image: url('https://images.unsplash.com/photo-1489846986031-7cea03ab8fd0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=a6c62b8e2662924c44c4725b8286ed60&auto=format&fit=crop&w=1600&q=80');
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center top;
    }
</style>
<body>
<header class="bg-primary">
    <div class="container h-100">
        <div class="row h-100 row align-items-center">
            <div class="col-12">
                <div class="text-center m-0 vh-100 d-flex flex-column justify-content-center text-light">
                    <h1 class="display-4">Mr. Mironov and Mr. Putnikov presents</h1>
                    <p class="lead">Games and their Companies</p>
                </div>
            </div>
        </div>
    </div>
</header>
<table class="table table-dark">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Year</th>
        <th>Company</th>
        <th>Description</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${games}" var="game">
        <tr>
            <td>${game.getId()}</td>
            <td>${game.getName()}</td>
            <td>${game.getYear()}</td>
            <td><a href="company?companyId=${game.getCompany().getId()}"> ${game.getCompany().getName()}</a></td>
            <td>${game.getDescription()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<footer id="footer" class="bg-dark text-light py-5">
    <div class="container py-5">
        <div class="row">
            <div class="col-6 col-md-6 col-lg-3 mb-2">
                <h6 class="text-uppercase">Thanks to</h6>
                <ul class="nav flex-column">
                    <li>Spring Boot</li>
                    <li>Gradle</li>
                    <li>TomCat</li>
                    <li>and others</li>
                </ul>
            </div>
            <div class="col-12 col-md-12 col-lg-6 mb-2 text-right">
                <h6 class="text-uppercase">Follow us for more information</h6>
                <ul class="nav flex-column">
                    <li>Alex Mironov</li>
                    <li>Semen Putnikov</li>
                </ul>
            </div>
        </div>
        <!--/row-->
    </div>
</footer>
</body>
</html>
