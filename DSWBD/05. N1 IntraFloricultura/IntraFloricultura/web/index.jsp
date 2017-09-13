<%-- 
    Document   : index
    Created on : 12/09/2017, 20:30:27
    Author     : eduardo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>Hospital São Marcos - Intranet</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp"><i>Floricultura - Intranet</i></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <!--<li>
                            <a href="index.jsp">Início</a>
                        </li>-->
                        <li>
                            <a href="cadClientes">Clientes</a>
                        </li>
<!--                        <li>
                            <a href="index.jsp?p=cadProdutos">Produtos</a>
                        </li>-->
                    </ul>
                    <!--<ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>-->
                </div>
            </div>
        </nav>
        <div class="container-fluid text-center">    
            <div class="row content">

                
                
            </div>
        </div>

        <footer class="container-fluid text-center">
            <p>© Floricultura</p>
        </footer>
    </body>
</html>
