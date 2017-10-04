<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-1 sidenav">
    <p><a href="cadProdutos?action=insert">Incluir Novo</a></p>
</div>
<div class="col-sm-11 text-left">
    <h1>Cadastro de Produtos</h1>
    <br>
    <table class="lista">
        <tr class="tablerow">
            <th>Código</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Tipo</th>
            <th>Data de Cadastro</th>
            <th>Unidade</th>
            <th>Quantidade</th>
            <th>Valor Unit.</th>
            <th>Status</th>
            <th>Ação</th>
        </tr>
        <c:if test = "${produtos.size() < 1}">
            <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
        </c:if>
        <% int c = 0;%>
        <c:forEach items="${produtos}" var="produto">
            <tr class="tablerow <%= (c++ % 2 != 0) ? "list-odd" : ""%>">
                <td><c:out value="${produto.codigo}" /></td>
                <td><c:out value="${produto.nome}" /></td>
                <td><c:out value="${produto.descricao}" /></td>
                <td><c:out value="${produto.tipo}" /></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dtcadastro}" /></td>
                <td><c:out value="${produto.unidade}" /></td>
                <td><c:out value="${produto.quantidade}" /></td>
                <td><c:out value="${produto.valor_formatado}" /></td>
                <td>
                    <c:if test = "${produto.status == true}">Ativo</c:if>
                    <c:if test = "${produto.status == false}">Inativo</c:if>
                    </td>
                    <td>
                        <a href="cadProdutos?action=edit&codigo=<c:out value="${produto.codigo}" />">Editar</a>
                    <a href="cadProdutos?action=delete&codigo=<c:out value="${produto.codigo}" />" onclick="return confirm('Você tem certeza que deseja excluir o produto?');">Excluir</a>
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