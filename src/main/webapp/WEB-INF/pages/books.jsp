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
                    <td>id</td>
                    <td>title</td>
                    <td>author</td>
                    <td>genre</td>
                    <td>description</td>
                    <td>price</td>
                </tr>
                <hr>
                <h4>Existing books:</h4>
                
                <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.author.authorName}</td>
                    <td>${book.genre.genreTitle}</td>
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
                        <spring:bind path="author.authorName">
                        <form:label path="author.authorName">Author Name</form:label><br> 
                    <form:input path="author.authorName" type="text" placeholder="name"/>
                    </spring:bind>
                        <br>
                        <spring:bind path="author.authorSurname">
                        <form:label path="author.authorSurname">Author Surname</form:label><br> 
                    <form:input path="author.authorSurname" type="text" placeholder="surname"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="genre.genreTitle">
                        <form:label path="genre.genreTitle">Genre</form:label><br> 
                    <form:input path="genre.genreTitle" type="text" placeholder="bookGenre"/>
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
            <h3>Edit Book by ID</h3>
            <spring:url value="/books/edit" var="editBookAction" />
                <form:form modelAttribute="book" action="${editBookAction}">
                    <spring:bind path="id">
                        <form:label path="id">ID</form:label><br>
                        <form:input path="id" type="number" placeholder="ID" />
                    </spring:bind><br>
                    <spring:bind path="bookTitle">
                        <form:label path="bookTitle">Title</form:label><br> 
                    <form:input path="bookTitle" type="text" placeholder="bookTitle"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="author.authorName">
                        <form:label path="author.authorName">Author Name</form:label><br> 
                    <form:input path="author.authorName" type="text" placeholder="name"/>
                    </spring:bind>
                        <br>
                        <spring:bind path="author.authorSurname">
                        <form:label path="author.authorSurname">Author Surname</form:label><br> 
                    <form:input path="author.authorSurname" type="text" placeholder="surname"/>
                    </spring:bind>
                        <br> 
                        <spring:bind path="genre.genreTitle">
                        <form:label path="genre.genreTitle">Genre</form:label><br> 
                    <form:input path="genre.genreTitle" type="text" placeholder="bookGenre"/>
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
                        <button type="submit">Edit</button>
                        <br> 
              </form:form>
        
        
    </body>
</html>
