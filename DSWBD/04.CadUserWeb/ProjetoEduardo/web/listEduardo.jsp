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
        <script type="text/javascript" src="js/scripts.js"></script>
        <title>Mostrar Usuários</title>
    </head>
    <body>
        <table border="1" cellspacing="0">
            <thead>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>E-mail</th>
                    <th>Celular</th>
                    <th>Sexo</th>
                    <th>Status</th>
                    <th>Data de Cadastro</th>
                    <th colspan=2>Ação</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (((List<Eduardo>) request.getAttribute("eduardos")).size() < 1) {
                %>
                <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
                <%
                } else {
                %>
                <c:forEach items="${eduardos}" var="eduardo">
                    <tr>
                        <td><c:out value="${eduardo.eduardocpfformat}" /></td>
                        <td><c:out value="${eduardo.eduardonome}" /></td>
                        <td><c:out value="${eduardo.eduardoendereco}" /></td>
                        <td><c:out value="${eduardo.eduardoemail}" /></td>
                        <td><c:out value="${eduardo.eduardocelular}" /></td>
                        <td>
                            <c:if test = "${eduardo.eduardosexo.toString() == 'M'}">Masculino</c:if>
                            <c:if test = "${eduardo.eduardosexo.toString() == 'F'}">Feminino</c:if>
                            <c:if test = "${eduardo.eduardosexo.toString() == 'O'}">Outro</c:if>
                        </td>
                        <td>
                            <c:if test = "${eduardo.eduardostatus == true}">Ativo</c:if>
                            <c:if test = "${eduardo.eduardostatus == false}">Inativo</c:if>
                        </td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${eduardo.eduardodatacadastro}" /></td>
                        <td><a href="EduardoController?action=edit&eduardocpf=<c:out value="${eduardo.eduardocpf}"/>">Editar</a></td>
                        <td><a href="EduardoController?action=delete&eduardocpf=<c:out value="${eduardo.eduardocpf}"/>" onclick="return questiona('Você tem certeza que deseja excluir o usuário?')">Excluir</a></td>
                    </tr>
                </c:forEach>
                <%
                    }
                %>
            </tbody>
        </table>
        <p><a href="EduardoController?action=insert">Adicionar</a></p>
    </body>
    <c:if test = "${alert != null}">
        <script type="text/javascript">
            alert('<c:out value="${alert}"/>');
        </script>
    </c:if>
</html>