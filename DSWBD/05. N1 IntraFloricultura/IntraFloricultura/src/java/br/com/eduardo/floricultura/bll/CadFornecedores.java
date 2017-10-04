package br.com.eduardo.floricultura.bll;

import br.com.eduardo.floricultura.dal.FornecedorDAO;
import br.com.eduardo.floricultura.model.Fornecedor;
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
@WebServlet(name = "cadFornecedores", urlPatterns = {"/cadFornecedores"})
public class CadFornecedores extends HttpServlet {

    private static final String INSERT_OR_EDIT = "?p=editFornecedores";
    private static final String LIST = "?p=listFornecedores";
    private final FornecedorDAO dao;

    public CadFornecedores() {
        super();
        dao = new FornecedorDAO();
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
                request.setAttribute("fornecedores", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar fornecedores");
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // Excluir
        else if (action.equals("delete")) {
            long idf = Long.parseLong(request.getParameter("idf"));
            char tipo = request.getParameter("tipo").charAt(0);

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdf(idf);
            fornecedor.setTipo(tipo);
            try {
                dao.delete(fornecedor);
                request.setAttribute("alert", "Fornecedor excluído com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao excluir fornecedor");
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = LIST;
            try {
                request.setAttribute("fornecedores", dao.getAll());
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao listar fornecedores");
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("edit")) {
            forward = INSERT_OR_EDIT;
            long idf = Long.parseLong(request.getParameter("idf"));
            char tipo = request.getParameter("tipo").charAt(0);
            try {
                Fornecedor fornecedor = dao.retrieve(idf, tipo);
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("action", "update");
            } catch (DatabaseException ex) {
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("alert", "Erro ao buscar dados para edição");
            }
        } else if (action.equals("insert")) {

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
            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setIdf(
                    Long.parseLong(
                            request.getParameter("idf")
                                    .replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", ""))
            );
            fornecedor.setTipo(request.getParameter("tipo").charAt(0));
            fornecedor.setNome(request.getParameter("nome"));
            fornecedor.setEndereco(request.getParameter("endereco"));
            fornecedor.setFone(request.getParameter("fone"));
            fornecedor.setEmail(request.getParameter("email"));
            fornecedor.setDtcadastro(new Date());
            fornecedor.setStatus(Boolean.valueOf(request.getParameter("status")));

            try {
                dao.create(fornecedor);
                request.setAttribute("alert", "Fornecedor cadastrado com sucesso.");
            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao cadastrar fornecedor.");
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("update")) {
            try {
                Fornecedor fornecedor = dao.retrieve(
                        Long.parseLong(request.getParameter("idf")
                                .replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "")),
                        request.getParameter("tipo").charAt(0));

                fornecedor.setNome(request.getParameter("nome"));
                fornecedor.setEndereco(request.getParameter("endereco"));
                fornecedor.setFone(request.getParameter("fone"));
                fornecedor.setEmail(request.getParameter("email"));
                fornecedor.setStatus(Boolean.valueOf(request.getParameter("status")));

                dao.update(fornecedor);
                request.setAttribute("alert", "Fornecedor atualizado com sucesso.");

            } catch (DatabaseException ex) {
                request.setAttribute("alert", "Erro ao atualizar fornecedor.");
                Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST);
        try {
            request.setAttribute("fornecedores", dao.getAll());
        } catch (DatabaseException ex) {
            request.setAttribute("alert", "Erro ao listar fornecedores");
            Logger.getLogger(CadFornecedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}
