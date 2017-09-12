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
        <script type="text/javascript" src="js/scripts.js"></script>
        <title><c:out value="${title}"/></title>
    </head>
    <body>
        <!--        <script>
                    $(function () {
                        $('input[name=dob]').datepicker();
                    });
                </script>-->
        <form method="POST" action='EduardoController' name="frmAddEduardo">
            <input type="hidden" name="action" value="<c:out value="${action}" />">
            <table>
                <tr>
                    <td>CPF:</td>
                    <td><input type="text" name="eduardocpf"  value="<c:out value="${eduardo.eduardocpf}" />" <c:if test = "${action == 'update'}">readonly="readonly"</c:if>/></td>
                    </tr>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="eduardonome" value="<c:out value="${eduardo.eduardonome}" />"/></td>
                </tr>
                <tr>
                    <td>Endereço:</td>
                    <td><input type="text" name="eduardoendereco" value="<c:out value="${eduardo.eduardoendereco}" />"/></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><input type="text" name="eduardoemail" value="<c:out value="${eduardo.eduardoemail}" />"/></td>
                </tr>
                <tr>
                    <td>Celular:</td>
                    <td><input type="text" name="eduardocelular" value="<c:out value="${eduardo.eduardocelular}" />"/></td>
                </tr>
                <tr>
                    <td>Sexo:</td>
                    <td>
                        <input type="radio" name="eduardosexo" <c:if test = "${eduardo.eduardosexo.toString() == 'M'}">checked</c:if> value="M">Masculino
                        <input type="radio" name="eduardosexo" <c:if test = "${eduardo.eduardosexo.toString() == 'F'}">checked</c:if> value="F">Feminino
                        <input type="radio" name="eduardosexo" <c:if test = "${eduardo.eduardosexo.toString() == 'O'}">checked</c:if> value="O">Outro
                        </td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td>
                            <input type="radio" name="eduardostatus" <c:if test = "${eduardo.eduardostatus == true}">checked</c:if> value="true">Ativo
                            <input type="radio" name="eduardostatus" <c:if test = "${eduardo.eduardostatus == false}">checked</c:if> value="false">Inativo
                    </td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" value="Enviar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>