<%-- 
    Document   : details
    Created on : 04.07.2017, 0:05:24
    Author     : dsltn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Details</title>
    </head>
    <body>
        <h3 align="center"> You can see full details of book here</h3>
            <table border="1">
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
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.author.authorName}</td>
                    <td>${book.author.authorSurname}</td>
                    <td>${book.genre.genreTitle}</td>
                    <td>${book.bookDescription}</td>
                    <td>${book.bookPrice}</td> 
    </body>
</html>
