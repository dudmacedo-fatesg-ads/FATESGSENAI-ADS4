package br.com.eduardo.floricultura.bll;

import br.com.eduardo.floricultura.dal.ProdutoDAO;
import br.com.eduardo.floricultura.model.Produto;
import br.com.eduardo.floricultura.model.TipoProduto;
import br.com.eduardo.floricultura.util.DatabaseException;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "cadProdutos", urlPatterns = {"/cadProdutos"})
public class CadProdutos extends HttpServlet {

    private static final String INSERT_OR_EDIT = "?p=editProdutos";
    private static final String LIST = "?p=listProdutos";
    private final ProdutoDAO dao;

    public CadProdutos() {
        super();
        dao = new ProdutoDAO();
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
                request.setAttribute("produtos", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar produtos");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Excluir
        else if (action.equals("delete")) {
            long codigo = Long.parseLong(request.getParameter("codigo"));

            Produto produto = new Produto();
            produto.setCodigo(codigo);
            try {
                dao.delete(produto);
                request.setAttribute("alert", "Produto excluído com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao excluir produto");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST;
            try {
                request.setAttribute("produtos", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar produtos");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("edit")) {
//            
            forward = INSERT_OR_EDIT;
            long codigo = Long.parseLong(request.getParameter("codigo"));
            try {
                Produto produto = dao.retrieve(codigo);
                request.setAttribute("produto", produto);
                request.setAttribute("tipos", TipoProduto.values());
                request.setAttribute("action", "update");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao buscar dados para edição");
            }
        } else if (action.equals("insert")) {

            forward = INSERT_OR_EDIT;
            request.setAttribute("action", "insert");
            request.setAttribute("tipos", TipoProduto.values());
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
            Produto produto = new Produto();

            produto.setCodigo(Long.parseLong(request.getParameter("codigo")));
            produto.setNome(request.getParameter("nome"));
            produto.setDescricao(request.getParameter("descricao"));
            produto.setDtcadastro(new Date());
            produto.setTipo(TipoProduto.getById(Integer.parseInt(request.getParameter("tipo"))));
            produto.setUnidade(request.getParameter("unidade"));
            produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
            produto.setValor(Double.parseDouble(request.getParameter("valor")));
            produto.setStatus(Boolean.valueOf(request.getParameter("status")));

            try {
                dao.create(produto);
                request.setAttribute("alert", "Produto cadastrado com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao cadastrar produto.");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("update")) {
            try {
                Produto produto = dao.retrieve(Long.parseLong(request.getParameter("codigo")));

                produto.setNome(request.getParameter("nome"));
                produto.setDescricao(request.getParameter("descricao"));
                produto.setTipo(TipoProduto.getById(Integer.parseInt(request.getParameter("tipo"))));
                produto.setUnidade(request.getParameter("unidade"));
                produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
                produto.setValor(Double.parseDouble(request.getParameter("valor")));
                produto.setStatus(Boolean.valueOf(request.getParameter("status")));

                dao.update(produto);
                request.setAttribute("alert", "Produto atualizado com sucesso.");

            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao atualizar produto.");
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        try {
            request.setAttribute("produtos", dao.getAll());
        } catch (DatabaseException ex) {
            request.setAttribute("alert", "Erro ao listar produtos");
            Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}
