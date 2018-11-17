<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Edit Game</h1>
<form:form method="POST" modelAttribute="game" action="/game/update" >
    <table >
        <tr>
            <td></td>
            <td><form:hidden  path="id" /></td>
        </tr>
        <tr>
            <td>Name : </td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Description :</td>
            <td><form:input path="description" /></td>
        </tr>
        <tr>
            <td>Year :</td>
            <td><form:input path="year" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:hidden path="company.id" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:hidden path="company.name" /></td>
        </tr>
        <tr>
            <td></td>
            <td><form:hidden path="company.country" /></td>
        </tr>

        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save" /></td>
        </tr>
    </table>
</form:form>
