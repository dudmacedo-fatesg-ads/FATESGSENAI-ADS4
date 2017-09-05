<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Usuários</title>
    </head>
    <body>
        <table border=1>
            <thead>
                <tr>
                    <th>CPF</th>
                    <th>Data de Cadastro</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>E-mail</th>
                    <th>Celular</th>
                    <th>Sexo</th>
                    <th>Status</th>
                    <th colspan=2>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.userid}" /></td>
                        <td><c:out value="${user.firstName}" /></td>
                        <td><c:out value="${user.lastName}" /></td>
                        <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.dob}" /></td>
                        <td><c:out value="${user.email}" /></td>
                        <td><a href="UserController?action=edit&userId=<c:out value="${user.userid}"/>">Update</a></td>
                        <td><a href="UserController?action=delete&userId=<c:out value="${user.userid}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="UserController?action=insert">Add User</a></p>
    </body>
</html>