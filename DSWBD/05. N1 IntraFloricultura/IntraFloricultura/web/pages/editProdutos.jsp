<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/formscript.js"></script>

<div class="col-sm-2 sidenav">

</div>
<div class="col-sm-8 text-left">
    <h1>Cadastro de Produtos</h1>
    <br>
    <div class="formulario">
        <form method="POST" action="cadProdutos" id="formProduto" name="formProduto">
            <input type="hidden" value="<c:out value="${action}" />" name="action">
            <table>
                <tr class="tablerow">
                    <td>
                        <label for="codigo">Código<b>(*)</b>:</label></td>
                    <td>
                        <input name="codigo" type="number" id="codigo" <c:if test="${action == 'update'}">readonly</c:if> required value="${produto.codigo}">
                    </td>
                </tr>
                <tr class="tablerow">
                    <td><label for="nome">Nome<b>(*)</b>:</label></td>
                    <td><input type="text" name="nome" id="nome" required maxlength="25" value="${produto.nome}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="descricao">Descrição<b>(*)</b>:</label></td>
                    <td><input type="text" name="descricao" id="descricao" required maxlength="100" value="${produto.descricao}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="tipo">Tipo<b>(*)</b>:</label></td>
                    <td>
                        <select name="tipo">
                            <c:forEach items="${tipos}" var="tipo">
                                <option value="<c:out value="${tipo.id}"/>" <c:if test="${tipo.id == produto.tipo.id}">selected</c:if>><c:out value="${tipo.descricao}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="tablerow">
                    <td><label for="unidade">Unidade<b>(*)</b>:</label></td>
                    <td><input type="text" name="unidade" id="unidade" required maxlength="2" value="${produto.unidade}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="quantidade">Quantidade<b>(*)</b>:</label></td>
                    <td><input type="number" step="0.01" name="quantidade" id="quantidade" required value="${produto.quantidade}"></td>
                </tr>
                <tr class="tablerow">
                    <td><label for="valor">Valor<b>(*)</b>:</label></td>
                    <td><input type="number" step="0.01" name="valor" id="valor" required value="${produto.valor}"></td>
                </tr>
                <tr class="tablerow">
                    <td>Status:</td>
                    <td>
                        <input type="radio" name="status" id="statustrue" <c:if test = "${produto.status == true}">checked</c:if> value="true"/>Ativo
                        <input type="radio" name="status" id="statusfalse" <c:if test = "${produto.status == false}">checked</c:if> value="false"/>Inativo
                    </td>
                </tr>
                <tr class="tablerow">
                    <td colspan="2">
                        <input type="reset" value="Limpar">
                        <input onclick="return validaCadProduto();"type="submit" value="Enviar">
                        <input onclick="window.location = 'cadProdutos';" type="button" value="Cancelar">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="col-sm-2 sidenav">
</div>
<script type="text/javascript">
    $(document).ready(maskCadProdutos());
</script>