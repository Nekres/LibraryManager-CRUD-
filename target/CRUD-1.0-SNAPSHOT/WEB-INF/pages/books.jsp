<%-- 
    Document   : books
    Created on : 28.06.2017, 15:45:13
    Author     : desolation
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to Library Manager admin-mode</h1>
        <c:if test="${!empty bookList}">
            <table border="1">
                <tr>
                    <td>book id</td>
                    <td>book title</td>
                    <td>book author</td>
                    <td>book genre</td>
                    <td>book description</td>
                    <td>book price</td>
                </tr>
                <hr>
                <h4>Existing books:</h4>
                
                <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.bookGenre}</td>
                    <td>${book.bookDescription}</td>
                    <td>${book.bookPrice}</td>
                </tr>
                 </c:forEach>
                </c:if>
                
            </table>
        <hr>
        <h3>Add new book</h3>
                <spring:url value="/books/add" var="addBookAction" />
                <form:form modelAttribute="book" action="${addBookAction}">
                    <spring:bind path="bookTitle">
                        <form:label path="bookTitle">Title</form:label><br> 
                    <form:input path="bookTitle" type="text" placeholder="bookTitle"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookAuthor">
                        <form:label path="bookAuthor">Author</form:label><br> 
                    <form:input path="bookAuthor" type="text" placeholder="bookAuthor"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookGenre">
                        <form:label path="bookGenre">Genre</form:label><br> 
                    <form:input path="bookGenre" type="text" placeholder="bookGenre"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookDescription">
                        <form:label path="bookDescription">Description</form:label><br> 
                    <form:input path="bookDescription" type="text" placeholder="bookDescription"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookPrice">
                        <form:label path="bookPrice">Price</form:label><br> 
                    <form:input path="bookPrice" type="number" placeholder="bookPrice"/>
                    </spring:bind>
                        <button type="submit">Add</button>
                        <br> 
              </form:form>
            <hr>
        
        
    </body>
</html>
