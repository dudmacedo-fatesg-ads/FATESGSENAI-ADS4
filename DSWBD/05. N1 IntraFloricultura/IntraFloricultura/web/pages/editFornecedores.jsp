<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/formscript.js"></script>

<div class="col-sm-2 sidenav">

</div>
<div class="col-sm-8 text-left">
    <h1>Cadastro de Fornecedores</h1>
    <br>
    <div class="formulario">
        <form method="POST" action="cadFornecedores" id="formFornecedor" name="formFornecedor">
            <input type="hidden" value="<c:out value="${action}" />" name="action">
            <table>
                <tr class="tablerow">
                    <td>Tipo: </td>
                    <td>
                        <input type="radio" name="tipo" <c:if test = "${fornecedor.tipo == 'F'.charAt(0)}">checked readonly</c:if> onclick="setFormPF();" value="F"/>Pessoa Física
                        <input type="radio" name="tipo" <c:if test = "${fornecedor.tipo == 'J'.charAt(0)}">checked readonly</c:if> onclick="setFormPJ();" value="J"/>Pessoa Jurídica
                    </td>
                </tr>
                <tr class="tablerow">
                    <td>
                        <label for="idf" id="idflabel">
                        <c:if test = "${fornecedor.tipo == 'F'.charAt(0)}">CPF<b>(*)</b>:</c:if>
                        <c:if test = "${fornecedor.tipo == 'J'.charAt(0)}">CNPJ<b>(*)</b>:</c:if>
                    </label></td>
                    <td>
                        <input name="idf" type="text" id="idf" readonly required onblur="idfBlur();" value="${fornecedor.idf_formatado}">
                        <label id="verifidf"></label>
                    </td>
                </tr>
                <tr class="tablerow">
                    <td><label for="nome">Nome<b>(*)</b>:</label></td>
                    <td><input type="text" name="nome" id="nome" maxlength="80" value="${fornecedor.nome}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="nome">Endereço<b>(*)</b>:</label></td>
                    <td><input type="text" name="endereco" id="endereco" maxlength="100" value="${fornecedor.endereco}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="nome">Fone<b>(*)</b>:</label></td>
                    <td><input type="text" name="fone" id="fone" value="${fornecedor.fone}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="nome">E-mail:</label></td>
                    <td><input type="email" name="email" id="email" maxlength="80" value="${fornecedor.email}"></td>
                </tr>
                <tr class="tablerow">
                    <td>Status:</td>
                    <td>
                        <input type="radio" name="status" id="statustrue" <c:if test = "${fornecedor.status == true}">checked</c:if> value="true"/>Ativo
                        <input type="radio" name="status" id="statusfalse" <c:if test = "${fornecedor.status == false}">checked</c:if> value="false"/>Inativo
                    </td>
                </tr>
                <tr class="tablerow">
                    <td colspan="2">
                        <input type="reset" value="Limpar">
                        <input onclick="return validaCadFornecedor();"type="submit" value="Enviar">
                        <input onclick="window.location = 'cadFornecedores';" type="button" value="Cancelar">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="col-sm-2 sidenav">
</div>
<script type="text/javascript">
    $(document).ready(maskCadFornecedores());
</script>