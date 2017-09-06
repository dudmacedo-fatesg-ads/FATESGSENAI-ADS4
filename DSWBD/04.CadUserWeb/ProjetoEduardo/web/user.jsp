<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Adicionar novo usuário</title>
    </head>
    <body>
        <script>
//            $(function () {
//                $('input[name=dob]').datepicker();
//            });
        </script>
        <form method="POST" action='UserController' name="frmAddUser">
            CPF: <input type="text" readonly="readonly" name="eduardocpf" value="<c:out value="${user.eduardocpf}" />" /> <br /> 
            Nome: <input type="text" name="eduardonome" value="<c:out value="${user.eduardonome}" />" /> <br />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>