<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-dark">
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
