<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tutorial. Spring Data JPA</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
    </tr>
    <c:forEach items="${list}" var="user" varStatus="status">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>