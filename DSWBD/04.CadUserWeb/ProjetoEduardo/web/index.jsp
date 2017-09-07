<%-- 
    Document   : index
    Created on : 29/08/2017, 20:07:57
    Author     : eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
    </head>
    <body>
        <jsp:forward page="/EduardoController?action=list" />
    </body>
</html>
