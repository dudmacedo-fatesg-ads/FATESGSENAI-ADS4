<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.zebra-datepicker.js"></script>

<script type="text/javascript" src="js/formscript.js"></script>

<div class="col-sm-2 sidenav">

</div>
<div class="col-sm-8 text-left">
    <h1>Cadastro de Clientes</h1>
    <br>
    <div class="formulario">
        <form method="POST" action="cadClientes" id="formCliente" name="frmEditCliente">
            <input type="hidden" value="<c:out value="${action}" />" name="action">
            <table>
                <tr class="edit-field">
                    <td>Tipo: </td>
                    <td>
                        <input type="radio" name="tipo" <c:if test = "${eduardo.eduardostatus == true}">checked</c:if> value="F"/>Pessoa Física
                        <input type="radio" name="tipo" <c:if test = "${eduardo.eduardostatus == false}">checked</c:if> value="J"/>Pessoa Jurídica
                        </td>
                    </tr>
                    <tr class="edit-field">
                        <td>Nº CPF: </td>
                        <td><input name="cpf" type="text" class="p" id="cpf" required></td>
                </tr>
                <tr class="edit-field">
                    <td>Nome:</td>
                    <td><input type="text" name="nome" value="${medico.nu_crm}"></td>
                </tr>
                <tr class="edit-field">
                    <td>Nome: </td>
                    <td><input type="text" name="nome" maxlength="60" value="${medico.nome}"></td>
                </tr>
                <tr class="edit-field">
                    <td>Telefone: </td>
                    <td><input type="text" name="telefone" maxlength="18" value="${medico.telefone}"></td>
                </tr>
                <tr class="edit-field">
                    <td>Especialidades: </td>
                    <td><input type="text" name="especialidades" maxlength="100" value="${medico.especialidades}"></td>
                </tr>
                <tr class="edit-field">
                    <td colspan="2">
                        <input type="reset" value="Limpar">
                        <input type="submit" value="Enviar">
                        <input onclick="window.location = 'cadClientes';" type="button" value="Cancelar">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="col-sm-2 sidenav">
</div>