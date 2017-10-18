package br.com.eduardo.floricultura.bll;

import br.com.eduardo.floricultura.dal.ClienteDAO;
import br.com.eduardo.floricultura.dal.PedidoDAO;
import br.com.eduardo.floricultura.model.Cliente;
import br.com.eduardo.floricultura.model.ItemPedido;
import br.com.eduardo.floricultura.model.Pedido;
import br.com.eduardo.floricultura.model.Produto;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "cadPedidos", urlPatterns = {"/cadPedidos"})
public class CadPedidos extends HttpServlet {

    private static final String INSERT_OR_EDIT = "?p=editPedidos";
    private static final String LIST = "?p=listPedidos";
    private final PedidoDAO dao;

    public CadPedidos() {
        super();
        dao = new PedidoDAO();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = LIST;
        String action = request.getParameter("action");

        // Listar (Padrão)
        if (action == null || action.equalsIgnoreCase("list")) {
            forward = LIST;
            try {
                request.setAttribute("pedidos", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar pedidos");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Excluir
        else if (action.equals("delete")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            Pedido pedido = new Pedido();
            pedido.setCodigo(codigo);
            try {
                dao.delete(pedido);
                request.setAttribute("alert", "Pedido excluído com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao excluir pedido");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST;
            try {
                request.setAttribute("pedidos", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar pedidos");
                Logger.getLogger(CadPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Editar
        else if (action.equals("edit")) {
            forward = INSERT_OR_EDIT;
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            try {
                Pedido pedido = dao.retrieve(codigo);

                request.setAttribute("pedido", pedido);
                request.setAttribute("action", "update");
            } catch (DatabaseException ex) {
                Logger.getLogger(CadPedidos.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("alert", "Erro ao buscar dados para edição");
            }
        } // Inserir
        else if (action.equals("insert")) {
            forward = INSERT_OR_EDIT;
            request.setAttribute("action", "insert");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
//
        if (action.equals("insert")) {
            try {
                // Novo pedido
                Pedido pedido = new Pedido();

                // Pega dados do cliente
                char tipo = request.getParameter("tipo").charAt(0);
                long idf = Long.parseLong(
                        request.getParameter("idf")
                                .replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", ""));
                pedido.setCliente(new ClienteDAO().retrieve(idf, tipo));

                // Levanta itens
                Enumeration<String> params = request.getParameterNames();
                List<ItemPedido> itens = new ArrayList<>();
                while (params.hasMoreElements()) {
                    String par_name = params.nextElement();
                    if (par_name.contains("item") && par_name.contains("_codigo")
                            && request.getParameter(par_name).length() > 0) {
                        ItemPedido item = new ItemPedido();

                        item.setProduto(new Produto(Long.parseLong(request.getParameter(par_name))));

                        item.setQuantidade(Double.parseDouble(request.getParameter(params.nextElement())));
                        item.setValor(Double.parseDouble(request.getParameter(params.nextElement())));

                        itens.add(item);
                    }
                    pedido.setItens(itens);
                }
                pedido.setItens(itens);

                pedido.setData(new Date(System.currentTimeMillis()));

                dao.create(pedido);
                request.setAttribute("alert", "Pedido cadastrado com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao cadastrar pedido.");
                Logger.getLogger(CadPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("update")) {
//            try {
//                Produto produto = dao.retrieve(Long.parseLong(request.getParameter("codigo")));
//
//                produto.setNome(request.getParameter("nome"));
//                produto.setDescricao(request.getParameter("descricao"));
//                produto.setTipo(TipoProduto.getById(Integer.parseInt(request.getParameter("tipo"))));
//                produto.setUnidade(request.getParameter("unidade"));
//                produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
//                produto.setValor(Double.parseDouble(request.getParameter("valor")));
//                produto.setStatus(Boolean.valueOf(request.getParameter("status")));
//
//                dao.update(produto);
//                request.setAttribute("alert", "Produto atualizado com sucesso.");
//
//            } catch (DatabaseException ex) {
//                request.setAttribute("alert", "Erro ao atualizar produto.");
//                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST);
        try {
            request.setAttribute("pedidos", dao.getAll());
        } catch (DatabaseException ex) {
            request.setAttribute("alert", "Erro ao listar pedidos");
            Logger.getLogger(CadPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}
