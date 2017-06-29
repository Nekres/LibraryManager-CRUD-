<%-- 
    Document   : books
    Created on : 28.06.2017, 15:45:13
    Author     : desolation
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${!empty bookList}">
            <table>
                <tr>
                    
                </tr>
                <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                </tr>
                 </c:forEach>
            </table>
        </c:if>
    </body>
</html>
