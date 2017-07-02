<%-- 
    Document   : error
    Created on : 01.07.2017, 20:29:45
    Author     : dsltn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String select = (String)request.getAttribute("errorMessage");
            out.println(select);
            String getback = (String)request.getAttribute("goBack");
            out.println(getback);
            %>
    </body>
</html>
