<%@page import="java.util.List"%>
<%@page import="br.com.eduardo.model.Eduardo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/styles.css" type="text/css">
        <title>Mostrar Usuários</title>
    </head>
    <body>
        <table border="1" cellspacing="0">
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
                <%
                    if (((List<Eduardo>) request.getAttribute("users")).size() < 1) {
                %>
                <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
                <%
                } else {
                %>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.eduardocpf}" /></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${user.eduardodatacadastro}" /></td>
                        <td><c:out value="${user.eduardonome}" /></td>
                        <td><c:out value="${user.eduardoendereco}" /></td>
                        <td><c:out value="${user.eduardoemail}" /></td>
                        <td><c:out value="${user.eduardocelular}" /></td>
                        <td><c:out value="${user.eduardosexo}" /></td>
                        <td><c:out value="${user.eduardostatus}" /></td>
                        <td><a href="UserController?action=edit&userId=<c:out value="${user.eduardocpf}"/>">Update</a></td>
                        <td><a href="UserController?action=delete&userId=<c:out value="${user.eduardocpf}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                <%
                    }
                %>
            </tbody>
        </table>
        <p><a href="UserController?action=insert">Add User</a></p>
    </body>
</html>