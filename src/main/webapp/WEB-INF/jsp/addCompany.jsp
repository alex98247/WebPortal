<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Edit Company</h1>
<form:form method="POST" modelAttribute="company" action="/company/update" >
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
            <td>Country :</td>
            <td><form:input path="country" /></td>
        </tr>

        <tr>
            <td> </td>
            <td><input type="submit" value="Edit Save" /></td>
        </tr>
    </table>
</form:form>
