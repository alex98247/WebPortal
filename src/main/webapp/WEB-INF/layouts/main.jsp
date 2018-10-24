<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
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
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="menu"/>

<tiles:insertAttribute name="body"/>

<tiles:insertAttribute name="footer"/>
</body>
</html>
