<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/formscript.js"></script>

<div class="col-sm-2 sidenav">

</div>
<div class="col-sm-8 text-left">
    <h1><c:if test="${action == 'insert'}">Cadastrar</c:if><c:if test="${action == 'edit'}">Editar</c:if> Pedido</h1>
        <br/>
        <div class="formulario">
            <form method="POST" action="cadPedidos" id="formPedido" name="formPedido">
                    <input type="hidden" value="<c:out value="${action}" />" name="action"/>
            <table>
                <c:set value="${pedido.clienteobj}" var="cliente"/>
                <tr class="tablerow">
                    <td>Tipo Cliente: </td>
                    <td>
                        <input type="radio" name="tipo" <c:if test = "${cliente.tipo == 'F'.charAt(0)}">checked </c:if> readonly onclick="setFormPF();" value="F"/>Pessoa Física
                        <input type="radio" name="tipo" <c:if test = "${cliente.tipo == 'J'.charAt(0)}">checked </c:if> readonly onclick="setFormPJ();" value="J"/>Pessoa Jurídica
                        </td>
                    </tr>
                    <tr class="tablerow">
                        <td>
                            <label for="idf" id="idflabel">
                            <c:if test = "${cliente.tipo == 'F'.charAt(0)}">CPF<b>(*)</b>:</c:if>
                            <c:if test = "${cliente.tipo == 'J'.charAt(0)}">CNPJ<b>(*)</b>:</c:if>
                            </label></td>
                        <td>
                            <input name="idf" type="text" id="idf" readonly required onblur="idfBlurPedido();" value="${cliente.idf_formatado}">
                        <label id="verifidf"><c:out value="${cliente.nome}" /></label>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <table class="lista">
                            <tr class="tablerow">
                                <td colspan="5">
                                    <p align="left"><i>Produtos no Pedido:</i></p>
                                </td>
                            </tr>
                            <tr class="tablerow">
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Unidade</th>
                                <th>Quantidade</th>
                                <th>Valor Unit.</th>
                                <th>Valor Total</th>
                                <th>Ação</th>
                            </tr>
                            <tbody id="itens">
                                <c:if test = "${produtos.size() < 1}">
                                    <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
                                </c:if>
                                <% int c = 0;%>
                                <c:forEach items="${pedido.itens}" var="item">
                                    <tr class="tablerow <%= ((c % 2 != 0) ? "list-odd" : "")%>" id="item<%= c%>">
                                        <td>
                                            <input name="item<%= c%>_codigo" id="item<%= c%>_codigo" onblur="itemPedidoCodigoBlur(<%= c%>);" value="<c:out value="${item.produtocodigo}" />" type="number" placeholder="Código do Produto">
                                        </td>
                                        <td id="item<%= c%>_nome"><c:out value="${item.produtonome}" /></td>
                                        <td id="item<%= c%>_unidade"><c:out value="${item.produtounidade}" /></td>
                                        <td>
                                            <input name="item<%= c%>_quantidade" id="item<%= c%>_quantidade" type="number" step="0.01" value="0" onblur="updateTotalPedido();">
                                        </td>
                                        <td><input name="item<%= c%>_valor" id="item<%= c%>_valor" type="number" step="0.01" readonly value="0"></td>
                                        <td><input id="item<%= c%>_valortotal" type="number" step="0.01" readonly value="0"></td>
                                        <td><input type="button" alt="Excluir" onclick="return removeItemPedido('item<%= c%>');" value="  -  "/></td>
                                    </tr>
                                </c:forEach>

                            </tbody>

                            <tr class="tablerow">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>
                                    <input type="button" value=" + " onclick="adicionaItemPedido();"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="tablerow">
                    <td colspan="2">
                        <p align="right">
                            Total do Pedido : <input step="0.01" type="number" id="totpedido" readonly value="0"/>
                        </p>
                    </td>
                </tr>

                <tr class="tablerow">
                    <td colspan="2">
                        <input type="reset" value="Limpar">
                        <input onclick="return validaCadPedido();" type="submit" value="Enviar">
                        <input onclick="window.location = 'cadPedidos';" type="button" value="Cancelar">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="col-sm-2 sidenav">
</div>
<script type="text/javascript">
    $(document).ready(function () {
        window["count"] = <%= ++c%>;
        $("#totpedido").maskMoney({
            prefix: "R$:",
            decimal: ",",
            thousands: "."
        });
    });
</script>