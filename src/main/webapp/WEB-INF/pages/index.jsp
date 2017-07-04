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
        <h1 align="center">Welcome to Library.<h1> 
                
                <h4>You can order any book you want by filling the form below.</h4>
                <p>You need to choose books from table you would like to get</p>
        <spring:url value="/order/buy" var="order"/>
        <form:form modelAttribute="client" action="${order}">
            <h4>Make an order</h4>
            <table >
                    <tr>
            <td ><form:label path="firstName">First Name</form:label>
                <form:input path="firstName" type="text" placeholder="First Name"/></td>
            <td>  <form:errors path="firstName" cssClass="error"> </form:errors></td></tr>
                    <tr><td width="100px"> <form:label path="lastName">Last Name</form:label>
                            <form:input path="lastName" type="text" placeholder="Last Name"/></td>
                    <td>  <form:errors path="lastName" cssClass="error"> </form:errors></td></tr>
                    <tr>  <td> <form:label path="address">Address</form:label>
                            <form:input path="address" type="text" placeholder="Address"/></td>
                    <td>  <form:errors path="address" cssClass="error"> </form:errors></td></tr>
                </table>
                            <button class="button" type="submit">Make an order</button>
         <c:if test="${!empty bookList}">
            <table border="1" class="table_blur">
                <tr>
                    <td>id</td>
                    <td>title</td>
                    <td>author Name</td>
                    <td>author Surname</td>
                    <td>genre</td>
                    <td>description</td>
                    <td>price</td>
                    <td>Order book</td>
                    <td>Book details</td>
                </tr>
                <hr>
                <h4>Select any book you want to order.</h4>
                <c:forEach items="${bookList}" var="book">
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.author.authorName}</td>
                    <td>${book.author.authorSurname}</td>
                    <td>${book.genre.genreTitle}</td>
                    <td>${book.bookDescription}</td>
                    <td>${book.bookPrice}</td>
                    <td>
                        <form:checkbox path="bookCounter" value="${book.id}"/> add</td>
                    <td><a href="<c:url value="/details/${book.id}"/>"/>see details</a>
            </tr> 
                
                 </c:forEach>
            </c:if>
            
            </form:form>
            <div id="sortByAuthor">
                <b>Get book list by author name and surname
                </b>
                (Leave empty to get all books)<br><br>
                <spring:url value="/books/author" var="byAuthor"/>
                <form:form modelAttribute="author" action="${byAuthor}">
                    <form:label path="authorName">First Name</form:label><br>
                <form:input path="authorName" type="text" placeholder="First Name"/><br>
                
                <form:label path="authorSurname">Surname</form:label><br>
                <form:input path="authorSurname" type="text" placeholder="Surname"/><br>
                <button class ="button" type="submit">Apply</button>
            </form:form>
                </div>
                <div id="sortByGenre">
                    <b>Get book list by genre</b><br>
                    (Leave empty to get all books)<br><br>
                <spring:url value="/books/genre" var="byGenre"/>
                <form:form modelAttribute="genre" action="${byGenre}">
                    <form:label path="genreTitle">Genre</form:label><br>
                <form:input path="genreTitle" type="text" placeholder="First Name"/><br>
                <button class="button" type="submit">Apply</button>
            </form:form>
                </div>
                <hr>
                <style>
                    #sortByAuthor{
                        position: absolute;
                        width: 30%;
                        height: 30%;
                        left:37%;
                        top:26%;
                        border: 3px solid #e3eef7;
                        
                    }
                    #sortByGenre{
                        border: 3px solid #e3eef7;
                        position:absolute;
                        width:30%;
                        height: 30%;
                        left: 69%;
                        top:25%;
                    }
                    .button{
                        background-color: #f44336;
                    }
                    .table_blur {
  background: #f5ffff;
  border-collapse: collapse;
  text-align: left;
}
.table_blur th {
  border-top: 1px solid #777777;	
  border-bottom: 1px solid #777777; 
  box-shadow: inset 0 1px 0 #999999, inset 0 -1px 0 #999999;
  background: linear-gradient(#9595b6, #5a567f);
  color: white;
  padding: 10px 15px;
  position: relative;
}
.table_blur th:after {
  content: "";
  display: block;
  position: absolute;
  left: 0;
  top: 25%;
  height: 25%;
  width: 100%;
  background: linear-gradient(rgba(255, 255, 255, 0), rgba(255,255,255,.08));
}
.table_blur tr:nth-child(odd) {
  background: #ebf3f9;
}
.table_blur th:first-child {
  border-left: 1px solid #777777;	
  border-bottom:  1px solid #777777;
  box-shadow: inset 1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur th:last-child {
  border-right: 1px solid #777777;
  border-bottom:  1px solid #777777;
  box-shadow: inset -1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur td {
  border: 1px solid #e3eef7;
  padding: 10px 15px;
  position: relative;
  transition: all 0.5s ease;
}
.table_blur tbody:hover td {
  color: transparent;
  text-shadow: 0 0 3px #a09f9d;
}
.table_blur tbody:hover tr:hover td {
  color: #444444;
  text-shadow: none;
}
.error { color: red; font-size: 0.9em; font-weight: bold; }
                    </style>
    </body>
</html>
