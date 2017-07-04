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
        <style>
            .error { color: red; font-size: 0.9em; font-weight: bold; }
                    </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator's panel</title>
        <spring:url value="/resources/css/styles.css" var="mainCss" />
        <link href="${mainCss}" rel="stylesheet" />
    </head>
    <body>
        <a href="<c:url value="/"/>">Go back to client-part</a>
        <h1>Welcome to Library Manager admin-mode</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>Welcome : ${pageContext.request.userPrincipal.name}
				<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
				<c:url var="logoutUrl" value="/logout" />
			     <form action="${logoutUrl}" id="logout" method="post">
			      <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
			      <input type="submit" name="submit" value="Log Out">
			     </form>
			</c:if>
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
                </tr>
                <hr>
                <h4>Existing books:</h4>
                
                <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.author.authorName}</td>
                    <td>${book.author.authorSurname}</td>
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
                <form:form modelAttribute="bookAdd" action="${addBookAction}">
                    <spring:bind path="bookTitle">
                        <form:label path="bookTitle">Title</form:label><br> 
                    <form:input path="bookTitle" type="text" placeholder="bookTitle"/>
                    <form:errors path="bookTitle" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="author.authorName">
                            <form:label path="author.authorName">Author Name</form:label><br> 
                            <form:input path="author.authorName" type="text" placeholder="name"/>
                            <form:errors path="author.authorName" cssClass="error"> </form:errors>
                        </spring:bind>
                        <br>
                        <spring:bind path="author.authorSurname">
                            <form:label path="author.authorSurname">Author Surname</form:label><br> 
                            <form:input path="author.authorSurname" type="text" placeholder="surname"/>
                            <form:errors path="author.authorSurname" cssClass="error"> </form:errors>
                        </spring:bind>
                        <br> 
                        <spring:bind path="genre.genreTitle">
                            <form:label path="genre.genreTitle">Genre</form:label><br> 
                            <form:input path="genre.genreTitle" type="text" placeholder="bookGenre"/>
                            <form:errors path="genre.genreTitle" cssClass="error"> </form:errors>
                        </spring:bind>
                        <br> 
                        <spring:bind path="bookDescription">
                        <form:label path="bookDescription">Description</form:label><br> 
                    <form:input path="bookDescription" type="text" placeholder="bookDescription"/>
                    <form:errors path="bookDescription" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookPrice">
                        <form:label path="bookPrice">Price</form:label><br> 
                    <form:input path="bookPrice" type="number" placeholder="bookPrice"/>
                    <form:errors path="bookPrice" cssClass="error"> </form:errors>
                    </spring:bind>
                        <button type="submit">Add</button>
                        <br> 
              </form:form>
            <hr>
            <h3>Edit Book by ID</h3>
            <spring:url value="/books/edit" var="editBookAction" />
                <form:form modelAttribute="bookEdit" action="${editBookAction}">
                    <spring:bind path="id">
                        <form:label path="id">ID</form:label><br>
                        <form:input path="id" type="number" placeholder="ID" />
                        <form:errors path="id" cssClass="error"> </form:errors>
                    </spring:bind><br>
                    <spring:bind path="bookTitle">
                        <form:label path="bookTitle">Title</form:label><br> 
                    <form:input path="bookTitle" type="text" placeholder="bookTitle"/>
                    <form:errors path="bookTitle" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="author.authorName">
                        <form:label path="author.authorName">Author Name</form:label><br> 
                    <form:input path="author.authorName" type="text" placeholder="name"/>
                    <form:errors path="author.authorName" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br>
                        <spring:bind path="author.authorSurname">
                        <form:label path="author.authorSurname">Author Surname</form:label><br> 
                    <form:input path="author.authorSurname" type="text" placeholder="surname"/>
                    <form:errors path="author.authorSurname" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="genre.genreTitle">
                        <form:label path="genre.genreTitle">Genre</form:label><br> 
                    <form:input path="genre.genreTitle" type="text" placeholder="bookGenre"/>
                    <form:errors path="genre.genreTitle" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookDescription">
                        <form:label path="bookDescription">Description</form:label><br> 
                    <form:input path="bookDescription" type="text" placeholder="bookDescription"/>
                    <form:errors path="bookDescription" cssClass="error"> </form:errors>
                    </spring:bind>
                        <br> 
                        <spring:bind path="bookPrice">
                        <form:label path="bookPrice">Price</form:label><br> 
                    <form:input path="bookPrice" type="number" placeholder="bookPrice"/>
                    <form:errors path="bookPrice" cssClass="error"> </form:errors>
                    </spring:bind>
                        <button type="submit">Edit</button>
                        <br> 
              </form:form>
        
        
    </body>
</html>
