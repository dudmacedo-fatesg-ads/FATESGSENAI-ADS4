<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-1 sidenav">
    <p><a href="cadPedidos?action=insert">Incluir Novo</a></p>
</div>
<div class="col-sm-11 text-left">
    <h1>Pedidos</h1>
    <br>
<!--    <form name="frmBuscarPedido" action="cadPedidos" method="GET">
        <input type="number" placeholder="Código do Pedido"/>
        <input type="submit" value="Buscar"/>
    </form>-->
    <br>
    <table class="lista">
        <tr class="tablerow">
            <th>Código</th>
            <th>Cliente</th>
            <th>Data</th>
            <th>Valor Total</th>
        </tr>
        <c:if test = "${pedidos.size() < 1}">
            <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
        </c:if>
        <% int c = 0;%>
        <c:forEach items="${pedidos}" var="pedido">
            <tr class="tablerow <%= (c++ % 2 != 0) ? "list-odd" : ""%>">
                <td><c:out value="${pedido.codigo}" /></td>
                <td><c:out value="${pedido.clientenome}" /></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.data}" /></td>
                <td><c:out value="${pedido.valortotal_formatado}" /></td>
                <td>
                    <a href="cadPedidos?action=edit&codigo=<c:out value="${pedido.codigo}" />">Editar</a>
                    <a href="cadPedidos?action=delete&codigo=<c:out value="${pedido.codigo}" />" onclick="return confirm('Você tem certeza que deseja excluir o pedido?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<c:if test = "${alert != null}">
    <script type="text/javascript">
        alert('<c:out value="${alert}"/>');
    </script>
</c:if>
<!--<div class="col-sm-2 sidenav">
</div>-->