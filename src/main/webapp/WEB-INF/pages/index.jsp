<%-- 
    Document   : index
    Created on : 28.06.2017, 16:14:02
    Author     : desolation
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Manager</title>
    </head>
    <body>
        <a href="<c:url value="/books"/>">Log as SuperAdmin</a>
        <h1>Welcome to Library. You can order any book you want by filling the form below.</h1>
        <spring:url value="/order/buy" var="order"/>
        <form:form modelAttribute="client" action="${order}">
            <table >
                    <tr>
            <td ><form:label path="firstName">First Name</form:label>
                <form:input path="firstName" type="text" placeholder="First Name"/></td></tr>
                    <tr><td width="100px"> <form:label path="lastName">Last Name</form:label>
                            <form:input path="lastName" type="text" placeholder="Last Name"/></td></tr>
                    <tr>  <td> <form:label path="address">Address</form:label>
                            <form:input path="address" type="text" placeholder="Address"/></td></tr>
                </table>
                            <button type="submit">Make an order</button>
         <c:if test="${!empty bookList}">
            <table border="1" class="table_blur">
                <tr>
                    <td>id</td>
                    <td>title</td>
                    <td>author</td>
                    <td>genre</td>
                    <td>description</td>
                    <td>price</td>
                    <td>Order book</td>
                </tr>
                <hr>
                <h4>Select any book you want to order.</h4>
                <c:forEach items="${bookList}" var="book">
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.author.authorName}</td>
                    <td>${book.genre.genreTitle}</td>
                    <td>${book.bookDescription}</td>
                    <td>${book.bookPrice}</td>
                    <td>
                        <form:checkbox path="bookCounter" value="${book.id}"/> add</td>
            </tr> 
                
                 </c:forEach>
            </c:if>
            
            </form:form>
            <div id="sortByAuthor">
                Get book list by author name and surname<br><br>
                <spring:url value="/books/author" var="byAuthor"/>
                <form:form modelAttribute="author" action="${byAuthor}">
                    <form:label path="authorName">First Name</form:label><br>
                <form:input path="authorName" type="text" placeholder="First Name"/><br>
                <form:label path="authorSurname">Surname</form:label><br>
                <form:input path="authorSurname" type="text" placeholder="Surname"/><br>
                <button type="submit">Apply</button>
            </form:form>
                </div>
                <hr>
                <style>
                    #sortByAuthor{
                        position: absolute;
                        width: 30%;
                        left:20%;
                        top:25%;
                        
                    }
                    </style>
    </body>
</html>
