<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-2 sidenav">
    <p><a href="cadClientes?action=insert">Incluir Novo</a></p>
</div>
<div class="col-sm-10 text-left">
    <h1>Cadastro de Clientes</h1>
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
        <c:if test = "${clientes.size() < 1}">
            <tr><td colspan="9"><p align="center"><i>Nenhum registro encontrado</i></p></td></tr>
        </c:if>
        <% int c = 0;%>
        <c:forEach items="${clientes}" var="cliente">
            <tr class="tablerow <%= (c++ % 2 != 0) ? "list-odd" : ""%>">
                <td><c:out value="${cliente.idfformatado}" /></td>
                <td><c:out value="${cliente.tiponome}" /></td>
                <td><c:out value="${cliente.nome}" /></td>
                <td><c:out value="${cliente.endereco}" /></td>
                <td><c:out value="${cliente.fone}" /></td>
                <td><c:out value="${cliente.email}" /></td>
                <td><c:out value="${cliente.dtcadastro}" /></td>
                <td><c:out value="${cliente.status}" /></td>
                <td>
                    <a href="cadClientes?action=edit&id=<c:out value="${cliente.idf}" />">Editar</a>
                    <a href="cadClientes?action=delete&id=<c:out value='${cliente.idf}' />">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!--<div class="col-sm-2 sidenav">
</div>-->