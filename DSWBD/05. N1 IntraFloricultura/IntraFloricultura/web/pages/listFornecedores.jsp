<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-1 sidenav">
    <p><a href="cadFornecedores?action=insert">Incluir Novo</a></p>
</div>
<div class="col-sm-11 text-left">
    <h1>Cadastro de Fornecedores</h1>
    <br>
    <table class="lista">
        <tr class="tablerow">
            <th>CPF/CNPJ</th>
            <th>Tipo</th>
            <th>Nome</th>
            <th>Endereço</th>
            <th>Telefone</th>
            <th>E-mail</th>
            <th>Data de Cadastro</th>
            <th>Status</th>
            <th>Ação</th>
        </tr>
        <c:if test = "${fornecedores.size() < 1}">
            <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
        </c:if>
        <% int c = 0;%>
        <c:forEach items="${fornecedores}" var="fornecedor">
            <tr class="tablerow <%= (c++ % 2 != 0) ? "list-odd" : ""%>">
                <td><c:out value="${fornecedor.idf_formatado}" /></td>
                <td><c:out value="${fornecedor.tiponome}" /></td>
                <td><c:out value="${fornecedor.nome}" /></td>
                <td><c:out value="${fornecedor.endereco}" /></td>
                <td><c:out value="${fornecedor.fone}" /></td>
                <td><c:out value="${fornecedor.email}" /></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${fornecedor.dtcadastro}" /></td>
                <td>
                    <c:if test = "${fornecedor.status == true}">Ativo</c:if>
                    <c:if test = "${fornecedor.status == false}">Inativo</c:if>
                </td>
                <td>
                    <a href="cadFornecedores?action=edit&idf=<c:out value="${fornecedor.idf}&tipo=${fornecedor.tipo}" />">Editar</a>
                    <a href="cadFornecedores?action=delete&idf=<c:out value="${fornecedor.idf}&tipo=${fornecedor.tipo}" />" onclick="return confirm('Você tem certeza que deseja excluir o fornecedor?');">Excluir</a>
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